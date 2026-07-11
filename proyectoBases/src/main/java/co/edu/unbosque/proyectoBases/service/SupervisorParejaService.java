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
		supervisorParejaRepository.crearSupervisorPareja(dto.getIdSupervisorPareja(), dto.getIdSupervisor(),
				dto.getIdPareja());
	}

	public ArrayList<SupervisorParejaDTO> obtenerTodas() {
		List<SupervisorPareja> entidades = supervisorParejaRepository.findAll();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay asignaciones de supervisor-pareja ");
		}
		ArrayList<SupervisorParejaDTO> resultado = new ArrayList<>();
		for (SupervisorPareja sp : entidades) {
			resultado.add(mapear(sp));
		}
		return resultado;
	}

	public SupervisorParejaDTO obtenerPorId(int idSupervisorPareja) {
		SupervisorPareja entidad = supervisorParejaRepository.obtenerPorId(idSupervisorPareja);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe la asignacion con ese id");
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

	public void eliminar(int idSupervisorPareja) {
		if (supervisorParejaRepository.obtenerPorId(idSupervisorPareja) == null) {
			throw new RecursoNoExistenteException("No existe con ese id");
		}
		supervisorParejaRepository.eliminarSupervisorPareja(idSupervisorPareja);
	}

	private SupervisorParejaDTO mapear(SupervisorPareja sp) {
		SupervisorParejaDTO dto = new SupervisorParejaDTO();
		dto.setIdSupervisorPareja(sp.getIdSupervisorPareja());
		if (sp.getSupervisor() != null) {
			dto.setIdSupervisor(sp.getSupervisor().getIdSupervisor());
		}
		if (sp.getPareja() != null) {
			dto.setIdPareja(sp.getPareja().getIdPareja());
		}
		return dto;
	}
}