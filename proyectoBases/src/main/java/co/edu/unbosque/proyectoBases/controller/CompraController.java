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
import co.edu.unbosque.proyectoBases.dto.CompraDTO;
import co.edu.unbosque.proyectoBases.service.CompraService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Compra" })
public class CompraController {

	@Autowired
	private CompraService compraService;

	@PostMapping(path = { "/CrearCompra" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearCompra(@RequestBody CompraDTO dto) {
		int idCompra = compraService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Compra creada");
		body.put("status", HttpStatus.CREATED.value());
		body.put("idCompra", idCompra);
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@GetMapping(path = { "/ObtenerCompras" })
	public ResponseEntity<List<CompraDTO>> obtenerCompras() {
		return ResponseEntity.ok(compraService.obtenerTodas());
	}

	@GetMapping(path = { "/ObtenerPorPareja" })
	public ResponseEntity<List<CompraDTO>> obtenerPorPareja(@RequestParam int idPareja) {
		return ResponseEntity.ok(compraService.obtenerPorPareja(idPareja));
	}
 
	@GetMapping(path = { "/ObtenerCompra" })
	public ResponseEntity<CompraDTO> obtenerCompra(@RequestParam int idCompra) {
		return ResponseEntity.ok(compraService.obtenerPorId(idCompra));
	}

	@DeleteMapping(path = { "/BorrarCompra" })
	public ResponseEntity<Map<String, Object>> borrarCompra(@RequestParam int idCompra) {
		compraService.eliminar(idCompra);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Compra eliminada");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
}