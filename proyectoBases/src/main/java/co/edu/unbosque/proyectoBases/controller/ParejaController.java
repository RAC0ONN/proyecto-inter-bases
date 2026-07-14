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
import co.edu.unbosque.proyectoBases.dto.ParejaDTO;
import co.edu.unbosque.proyectoBases.service.ParejaService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Pareja" })
public class ParejaController {

	@Autowired
	private ParejaService parejaService;

	@PostMapping(path = { "/CrearPareja" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> crearPareja(@RequestBody ParejaDTO dto) {
		parejaService.crear(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Pareja creada");
		body.put("status", HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	@PutMapping(path = { "/ActualizarPareja" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> actualizarPareja(@RequestBody ParejaDTO dto) {
		parejaService.actualizar(dto);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Pareja actualizada");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	@GetMapping(path = { "/ObtenerParejas" })
	public ResponseEntity<List<ParejaDTO>> obtenerParejas() {
		return ResponseEntity.ok(parejaService.obtenerTodas());
	}

	
	
	@GetMapping(path = { "/ObtenerPareja" })
	public ResponseEntity<ParejaDTO> obtenerPareja(@RequestParam int idPareja) {
		return ResponseEntity.ok(parejaService.obtenerPorId(idPareja));
	}

	@GetMapping(path = { "/ObtenerPorCliente" })
	public ResponseEntity<List<ParejaDTO>> obtenerPorCliente(@RequestParam int idCliente) {
		return ResponseEntity.ok(parejaService.obtenerPorCliente(idCliente));
	}

	@GetMapping(path = { "/ObtenerCupoConsumido" })
	public ResponseEntity<Map<String, Object>> obtenerCupoConsumido(@RequestParam int idCliente) {
		double cupoConsumido = parejaService.obtenerCupoConsumido(idCliente);
		Map<String, Object> body = new HashMap<>();
		body.put("idCliente", idCliente);
		body.put("cupoConsumido", cupoConsumido);
		return ResponseEntity.ok(body);
	}

	@DeleteMapping(path = { "/BorrarPareja" })
	public ResponseEntity<Map<String, Object>> borrarPareja(@RequestParam int idPareja) {
		parejaService.eliminar(idPareja);
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Pareja eliminada");
		body.put("status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
	@PostMapping(path = { "/Autenticar" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> autenticarPareja(@RequestBody Map<String, String> credenciales) {
	    String username = credenciales.get("username");
	    String password = credenciales.get("password");
	    
	    ParejaDTO dto = parejaService.autenticar(username, password);
	    
	    Map<String, Object> body = new HashMap<>();
	    body.put("message", "Autenticación exitosa");
	    body.put("status", HttpStatus.OK.value());
	    body.put("rol", "pareja");
	    body.put("datos", dto);
	    
	    return ResponseEntity.ok(body);
	}
}