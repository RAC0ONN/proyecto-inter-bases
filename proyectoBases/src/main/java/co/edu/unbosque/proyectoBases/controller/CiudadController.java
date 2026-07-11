package co.edu.unbosque.proyectoBases.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.proyectoBases.dto.CiudadDTO;
import co.edu.unbosque.proyectoBases.service.CiudadService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/Ciudad" })
public class CiudadController {

	@Autowired
	private CiudadService ciudadService;

	@GetMapping(path = { "/ObtenerCiudades" })
	public ResponseEntity<List<CiudadDTO>> obtenerCiudades() {
		return ResponseEntity.ok(ciudadService.obtenerTodas());
	}

	@GetMapping(path = { "/ObtenerCiudad" })
	public ResponseEntity<CiudadDTO> obtenerCiudad(@RequestParam int idCiudad) {
		return ResponseEntity.ok(ciudadService.obtenerPorId(idCiudad));
	}
}