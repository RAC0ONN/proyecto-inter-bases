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
import co.edu.unbosque.proyectoBases.dto.SupervisorDTO;
import co.edu.unbosque.proyectoBases.service.SupervisorService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Supervisor" })
public class SupervisorController {

	@Autowired
	private SupervisorService supervisorService;

	@PostMapping(path = { "/CrearSupervisor" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearSupervisor(@RequestBody SupervisorDTO dto) {
		supervisorService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Supervisor creado");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerSupervisores" })
	public ResponseEntity<List<SupervisorDTO>> obtenerSupervisores() {
		return ResponseEntity.ok(supervisorService.obtenerTodos());
	}

	@GetMapping(path = { "/ObtenerSupervisor" })
	public ResponseEntity<SupervisorDTO> obtenerSupervisor(@RequestParam int idSupervisor) {
		return ResponseEntity.ok(supervisorService.obtenerPorId(idSupervisor));
	}

	@DeleteMapping(path = { "/BorrarSupervisor" })
	public ResponseEntity<Map<String, Object>> borrarSupervisor(@RequestParam int idSupervisor) {
		supervisorService.eliminar(idSupervisor);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Supervisor eliminado");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
	@PostMapping(path = { "/Autenticar" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> autenticarSupervisor(@RequestBody Map<String, String> credenciales) {
		String usuario = credenciales.get("username");
		String contrasenia = credenciales.get("password");
		
		SupervisorDTO dto = supervisorService.autenticar(usuario, contrasenia);
		
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.OK.value());
		body.put("rol", "SUPERVISOR");
		body.put("datos", dto); 
		
		return ResponseEntity.ok(body);
	}
}