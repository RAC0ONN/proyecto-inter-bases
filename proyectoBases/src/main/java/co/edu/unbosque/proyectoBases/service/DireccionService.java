package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.DireccionDTO;
import co.edu.unbosque.proyectoBases.entity.Direccion;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.DireccionRepository;

@Service
public class DireccionService {

	@Autowired
	private DireccionRepository direccionRepository;

	public void crear(DireccionDTO dto) {
		direccionRepository.crearDireccion(dto.getIdDireccion(), dto.getViaPrincipal(), dto.getNumeroVia(),
				dto.getLetraVia(), dto.getSufijo(), dto.getNumeroGenerador(), dto.getPlaca(), dto.getIdBarrio(),
				dto.getIdCiudad());
	}

	public ArrayList<DireccionDTO> obtenerTodas() {
		List<Direccion> entidades = direccionRepository.obtenerTodas();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay direcciones");
		}
		ArrayList<DireccionDTO> resultado = new ArrayList<>();
		for (Direccion d : entidades) {
			resultado.add(mapear(d));
		}
		return resultado;
	}

	public DireccionDTO obtenerPorId(int idDireccion) {
		Direccion entidad = direccionRepository.obtenerPorId(idDireccion);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe una dirección con ese id");
		}
		return mapear(entidad);
	}

	public void eliminar(int idDireccion) {
		if (direccionRepository.obtenerPorId(idDireccion) == null) {
			throw new RecursoNoExistenteException("No existe una dirección con ese id");
		}
		direccionRepository.eliminarDireccion(idDireccion);
	}

	private DireccionDTO mapear(Direccion d) {
		DireccionDTO dto = new DireccionDTO();
		dto.setIdDireccion(d.getIdDireccion());
		dto.setViaPrincipal(d.getViaPrincipal());
		dto.setNumeroVia(d.getNumeroVia());
		dto.setLetraVia(d.getLetraVia());
		dto.setSufijo(d.getSufijo());
		dto.setNumeroGenerador(d.getNumeroGenerador());
		dto.setPlaca(d.getPlaca());

		if (d.getBarrio() != null) {
			dto.setIdBarrio(d.getBarrio().getIdBarrio());
		}
		if (d.getCiudad() != null) {
			dto.setIdCiudad(d.getCiudad().getIdCiudad());
		}
		return dto;
	}
}