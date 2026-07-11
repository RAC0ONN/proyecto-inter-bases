package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.CiudadDTO;
import co.edu.unbosque.proyectoBases.entity.Ciudad;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.CiudadRepository;

@Service
public class CiudadService {

	@Autowired
	private CiudadRepository ciudadRepository;

	public ArrayList<CiudadDTO> obtenerTodas() {
		List<Ciudad> entidades = ciudadRepository.obtenerCiudades();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay ciudades registradas en el sistema.");
		}
		ArrayList<CiudadDTO> resultado = new ArrayList<>();
		for (Ciudad c : entidades) {
			resultado.add(mapear(c));
		}
		return resultado;
	}

	public CiudadDTO obtenerPorId(int idCiudad) {
		Ciudad entidad = ciudadRepository.existePorId(idCiudad);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe una ciudad con ese ID.");
		}
		return mapear(entidad);
	}

	private CiudadDTO mapear(Ciudad c) {
		CiudadDTO dto = new CiudadDTO();
		dto.setIdCiudad(c.getIdCiudad());
		dto.setNombre(c.getNombre());
		return dto;
	}
}