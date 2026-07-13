package co.edu.unbosque.proyectoBases.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.CompraDTO;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import co.edu.unbosque.proyectoBases.entity.Compra;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.entity.Restriccion;
import co.edu.unbosque.proyectoBases.exceptions.RecursoCompraRestringidaException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoCupoInsuficienteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.AlmacenRepository;
import co.edu.unbosque.proyectoBases.repository.CompraRepository;
import co.edu.unbosque.proyectoBases.repository.ParejaRepository;
import co.edu.unbosque.proyectoBases.repository.RestriccionRepository;
import co.edu.unbosque.proyectoBases.repository.SobrecupoRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private ParejaRepository parejaRepository;

	@Autowired
	private AlmacenRepository almacenRepository;

	@Autowired
	private RestriccionRepository restriccionRepository;

	@Autowired
	private SobrecupoRepository sobrecupoRepository;

	private static final Map<DayOfWeek, String> DIAS = Map.of(DayOfWeek.MONDAY, "LUNES", DayOfWeek.TUESDAY, "MARTES",
			DayOfWeek.WEDNESDAY, "MIERCOLES", DayOfWeek.THURSDAY, "JUEVES", DayOfWeek.FRIDAY, "VIERNES",
			DayOfWeek.SATURDAY, "SABADO", DayOfWeek.SUNDAY, "DOMINGO");

	public int crear(CompraDTO dto) {
		Pareja pareja = parejaRepository.obtenerPorId(dto.getIdPareja());
		if (pareja == null) {
			throw new RecursoNoExistenteException("No existe una pareja con ese id, no se puede registrar la compra");
		}

		Almacen almacen = almacenRepository.obtenerPorId(dto.getIdAlmacen());
		if (almacen == null) {
			throw new RecursoNoExistenteException("No existe un almacén con ese id, no se puede registrar la compra");
		}

		LocalDate fechaFinal = (dto.getFecha() != null) ? dto.getFecha() : LocalDate.now();
		LocalTime horaFinal = (dto.getHora() != null) ? dto.getHora() : LocalTime.now();

		validarRestriccionHoraria(dto.getIdPareja(), fechaFinal, horaFinal);
		validarCupoDisponible(pareja, dto.getMonto());

		int siguienteId = compraRepository.obtenerSiguienteId();
		compraRepository.crearCompra(siguienteId, horaFinal, dto.getMonto(), fechaFinal, dto.getIdPareja(),
				dto.getIdAlmacen());
		return siguienteId;
	}

	public ArrayList<CompraDTO> obtenerPorPareja(int idPareja) {
		List<Compra> entidades = compraRepository.obtenerPorPareja(idPareja);
		ArrayList<CompraDTO> resultado = new ArrayList<>();
		if (entidades != null) {
			for (Compra c : entidades) {
				resultado.add(mapear(c));
			}
		}
		return resultado;
	}
	
	public ArrayList<CompraDTO> obtenerTodas() {
		List<Compra> entidades = compraRepository.obtenerTodas();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay compras ");
		}
		ArrayList<CompraDTO> resultado = new ArrayList<>();
		for (Compra c : entidades) {
			resultado.add(mapear(c));
		}
		return resultado;
	}

	public CompraDTO obtenerPorId(int idCompra) {
		Compra entidad = compraRepository.obtenerPorId(idCompra);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe una compra con ese id");
		}
		return mapear(entidad);
	}

	public void eliminar(int idCompra) {
		if (compraRepository.obtenerPorId(idCompra) == null) {
			throw new RecursoNoExistenteException("No existe una compra con ese id");
		}
		compraRepository.eliminarCompra(idCompra);
	}

	private CompraDTO mapear(Compra c) {
		CompraDTO dto = new CompraDTO();
		dto.setIdCompra(c.getIdCompra());
		dto.setFecha(c.getFecha());
		dto.setHora(c.getHora());
		dto.setMonto(c.getMonto());

		if (c.getAlmacen() != null) {
			dto.setIdAlmacen(c.getAlmacen().getIdAlmacen());
		}
		if (c.getPareja() != null) {
			dto.setIdPareja(c.getPareja().getIdPareja());
		}
		return dto;
	}

	private void validarRestriccionHoraria(int idPareja, LocalDate fecha, LocalTime hora) {
		List<Restriccion> restricciones = restriccionRepository.obtenerPorPareja(idPareja);
		if (restricciones == null || restricciones.isEmpty()) {
			return;
		}

		String diaCompra = DIAS.get(fecha.getDayOfWeek());
		for (Restriccion r : restricciones) {
			boolean mismoDia = r.getDiaSemana() != null
					&& r.getDiaSemana().trim().toUpperCase(Locale.ROOT).equals(diaCompra);
			if (!mismoDia) {
				continue;
			}
			boolean dentroDelRango = !hora.isBefore(r.getHoraInicio()) && !hora.isAfter(r.getHoraFin());
			if (dentroDelRango) {
				throw new RecursoCompraRestringidaException(String.format(
						"No es posible registrar la compra porque existe una restricción para el día %s entre las %s y las %s.",
						diaCompra, r.getHoraInicio(), r.getHoraFin()));
			}
		}
	}

	private void validarCupoDisponible(Pareja pareja, double monto) {
		double montoGastado = compraRepository.obtenerMontoTotalPorPareja(pareja.getIdPareja());
		double cupoDisponible = pareja.getCupoAsignado() - montoGastado;

		if (monto <= cupoDisponible) {
			return;
		}

		double excedente = monto - Math.max(cupoDisponible, 0);
		double sobrecupoDisponible = sobrecupoRepository.obtenerMontoAprobadoTotalPorPareja(pareja.getIdPareja());

		if (excedente <= sobrecupoDisponible) {
			return;
		}

		throw new RecursoCupoInsuficienteException(
				String.format("La compra no puede realizarse porque el cupo disponible es de %.2f y el sobrecupo disponible es de %.2f. El valor de la compra es %.2f.",
						Math.max(cupoDisponible, 0), sobrecupoDisponible, monto));
	}

}