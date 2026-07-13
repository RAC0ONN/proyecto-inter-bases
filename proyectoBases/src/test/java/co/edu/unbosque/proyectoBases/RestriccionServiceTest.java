package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.RestriccionDTO;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.entity.Restriccion;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.RestriccionRepository;
import co.edu.unbosque.proyectoBases.service.RestriccionService;

public class RestriccionServiceTest {

	private RestriccionRepository restriccionRepository;
	private RestriccionService restriccionService;

	@BeforeEach
	public void setUp() {

		restriccionRepository = Mockito.mock(RestriccionRepository.class);
		restriccionService = new RestriccionService();
		ReflectionTestUtils.setField(restriccionService, "restriccionRepository", restriccionRepository);
	}

	@Test
	public void crearRestriccion() {

		RestriccionDTO dto = new RestriccionDTO();
		dto.setIdRestriccion(1);
		dto.setDiaSemana("LUNES");
		dto.setHoraInicio(LocalTime.of(8, 0));
		dto.setHoraFin(LocalTime.of(12, 0));
		dto.setIdPareja(5);

		restriccionService.crear(dto);

		verify(restriccionRepository, times(1)).crearRestriccion(1, "LUNES", LocalTime.of(8, 0), LocalTime.of(12, 0),
				5);
	}

	@Test
	public void obtenerTodas() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(5);

		Restriccion restriccion = new Restriccion();
		restriccion.setIdRestriccion(1);
		restriccion.setDiaSemana("MARTES");
		restriccion.setHoraInicio(LocalTime.of(9, 0));
		restriccion.setHoraFin(LocalTime.of(13, 0));
		restriccion.setPareja(pareja);

		ArrayList<Restriccion> lista = new ArrayList<>();
		lista.add(restriccion);

		when(restriccionRepository.obtenerTodas()).thenReturn(lista);

		ArrayList<RestriccionDTO> resultado = restriccionService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdRestriccion());
		assertEquals("MARTES", resultado.get(0).getDiaSemana());
		assertEquals(LocalTime.of(9, 0), resultado.get(0).getHoraInicio());
		assertEquals(LocalTime.of(13, 0), resultado.get(0).getHoraFin());
		assertEquals(5, resultado.get(0).getIdPareja());
	}

	@Test
	public void obtenerTodas_SinDatos() {

		when(restriccionRepository.obtenerTodas()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> restriccionService.obtenerTodas());
	}

	@Test
	public void obtenerPorId() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(2);

		Restriccion restriccion = new Restriccion();
		restriccion.setIdRestriccion(10);
		restriccion.setDiaSemana("VIERNES");
		restriccion.setHoraInicio(LocalTime.of(14, 0));
		restriccion.setHoraFin(LocalTime.of(18, 0));
		restriccion.setPareja(pareja);

		when(restriccionRepository.obtenerPorId(10)).thenReturn(restriccion);

		RestriccionDTO dto = restriccionService.obtenerPorId(10);

		assertEquals(10, dto.getIdRestriccion());
		assertEquals("VIERNES", dto.getDiaSemana());
		assertEquals(LocalTime.of(14, 0), dto.getHoraInicio());
		assertEquals(LocalTime.of(18, 0), dto.getHoraFin());
		assertEquals(2, dto.getIdPareja());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(restriccionRepository.obtenerPorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> restriccionService.obtenerPorId(100));
	}

	@Test
	public void obtenerPorPareja() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(3);

		Restriccion restriccion = new Restriccion();
		restriccion.setIdRestriccion(15);
		restriccion.setDiaSemana("SABADO");
		restriccion.setHoraInicio(LocalTime.of(10, 0));
		restriccion.setHoraFin(LocalTime.of(15, 0));
		restriccion.setPareja(pareja);

		ArrayList<Restriccion> lista = new ArrayList<>();
		lista.add(restriccion);

		when(restriccionRepository.obtenerPorPareja(3)).thenReturn(lista);

		ArrayList<RestriccionDTO> resultado = restriccionService.obtenerPorPareja(3);

		assertEquals(1, resultado.size());
		assertEquals(15, resultado.get(0).getIdRestriccion());
		assertEquals("SABADO", resultado.get(0).getDiaSemana());
		assertEquals(3, resultado.get(0).getIdPareja());
	}

	@Test
	public void obtenerPorPareja_SinDatos() {

		when(restriccionRepository.obtenerPorPareja(3)).thenReturn(null);

		ArrayList<RestriccionDTO> resultado = restriccionService.obtenerPorPareja(3);

		assertTrue(resultado.isEmpty());
	}

	@Test
	public void eliminarRestriccion() {

		Restriccion restriccion = new Restriccion();
		restriccion.setIdRestriccion(1);

		when(restriccionRepository.obtenerPorId(1)).thenReturn(restriccion);

		restriccionService.eliminar(1);

		verify(restriccionRepository, times(1)).eliminarRestriccion(1);
	}

	@Test
	public void eliminarRestriccion_NoExiste() {

		when(restriccionRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> restriccionService.eliminar(1));
	}

}
