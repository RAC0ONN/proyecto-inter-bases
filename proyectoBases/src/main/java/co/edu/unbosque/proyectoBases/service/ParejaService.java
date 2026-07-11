package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.ParejaDTO;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.ParejaRepository;

@Service
public class ParejaService {

	@Autowired
	private ParejaRepository parejaRepository;

	public void crear(ParejaDTO dto) {
		parejaRepository.crearPareja(dto.getPrimerNombre(), dto.getSegundoNombre(), dto.getPrimerApellido(),
				dto.getSegundoApellido(), dto.getNombreUsuario(), dto.getContraseniaUsuario(), dto.getCupoAsignado(),
				dto.getCorreoElectronico(), dto.getIdCliente());
	}

	public void actualizar(ParejaDTO dto) {
		if (parejaRepository.obtenerPorId(dto.getIdPareja()) == null) {
			throw new RecursoNoExistenteException("Error, la pareja no existe");
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