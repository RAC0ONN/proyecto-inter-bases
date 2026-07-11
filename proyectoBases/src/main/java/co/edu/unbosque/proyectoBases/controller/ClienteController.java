package co.edu.unbosque.proyectoBases.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.proyectoBases.dto.ClienteDTO;
import co.edu.unbosque.proyectoBases.service.ClienteService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Cliente" })
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping(path = { "/CrearCliente" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearCliente(@RequestBody ClienteDTO dto) {
		clienteService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Cliente creado");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerClientes" })
	public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
		return ResponseEntity.ok(clienteService.obtenerTodos());
	}

	@GetMapping(path = { "/ObtenerCliente" })
	public ResponseEntity<ClienteDTO> obtenerCliente(@RequestParam int idCliente) {
		return ResponseEntity.ok(clienteService.obtenerPorId(idCliente));
	}

	@DeleteMapping(path = { "/BorrarCliente" })
	public ResponseEntity<Map<String, Object>> borrarCliente(@RequestParam int idCliente) {
		clienteService.eliminar(idCliente);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Cliente eliminado");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
	
	@PostMapping(path = { "/Autenticar" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> autenticarCliente(@RequestBody Map<String, String> credenciales) {
		String usuario = credenciales.get("username");
		String contrasenia = credenciales.get("password");
		
		ClienteDTO dto = clienteService.autenticar(usuario, contrasenia);
		
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.OK.value());
		body.put("rol", "CLIENTE");
		body.put("datos", dto); 
		return ResponseEntity.ok(body);
	}
	
	
	
}