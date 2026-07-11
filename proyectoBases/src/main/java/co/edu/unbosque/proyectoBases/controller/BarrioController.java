package co.edu.unbosque.proyectoBases.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.proyectoBases.dto.BarrioDTO;
import co.edu.unbosque.proyectoBases.service.BarrioService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Barrio" })
public class BarrioController {

	@Autowired
	private BarrioService barrioService;

	@GetMapping(path = { "/ObtenerBarrios" })
	public ResponseEntity<List<BarrioDTO>> obtenerBarrios() {
		return ResponseEntity.ok(barrioService.obtenerTodos());
	}

	@GetMapping(path = { "/ObtenerBarrio" })
	public ResponseEntity<BarrioDTO> obtenerBarrio(@RequestParam int idBarrio) {
		return ResponseEntity.ok(barrioService.obtenerPorId(idBarrio));
	}
}