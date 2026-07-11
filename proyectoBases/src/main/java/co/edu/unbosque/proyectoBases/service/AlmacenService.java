package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.AlmacenDTO;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.AlmacenRepository;

@Service
public class AlmacenService {

	@Autowired
	private AlmacenRepository almacenRepository;

	public void crear(AlmacenDTO dto) {
		almacenRepository.crearAlmacen(dto.getIdAlmacen(), dto.getNombre(), dto.getIdDireccion());
	}

	public void actualizar(AlmacenDTO dto) {
		if (almacenRepository.obtenerPorId(dto.getIdAlmacen()) == null) {
			throw new RecursoNoExistenteException("Error, el almacén no existe");
		}
		almacenRepository.actualizarAlmacen(dto.getNombre(), dto.getIdDireccion(), dto.getIdAlmacen());
	}

	public ArrayList<AlmacenDTO> obtenerTodos() {
		List<Almacen> entidades = almacenRepository.obtenerTodos();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay almacenes registrados");
		}
		ArrayList<AlmacenDTO> resultado = new ArrayList<>();
		for (Almacen a : entidades) {
			resultado.add(mapear(a));
		}
		return resultado;
	}

	public AlmacenDTO obtenerPorId(int idAlmacen) {
		Almacen entidad = almacenRepository.obtenerPorId(idAlmacen);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe un almacén con ese id");
		}
		return mapear(entidad);
	}

	public void eliminar(int idAlmacen) {
		if (almacenRepository.obtenerPorId(idAlmacen) == null) {
			throw new RecursoNoExistenteException("No existe un almacén con ese id");
		}
		almacenRepository.eliminarAlmacen(idAlmacen);
	}

	private AlmacenDTO mapear(Almacen a) {
		AlmacenDTO dto = new AlmacenDTO();
		dto.setIdAlmacen(a.getIdAlmacen());
		dto.setNombre(a.getNombre());
		if (a.getDireccion() != null) {
			dto.setIdDireccion(a.getDireccion().getIdDireccion());
		}
		return dto;
	}
}