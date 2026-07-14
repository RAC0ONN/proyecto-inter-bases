package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.SupervisorParejaDTO;
import co.edu.unbosque.proyectoBases.entity.SupervisorPareja;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.SupervisorParejaRepository;

@Service
public class SupervisorParejaService {

	@Autowired
	private SupervisorParejaRepository supervisorParejaRepository;

	public void crear(SupervisorParejaDTO dto) {
		if (supervisorParejaRepository.obtenerPorPareja(dto.getIdPareja()) != null) {
			throw new RecursoNoExistenteException("Esa pareja ya tiene un supervisor asignado");
		}
		supervisorParejaRepository.crearSupervisorPareja(dto.getIdSupervisor(), dto.getIdPareja());
	}

	public ArrayList<SupervisorParejaDTO> obtenerTodas() {
		List<SupervisorPareja> entidades = supervisorParejaRepository.findAll();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay asignaciones de supervisor-pareja");
		}
		ArrayList<SupervisorParejaDTO> resultado = new ArrayList<>();
		for (SupervisorPareja sp : entidades) {
			resultado.add(mapear(sp));
		}
		return resultado;
	}

	public SupervisorParejaDTO obtenerPorPareja(int idPareja) {
		SupervisorPareja entidad = supervisorParejaRepository.obtenerPorPareja(idPareja);
		if (entidad == null) {
			throw new RecursoNoExistenteException("Esa pareja no tiene supervisor asignado");
		}
		return mapear(entidad);
	}

	public ArrayList<SupervisorParejaDTO> obtenerPorSupervisor(int idSupervisor) {
		ArrayList<SupervisorPareja> entidades = supervisorParejaRepository.obtenerPorSupervisor(idSupervisor);
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("El supervisor no tiene parejas asignadas");
		}
		ArrayList<SupervisorParejaDTO> resultado = new ArrayList<>();
		for (SupervisorPareja sp : entidades) {
			resultado.add(mapear(sp));
		}
		return resultado;
	}

	public void eliminar(int idPareja) {
		if (supervisorParejaRepository.obtenerPorPareja(idPareja) == null) {
			throw new RecursoNoExistenteException("Esa pareja no tiene supervisor asignado");
		}
		supervisorParejaRepository.eliminarSupervisorPareja(idPareja);
	}

	private SupervisorParejaDTO mapear(SupervisorPareja sp) {
		SupervisorParejaDTO dto = new SupervisorParejaDTO();
		dto.setIdPareja(sp.getIdPareja());
		if (sp.getSupervisor() != null) {
			dto.setIdSupervisor(sp.getSupervisor().getIdSupervisor());
		}
		return dto;
	}
}