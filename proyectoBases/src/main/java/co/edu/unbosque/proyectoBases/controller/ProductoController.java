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
import co.edu.unbosque.proyectoBases.dto.ProductoDTO;
import co.edu.unbosque.proyectoBases.service.ProductoService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Producto" })
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@PostMapping(path = { "/CrearProducto" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearProducto(@RequestBody ProductoDTO dto) {
		productoService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Producto creado");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@PutMapping(path = { "/ActualizarProducto" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> actualizarProducto(@RequestBody ProductoDTO dto) {
		productoService.actualizar(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Producto actualizado");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	@GetMapping(path = { "/ObtenerProductos" })
	public ResponseEntity<List<ProductoDTO>> obtenerProductos() {
		return ResponseEntity.ok(productoService.obtenerTodos());
	}

	@GetMapping(path = { "/ObtenerProducto" })
	public ResponseEntity<ProductoDTO> obtenerProducto(@RequestParam int idProducto) {
		return ResponseEntity.ok(productoService.obtenerPorId(idProducto));
	}

	@DeleteMapping(path = { "/BorrarProducto" })
	public ResponseEntity<Map<String, Object>> borrarProducto(@RequestParam int idProducto) {
		productoService.eliminar(idProducto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Producto eliminado");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}