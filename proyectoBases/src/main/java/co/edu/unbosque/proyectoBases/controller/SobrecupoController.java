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
import co.edu.unbosque.proyectoBases.dto.SobrecupoDTO;
import co.edu.unbosque.proyectoBases.service.SobrecupoService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Sobrecupo" })
public class SobrecupoController {

	@Autowired
	private SobrecupoService sobrecupoService;

	@PostMapping(path = { "/CrearSobrecupo" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearSobrecupo(@RequestBody SobrecupoDTO dto) {
		sobrecupoService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Sobrecupo creado");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerSobrecupos" })
	public ResponseEntity<List<SobrecupoDTO>> obtenerSobrecupos() {
		return ResponseEntity.ok(sobrecupoService.obtenerTodas());
	}

	@GetMapping(path = { "/ObtenerSobrecupo" })
	public ResponseEntity<SobrecupoDTO> obtenerSobrecupo(@RequestParam int idSobrecupo) {
		return ResponseEntity.ok(sobrecupoService.obtenerPorId(idSobrecupo));
	}

	@GetMapping(path = { "/ObtenerPorPareja" })
	public ResponseEntity<List<SobrecupoDTO>> obtenerPorPareja(@RequestParam int idPareja) {
		return ResponseEntity.ok(sobrecupoService.obtenerPorPareja(idPareja));
	}

	@DeleteMapping(path = { "/BorrarSobrecupo" })
	public ResponseEntity<Map<String, Object>> borrarSobrecupo(@RequestParam int idSobrecupo) {
		sobrecupoService.eliminar(idSobrecupo);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Sobrecupo eliminado");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}