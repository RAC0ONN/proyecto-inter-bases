package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.BarrioDTO;
import co.edu.unbosque.proyectoBases.entity.Barrio;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.BarrioRepository;
import co.edu.unbosque.proyectoBases.service.BarrioService;

public class BarrioServiceTest {

	private BarrioRepository barrioRepository;
	private BarrioService barrioService;

	@BeforeEach
	public void setUp() {
		
		barrioRepository = Mockito.mock(BarrioRepository.class);
		barrioService = new BarrioService();
		ReflectionTestUtils.setField(barrioService, "barrioRepository", barrioRepository);

	}

	@Test
	public void obtenerTodos() {

		Barrio barrio = new Barrio();
		barrio.setIdBarrio(1);
		barrio.setNombre("Cedritos");

		ArrayList<Barrio> lista = new ArrayList<>();
		lista.add(barrio);

		when(barrioRepository.obtenerBarrios()).thenReturn(lista);

		ArrayList<BarrioDTO> resultado = barrioService.obtenerTodos();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdBarrio());
		assertEquals("Cedritos", resultado.get(0).getNombre());
	}

	@Test
	public void obtenerTodos_SinDatos() {

		when(barrioRepository.obtenerBarrios()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> barrioService.obtenerTodos());
	}

	@Test
	public void obtenerPorId() {

		Barrio barrio = new Barrio();
		barrio.setIdBarrio(5);
		barrio.setNombre("Suba");

		when(barrioRepository.existePorId(5)).thenReturn(barrio);

		BarrioDTO dto = barrioService.obtenerPorId(5);

		assertEquals(5, dto.getIdBarrio());
		assertEquals("Suba", dto.getNombre());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(barrioRepository.existePorId(10)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> barrioService.obtenerPorId(10));
	}

}
