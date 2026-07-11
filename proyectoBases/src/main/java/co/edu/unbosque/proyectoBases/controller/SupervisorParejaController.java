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
import co.edu.unbosque.proyectoBases.dto.SupervisorParejaDTO;
import co.edu.unbosque.proyectoBases.service.SupervisorParejaService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/SupervisorPareja" })
public class SupervisorParejaController {

	@Autowired
	private SupervisorParejaService supervisorParejaService;

	@PostMapping(path = { "/CrearSupervisorPareja" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearSupervisorPareja(@RequestBody SupervisorParejaDTO dto) {
		supervisorParejaService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Asignación supervisor-pareja creada");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerSupervisorParejas" })
	public ResponseEntity<List<SupervisorParejaDTO>> obtenerSupervisorParejas() {
		return ResponseEntity.ok(supervisorParejaService.obtenerTodas());
	}

	@GetMapping(path = { "/ObtenerSupervisorPareja" })
	public ResponseEntity<SupervisorParejaDTO> obtenerSupervisorPareja(@RequestParam int idSupervisorPareja) {
		return ResponseEntity.ok(supervisorParejaService.obtenerPorId(idSupervisorPareja));
	}

	@GetMapping(path = { "/ObtenerPorSupervisor" })
	public ResponseEntity<List<SupervisorParejaDTO>> obtenerPorSupervisor(@RequestParam int idSupervisor) {
		return ResponseEntity.ok(supervisorParejaService.obtenerPorSupervisor(idSupervisor));
	}

	@DeleteMapping(path = { "/BorrarSupervisorPareja" })
	public ResponseEntity<Map<String, Object>> borrarSupervisorPareja(@RequestParam int idSupervisorPareja) {
		supervisorParejaService.eliminar(idSupervisorPareja);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Asignación eliminada con éxito");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}