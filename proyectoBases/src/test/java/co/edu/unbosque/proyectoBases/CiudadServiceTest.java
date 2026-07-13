package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.CiudadDTO;
import co.edu.unbosque.proyectoBases.entity.Ciudad;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.CiudadRepository;
import co.edu.unbosque.proyectoBases.service.CiudadService;

public class CiudadServiceTest {

	private CiudadRepository ciudadRepository;
	private CiudadService ciudadService;

	@BeforeEach
	public void setUp() {

		ciudadRepository = Mockito.mock(CiudadRepository.class);
		ciudadService = new CiudadService();
		ReflectionTestUtils.setField(ciudadService, "ciudadRepository", ciudadRepository);
		
	}

	@Test
	public void obtenerTodas() {

		Ciudad ciudad = new Ciudad();
		ciudad.setIdCiudad(1);
		ciudad.setNombre("Bogotá");

		ArrayList<Ciudad> lista = new ArrayList<>();
		lista.add(ciudad);

		when(ciudadRepository.obtenerCiudades()).thenReturn(lista);

		ArrayList<CiudadDTO> resultado = ciudadService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdCiudad());
		assertEquals("Bogotá", resultado.get(0).getNombre());
	}

	@Test
	public void obtenerTodas_SinDatos() {

		when(ciudadRepository.obtenerCiudades()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> ciudadService.obtenerTodas());
	}

	@Test
	public void obtenerPorId() {

		Ciudad ciudad = new Ciudad();
		ciudad.setIdCiudad(7);
		ciudad.setNombre("Medellín");

		when(ciudadRepository.existePorId(7)).thenReturn(ciudad);

		CiudadDTO dto = ciudadService.obtenerPorId(7);

		assertEquals(7, dto.getIdCiudad());
		assertEquals("Medellín", dto.getNombre());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(ciudadRepository.existePorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> ciudadService.obtenerPorId(100));
	}
}
