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
import co.edu.unbosque.proyectoBases.dto.AlmacenDTO;
import co.edu.unbosque.proyectoBases.service.AlmacenService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Almacen" })
public class AlmacenController {

	@Autowired
	private AlmacenService almacenService;

	@PostMapping(path = { "/CrearAlmacen" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearAlmacen(@RequestBody AlmacenDTO dto) {
		almacenService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Almacén creado");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@PutMapping(path = { "/ActualizarAlmacen" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> actualizarAlmacen(@RequestBody AlmacenDTO dto) {
		almacenService.actualizar(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Almacén actualizado");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	@GetMapping(path = { "/ObtenerAlmacenes" })
	public ResponseEntity<List<AlmacenDTO>> obtenerAlmacenes() {
		return ResponseEntity.ok(almacenService.obtenerTodos());
	}

	@GetMapping(path = { "/ObtenerAlmacen" })
	public ResponseEntity<AlmacenDTO> obtenerAlmacen(@RequestParam int idAlmacen) {
		return ResponseEntity.ok(almacenService.obtenerPorId(idAlmacen));
	}

	@DeleteMapping(path = { "/BorrarAlmacen" })
	public ResponseEntity<Map<String, Object>> borrarAlmacen(@RequestParam int idAlmacen) {
		almacenService.eliminar(idAlmacen);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Almacén eliminado");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}