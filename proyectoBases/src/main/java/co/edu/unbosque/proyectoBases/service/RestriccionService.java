package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.RestriccionDTO;
import co.edu.unbosque.proyectoBases.entity.Restriccion;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.RestriccionRepository;

@Service
public class RestriccionService {

	@Autowired
	private RestriccionRepository restriccionRepository;

	public void crear(RestriccionDTO dto) {
		restriccionRepository.crearRestriccion(dto.getIdRestriccion(), dto.getDiaSemana(), dto.getHoraInicio(),
				dto.getHoraFin(), dto.getIdPareja());
	}

	public ArrayList<RestriccionDTO> obtenerTodas() {
		List<Restriccion> entidades = restriccionRepository.obtenerTodas();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay restricciones ");
		}
		ArrayList<RestriccionDTO> resultado = new ArrayList<>();
		for (Restriccion r : entidades) {
			resultado.add(mapear(r));
		}
		return resultado;
	}

	public RestriccionDTO obtenerPorId(int idRestriccion) {
		Restriccion entidad = restriccionRepository.obtenerPorId(idRestriccion);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe una restricción con ese id");
		}
		return mapear(entidad);
	}

	public void eliminar(int idRestriccion) {
		if (restriccionRepository.obtenerPorId(idRestriccion) == null) {
			throw new RecursoNoExistenteException("No existe una restriccion con ese id");
		}
		restriccionRepository.eliminarRestriccion(idRestriccion);
	}

	private RestriccionDTO mapear(Restriccion r) {
		RestriccionDTO dto = new RestriccionDTO();
		dto.setIdRestriccion(r.getIdRestriccion());
		dto.setDiaSemana(r.getDiaSemana());
		dto.setHoraInicio(r.getHoraInicio());
		dto.setHoraFin(r.getHoraFin());
		if (r.getPareja() != null) {
			dto.setIdPareja(r.getPareja().getIdPareja());
		}
		return dto;
	}
}