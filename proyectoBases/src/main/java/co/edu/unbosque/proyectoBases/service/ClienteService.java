package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.ClienteDTO;
import co.edu.unbosque.proyectoBases.entity.Cliente;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void crear(ClienteDTO dto) {
		clienteRepository.crearCliente( dto.getPrimerNombre(), dto.getSegundoNombre(),
				dto.getPrimerApellido(), dto.getSegundoApellido(), dto.getNombreUsuario(), dto.getContraseniaUsuario(),
				dto.getCorreoElectronico(), dto.getCupoTotal());
	}

	public ArrayList<ClienteDTO> obtenerTodos() {
		List<Cliente> entidades = clienteRepository.obtenerTodos();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay clientes registrados");
		}
		ArrayList<ClienteDTO> resultado = new ArrayList<>();
		for (Cliente c : entidades) {
			resultado.add(mapear(c));
		}
		return resultado;
	}

	public ClienteDTO obtenerPorId(int idCliente) {
		Cliente entidad = clienteRepository.obtenerPorId(idCliente);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe un cliente con ese id");
		}
		return mapear(entidad);
	}

	public void eliminar(int idCliente) {
		if (clienteRepository.obtenerPorId(idCliente) == null) {
			throw new RecursoNoExistenteException("No existe un cliente con ese id");
		}
		clienteRepository.eliminarCliente(idCliente);
	}

	private ClienteDTO mapear(Cliente c) {
		ClienteDTO dto = new ClienteDTO();
		dto.setIdCliente(c.getIdCliente());
		dto.setPrimerNombre(c.getPrimerNombre());
		dto.setSegundoNombre(c.getSegundoNombre());
		dto.setPrimerApellido(c.getPrimerApellido());
		dto.setSegundoApellido(c.getSegundoApellido());
		dto.setNombreUsuario(c.getNombreUsuario());
		dto.setContraseniaUsuario(c.getContraseniaUsuario());
		dto.setCorreoElectronico(c.getCorreoElectronico());
		dto.setCupoTotal(c.getCupoTotal());
		return dto;
	}
	
	public ClienteDTO autenticar(String nombreUsuario, String contrasenia) {
		List<Cliente> entidades = clienteRepository.obtenerTodos();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay clientes registrados en el sistema");
		}
		
		for (Cliente c : entidades) {
			if (c.getNombreUsuario() != null && c.getNombreUsuario().equals(nombreUsuario)) {
				if (c.getContraseniaUsuario() != null && c.getContraseniaUsuario().equals(contrasenia)) {
					return mapear(c);
				} else {
					throw new RecursoNoExistenteException("Contraseña incorrecta");
				}
			}
		}
		throw new RecursoNoExistenteException("El usuario de cliente no existe");
	}
	
}