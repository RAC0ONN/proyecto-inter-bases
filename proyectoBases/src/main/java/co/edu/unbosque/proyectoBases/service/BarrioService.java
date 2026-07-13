package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.BarrioDTO;
import co.edu.unbosque.proyectoBases.entity.Barrio;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.BarrioRepository;

@Service
public class BarrioService { 
	@Autowired
	private BarrioRepository barrioRepository;

	public ArrayList<BarrioDTO> obtenerTodos() {
		List<Barrio> entidades = barrioRepository.obtenerBarrios();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay barrios registrados ");
		}
		ArrayList<BarrioDTO> resultado = new ArrayList<>();
		for (Barrio b : entidades) {
			resultado.add(mapear(b));
		}
		return resultado;
	}

	public BarrioDTO obtenerPorId(int idBarrio) {
		Barrio entidad = barrioRepository.existePorId(idBarrio);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe un barrio con ese id");
		}
		return mapear(entidad);
	}

	private BarrioDTO mapear(Barrio b) {
		BarrioDTO dto = new BarrioDTO();
		dto.setIdBarrio(b.getIdBarrio());
		dto.setNombre(b.getNombre());
		return dto;

	}
}



