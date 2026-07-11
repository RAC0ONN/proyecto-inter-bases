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
import co.edu.unbosque.proyectoBases.dto.CompraProductoDTO;
import co.edu.unbosque.proyectoBases.service.CompraProductoService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/CompraProducto" })
public class CompraProductoController {

	@Autowired
	private CompraProductoService compraProductoService;

	@PostMapping(path = { "/CrearCompraProducto" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearCompraProducto(@RequestBody CompraProductoDTO dto) {
		compraProductoService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Producto agregado a la compra");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerCompraProductos" })
	public ResponseEntity<List<CompraProductoDTO>> obtenerCompraProductos() {
		return ResponseEntity.ok(compraProductoService.obtenerTodas());
	}

	@GetMapping(path = { "/ObtenerCompraProducto" })
	public ResponseEntity<CompraProductoDTO> obtenerCompraProducto(@RequestParam int idCompraProducto) {
		return ResponseEntity.ok(compraProductoService.obtenerPorId(idCompraProducto));
	}

	@GetMapping(path = { "/ObtenerPorCompra" })
	public ResponseEntity<List<CompraProductoDTO>> obtenerPorCompra(@RequestParam int idCompra) {
		return ResponseEntity.ok(compraProductoService.obtenerPorCompra(idCompra));
	}

	@DeleteMapping(path = { "/BorrarCompraProducto" })
	public ResponseEntity<Map<String, Object>> borrarCompraProducto(@RequestParam int idCompraProducto) {
		compraProductoService.eliminar(idCompraProducto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Registro de producto comprado eliminado");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}