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
import co.edu.unbosque.proyectoBases.dto.DireccionDTO;
import co.edu.unbosque.proyectoBases.service.DireccionService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Direccion" })
public class DireccionController {

	@Autowired
	private DireccionService direccionService;

	@PostMapping(path = { "/CrearDireccion" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearDireccion(@RequestBody DireccionDTO dto) {
		direccionService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Dirección creada");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerDirecciones" })
	public ResponseEntity<List<DireccionDTO>> obtenerDirecciones() {
		return ResponseEntity.ok(direccionService.obtenerTodas());
	}

	@GetMapping(path = { "/ObtenerDireccion" })
	public ResponseEntity<DireccionDTO> obtenerDireccion(@RequestParam int idDireccion) {
		return ResponseEntity.ok(direccionService.obtenerPorId(idDireccion));
	}

	@DeleteMapping(path = { "/BorrarDireccion" })
	public ResponseEntity<Map<String, Object>> borrarDireccion(@RequestParam int idDireccion) {
		direccionService.eliminar(idDireccion);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Dirección eliminada");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}