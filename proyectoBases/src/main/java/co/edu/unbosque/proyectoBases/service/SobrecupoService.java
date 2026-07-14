package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.SobrecupoDTO;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.entity.Sobrecupo;
import co.edu.unbosque.proyectoBases.entity.Supervisor;
import co.edu.unbosque.proyectoBases.exceptions.RecursoEstadoInvalidoException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.AlmacenRepository;
import co.edu.unbosque.proyectoBases.repository.ParejaRepository;
import co.edu.unbosque.proyectoBases.repository.SobrecupoRepository;
import co.edu.unbosque.proyectoBases.repository.SupervisorRepository;

@Service
public class SobrecupoService {

	public static final String PENDIENTE = "PENDIENTE";
	public static final String ESPERANDO_CLIENTE = "ESPERANDO_CLIENTE";
	public static final String APROBADO = "APROBADO";
	public static final String RECHAZADO = "RECHAZADO";
	
	@Autowired
	private SobrecupoRepository sobrecupoRepository;
 
	@Autowired
	private ParejaRepository parejaRepository;
 
	@Autowired
	private AlmacenRepository almacenRepository;
 
	@Autowired
	private SupervisorRepository supervisorRepository;
	
	

	/*public void crear(SobrecupoDTO dto) {
		sobrecupoRepository.crearSobrecupo(dto.getIdSobrecupo(), dto.getPorcentajeSobrecupo(), dto.getValorMaximo(),
				dto.getIdPareja());
	} */
	
	
	public void solicitar(SobrecupoDTO dto) {
	   
	    Optional<Supervisor> supervisor = supervisorRepository.obtenerPorAlmacen(dto.getIdAlmacen());
	    if (supervisor.isEmpty()) {
	        throw new RecursoNoExistenteException("El almacén seleccionado no tiene un supervisor asignado");
	    }

	      sobrecupoRepository.crearSobrecupo(
	        supervisor.get().getIdSupervisor(), 
	        dto.getIdPareja(),
	        PENDIENTE, 
	        dto.getMontoSobrecupo()
	    );
	}
	
	public void escalarACliente(int idSobrecupo, boolean escalar) {
		Sobrecupo sobrecupo = sobrecupoRepository.obtenerPorId(idSobrecupo);
		if (sobrecupo == null) {
			throw new RecursoNoExistenteException("No existe una solicitud de sobrecupo con ese id");
		}
		if (!PENDIENTE.equals(sobrecupo.getEstadoSobrecupo())) {
			throw new RecursoEstadoInvalidoException(
					"Esta solicitud ya fue procesada (estado actual: " + sobrecupo.getEstadoSobrecupo() + ")");
		}

		String nuevoEstado = escalar ? ESPERANDO_CLIENTE : RECHAZADO;
		sobrecupoRepository.actualizarEstado(nuevoEstado, idSobrecupo);
	}

	
	public void responderCliente(int idSobrecupo, boolean aprobado) {
		Sobrecupo sobrecupo = sobrecupoRepository.obtenerPorId(idSobrecupo);
		if (sobrecupo == null) {
			throw new RecursoNoExistenteException("No existe una solicitud de sobrecupo con ese id");
		}
		if (!ESPERANDO_CLIENTE.equals(sobrecupo.getEstadoSobrecupo())) {
			throw new RecursoEstadoInvalidoException(
					"Esta solicitud no está esperando respuesta del cliente (estado actual: "
							+ sobrecupo.getEstadoSobrecupo() + ")");
		}

		String nuevoEstado = aprobado ? APROBADO : RECHAZADO;
		sobrecupoRepository.actualizarEstado(nuevoEstado, idSobrecupo);
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

	public ArrayList<SobrecupoDTO> obtenerPorSupervisor(int idSupervisor) {
		ArrayList<Sobrecupo> entidades = sobrecupoRepository.obtenerPorSupervisor(idSupervisor);
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay solicitudes de sobrecupo para este supervisor");
		}
		ArrayList<SobrecupoDTO> resultado = new ArrayList<>();
		for (Sobrecupo s : entidades) {
			resultado.add(mapear(s));
		}
		return resultado;
	}

	/** Todas las solicitudes de las parejas de un cliente (para su dashboard). */
	public ArrayList<SobrecupoDTO> obtenerPorCliente(int idCliente) {
		ArrayList<Sobrecupo> entidades = sobrecupoRepository.obtenerPorCliente(idCliente);
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay solicitudes de sobrecupo para las parejas de este cliente");
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
		dto.setEstadoSobrecupo(s.getEstadoSobrecupo());
		dto.setMontoSobrecupo(s.getMontoSobrecupo());
		if (s.getSupervisor() != null) {
			dto.setIdSupervisor(s.getSupervisor().getIdSupervisor());
		}
		if (s.getPareja() != null) {
			dto.setIdPareja(s.getPareja().getIdPareja());
		}
		return dto;
	}
	
	
}