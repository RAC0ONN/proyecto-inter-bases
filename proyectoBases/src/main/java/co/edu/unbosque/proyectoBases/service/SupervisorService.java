package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.SupervisorDTO;
import co.edu.unbosque.proyectoBases.entity.Supervisor;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.SupervisorRepository;

@Service
public class SupervisorService {

	@Autowired
	private SupervisorRepository supervisorRepository;

	public void crear(SupervisorDTO dto) {
		supervisorRepository.crearSupervisor( dto.getPrimerNombre(), dto.getSegundoNombre(),
				dto.getPrimerApellido(), dto.getSegundoApellido(), dto.getNombreUsuario(), dto.getContraseniaUsuario(),
				dto.getCorreoElectronico(), dto.getIdAlmacen());
	}

	public ArrayList<SupervisorDTO> obtenerTodos() {
		List<Supervisor> entidades = supervisorRepository.obtenerTodos();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay supervisores registrados");
		}
		ArrayList<SupervisorDTO> resultado = new ArrayList<>();
		for (Supervisor s : entidades) {
			resultado.add(mapear(s));
		}
		return resultado;
	}

	public SupervisorDTO obtenerPorId(int idSupervisor) {
		Supervisor entidad = supervisorRepository.obtenerPorId(idSupervisor);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe un supervisor con ese id");
		}
		return mapear(entidad);
	}

	public void eliminar(int idSupervisor) {
		if (supervisorRepository.obtenerPorId(idSupervisor) == null) {
			throw new RecursoNoExistenteException("No existe un supervisor con ese id");
		}
		supervisorRepository.eliminarSupervisor(idSupervisor);
	}

	private SupervisorDTO mapear(Supervisor s) {
		SupervisorDTO dto = new SupervisorDTO();
		dto.setIdSupervisor(s.getIdSupervisor());
		dto.setPrimerNombre(s.getPrimerNombre());
		dto.setSegundoNombre(s.getSegundoNombre());
		dto.setPrimerApellido(s.getPrimerApellido());
		dto.setSegundoApellido(s.getSegundoApellido());
		dto.setNombreUsuario(s.getNombreUsuario());
		dto.setContraseniaUsuario(s.getContraseniaUsuario());
		dto.setCorreoElectronico(s.getCorreoElectronico());
		if (s.getAlmacen() != null) {
			dto.setIdAlmacen(s.getAlmacen().getIdAlmacen());
		}
		return dto;
	}
	public SupervisorDTO autenticar(String nombreUsuario, String contrasenia) {
		List<Supervisor> entidades = supervisorRepository.obtenerTodos();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay supervisores registrados en el sistema");
		}
		
		for (Supervisor s : entidades) {
			if (s.getNombreUsuario() != null && s.getNombreUsuario().equals(nombreUsuario)) {
				if (s.getContraseniaUsuario() != null && s.getContraseniaUsuario().equals(contrasenia)) {
					return mapear(s);
				} else {
					throw new RecursoNoExistenteException("Contraseña incorrecta");
				}
			}
		}
		throw new RecursoNoExistenteException("El usuario de supervisor no existe");
	}
}