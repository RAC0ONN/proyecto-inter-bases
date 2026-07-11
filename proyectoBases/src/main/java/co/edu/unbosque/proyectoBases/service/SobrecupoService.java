package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.SobrecupoDTO;
import co.edu.unbosque.proyectoBases.entity.Sobrecupo;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.SobrecupoRepository;

@Service
public class SobrecupoService {

	@Autowired
	private SobrecupoRepository sobrecupoRepository;

	public void crear(SobrecupoDTO dto) {
		sobrecupoRepository.crearSobrecupo(dto.getIdSobrecupo(), dto.getPorcentajeSobrecupo(), dto.getValorMaximo(),
				dto.getIdPareja());
	}

	public ArrayList<SobrecupoDTO> obtenerTodas() {
		List<Sobrecupo> entidades = sobrecupoRepository.findAll();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay registros de sobrecupo");
		}
		ArrayList<SobrecupoDTO> resultado = new ArrayList<>();
		for (Sobrecupo s : entidades) {
			resultado.add(mapear(s));
		}
		return resultado;
	}

	public SobrecupoDTO obtenerPorId(int idSobrecupo) {
		Sobrecupo entidad = sobrecupoRepository.obtenerPorId(idSobrecupo);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe un sobrecupo con ese id");
		}
		return mapear(entidad);
	}

	public ArrayList<SobrecupoDTO> obtenerPorPareja(int idPareja) {
		ArrayList<Sobrecupo> entidades = sobrecupoRepository.obtenerPorPareja(idPareja);
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay sobrecupos registrados para esa pareja");
		}
		ArrayList<SobrecupoDTO> resultado = new ArrayList<>();
		for (Sobrecupo s : entidades) {
			resultado.add(mapear(s));
		}
		return resultado;
	}

	public void eliminar(int idSobrecupo) {
		if (sobrecupoRepository.obtenerPorId(idSobrecupo) == null) {
			throw new RecursoNoExistenteException("No existe un sobrecupo con ese id");
		}
		sobrecupoRepository.eliminarSobrecupo(idSobrecupo);
	}

	private SobrecupoDTO mapear(Sobrecupo s) {
		SobrecupoDTO dto = new SobrecupoDTO();
		dto.setIdSobrecupo(s.getIdSobrecupo());
		dto.setPorcentajeSobrecupo(s.getPorcentajeSobrecupo());
		dto.setValorMaximo(s.getValorMaximo());
		if (s.getPareja() != null) {
			dto.setIdPareja(s.getPareja().getIdPareja());
		}
		return dto;
	}
}