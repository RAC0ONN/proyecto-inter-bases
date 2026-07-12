package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectoBases.dto.ParejaDTO;
import co.edu.unbosque.proyectoBases.entity.Cliente;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.exceptions.RecursoLimiteCupoExcedidoException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.ClienteRepository;
import co.edu.unbosque.proyectoBases.repository.ParejaRepository;

@Service
public class ParejaService {

	@Autowired
	private ParejaRepository parejaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public void crear(ParejaDTO dto) {
		Cliente cliente = clienteRepository.obtenerPorId(dto.getIdCliente());
		if (cliente == null) {
			throw new RecursoNoExistenteException("No existe un cliente con ese id, no se puede asignar la pareja");
		}

		double cupoActualAsignado = parejaRepository.obtenerCupoAsignadoTotal(dto.getIdCliente());
		double nuevoTotal = cupoActualAsignado + dto.getCupoAsignado();
		if (nuevoTotal > cliente.getCupoTotal()) {
			double disponible = cliente.getCupoTotal() - cupoActualAsignado;
			throw new RecursoLimiteCupoExcedidoException(String.format(
					"El cupo asignado (%.2f) supera el cupo disponible del cliente (%.2f). "
							+ "Cupo total: %.2f, ya asignado a otras parejas: %.2f",
					dto.getCupoAsignado(), disponible, cliente.getCupoTotal(), cupoActualAsignado));
		}

		parejaRepository.crearPareja(dto.getPrimerNombre(), dto.getSegundoNombre(), dto.getPrimerApellido(),
				dto.getSegundoApellido(), dto.getNombreUsuario(), dto.getContraseniaUsuario(), dto.getCupoAsignado(),
				dto.getCorreoElectronico(), dto.getIdCliente());
	}

	public void actualizar(ParejaDTO dto) {
		if (parejaRepository.obtenerPorId(dto.getIdPareja()) == null) {
			throw new RecursoNoExistenteException("Error, la pareja no existe");
		}
		Cliente cliente = clienteRepository.obtenerPorId(dto.getIdCliente());
		if (cliente == null) {
			throw new RecursoNoExistenteException("No existe un cliente con ese id, no se puede asignar la pareja");
		}

		double cupoDeOtrasParejas = parejaRepository.obtenerCupoAsignadoTotalExcluyendo(dto.getIdCliente(),
				dto.getIdPareja());
		double nuevoTotal = cupoDeOtrasParejas + dto.getCupoAsignado();
		if (nuevoTotal > cliente.getCupoTotal()) {
			double disponible = cliente.getCupoTotal() - cupoDeOtrasParejas;
			throw new RecursoLimiteCupoExcedidoException(String.format(
					"El cupo asignado (%.2f) supera el cupo disponible del cliente (%.2f). "
							+ "Cupo total: %.2f, ya asignado a otras parejas: %.2f",
					dto.getCupoAsignado(), disponible, cliente.getCupoTotal(), cupoDeOtrasParejas));
		}

		parejaRepository.actualizarPareja(dto.getPrimerNombre(), dto.getSegundoNombre(), dto.getPrimerApellido(),
				dto.getSegundoApellido(), dto.getNombreUsuario(), dto.getContraseniaUsuario(), dto.getCupoAsignado(),
				dto.getCorreoElectronico(), dto.getIdCliente(), dto.getIdPareja());
	}

	public ArrayList<ParejaDTO> obtenerTodas() {
		List<Pareja> entidades = parejaRepository.obtenerTodas();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay parejas registradas");
		}
		ArrayList<ParejaDTO> resultado = new ArrayList<>();
		for (Pareja p : entidades) {
			resultado.add(mapear(p));
		}
		return resultado;
	}

	public ParejaDTO obtenerPorId(int idPareja) {
		Pareja entidad = parejaRepository.obtenerPorId(idPareja);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe una pareja con ese id");
		}
		return mapear(entidad);
	}

	public ArrayList<ParejaDTO> obtenerPorCliente(int idCliente) {
		List<Pareja> entidades = parejaRepository.obtenerPorCliente(idCliente);
		ArrayList<ParejaDTO> resultado = new ArrayList<>();
		if (entidades != null) {
			for (Pareja p : entidades) {
				resultado.add(mapear(p));
			}
		}
		return resultado;
	}

	public double obtenerCupoConsumido(int idCliente) {
		return parejaRepository.obtenerCupoAsignadoTotal(idCliente);
	}

	public void eliminar(int idPareja) {
		if (parejaRepository.obtenerPorId(idPareja) == null) {
			throw new RecursoNoExistenteException("No existe una pareja con ese id");
		}
		parejaRepository.eliminarPareja(idPareja);
	}

	private ParejaDTO mapear(Pareja p) {
		ParejaDTO dto = new ParejaDTO();
		dto.setIdPareja(p.getIdPareja());
		dto.setPrimerNombre(p.getPrimerNombre());
		dto.setSegundoNombre(p.getSegundoNombre());
		dto.setPrimerApellido(p.getPrimerApellido());
		dto.setSegundoApellido(p.getSegundoApellido());
		dto.setNombreUsuario(p.getNombreUsuario());
		dto.setContraseniaUsuario(p.getContraseniaUsuario());
		dto.setCupoAsignado(p.getCupoAsignado());
		dto.setCorreoElectronico(p.getCorreoElectronico());
		if (p.getCliente() != null) {
			dto.setIdCliente(p.getCliente().getIdCliente());
		}
		return dto;
	}
}