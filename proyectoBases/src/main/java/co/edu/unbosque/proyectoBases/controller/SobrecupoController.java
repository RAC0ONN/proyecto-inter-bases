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
import org.springframework.web.bind.annotation.PutMapping;
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

	@PostMapping(path = { "/Solicitar" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> solicitar(@RequestBody SobrecupoDTO dto) {
		sobrecupoService.solicitar(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Solicitud de sobrecupo enviada al supervisor del almacén");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerPorSupervisor" })
	public ResponseEntity<List<SobrecupoDTO>> obtenerPorSupervisor(@RequestParam int idSupervisor) {
		return ResponseEntity.ok(sobrecupoService.obtenerPorSupervisor(idSupervisor));
	}

	@GetMapping(path = { "/ObtenerPorCliente" })
	public ResponseEntity<List<SobrecupoDTO>> obtenerPorCliente(@RequestParam int idCliente) {
		return ResponseEntity.ok(sobrecupoService.obtenerPorCliente(idCliente));
	}

	@PutMapping(path = { "/Escalar" })
	public ResponseEntity<Map<String, Object>> escalar(@RequestParam int idSobrecupo, @RequestParam boolean escalar) {
		sobrecupoService.escalarACliente(idSobrecupo, escalar);
		Map<String, Object> body = new HashMap<>();
		body.put("message", escalar ? "Solicitud enviada al cliente titular para su autorización"
				: "Solicitud rechazada por el supervisor");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.ok(body);
	}

	@PutMapping(path = { "/ResponderCliente" })
	public ResponseEntity<Map<String, Object>> responderCliente(@RequestParam int idSobrecupo,
			@RequestParam boolean aprobado) {
		sobrecupoService.responderCliente(idSobrecupo, aprobado);
		Map<String, Object> body = new HashMap<>();
		body.put("message", aprobado ? "Sobrecupo aprobado" : "Sobrecupo rechazado por el cliente");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.ok(body);
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