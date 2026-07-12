package co.edu.unbosque.proyectoBases.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.CompraDTO;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import co.edu.unbosque.proyectoBases.entity.Compra;
import co.edu.unbosque.proyectoBases.entity.Pareja;
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

	
	public void crear(CompraDTO dto) {
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

		compraRepository.crearCompra(dto.getIdCompra(), horaFinal, dto.getMonto(), fechaFinal, dto.getIdPareja(),
				dto.getIdAlmacen());
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
}