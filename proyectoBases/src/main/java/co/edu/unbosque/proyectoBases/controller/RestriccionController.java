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
import co.edu.unbosque.proyectoBases.dto.RestriccionDTO;
import co.edu.unbosque.proyectoBases.service.RestriccionService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Restriccion" })
public class RestriccionController {

	@Autowired
	private RestriccionService restriccionService;

	@PostMapping(path = { "/CrearRestriccion" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearRestriccion(@RequestBody RestriccionDTO dto) {
		restriccionService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Restricción creada");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerRestricciones" })
	public ResponseEntity<List<RestriccionDTO>> obtenerRestricciones() {
		return ResponseEntity.ok(restriccionService.obtenerTodas());
	}

	@GetMapping(path = { "/ObtenerRestriccion" })
	public ResponseEntity<RestriccionDTO> obtenerRestriccion(@RequestParam int idRestriccion) {
		return ResponseEntity.ok(restriccionService.obtenerPorId(idRestriccion));
	}
	
	@GetMapping(path = { "/ObtenerPorPareja" })
	public ResponseEntity<List<RestriccionDTO>> obtenerPorPareja(@RequestParam int idPareja) {
		return ResponseEntity.ok(restriccionService.obtenerPorPareja(idPareja));
	}

	@DeleteMapping(path = { "/BorrarRestriccion" })
	public ResponseEntity<Map<String, Object>> borrarRestriccion(@RequestParam int idRestriccion) {
		restriccionService.eliminar(idRestriccion);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Restricción eliminada");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}