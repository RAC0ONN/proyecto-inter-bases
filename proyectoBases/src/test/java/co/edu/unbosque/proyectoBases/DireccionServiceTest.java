package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.DireccionDTO;
import co.edu.unbosque.proyectoBases.entity.Barrio;
import co.edu.unbosque.proyectoBases.entity.Ciudad;
import co.edu.unbosque.proyectoBases.entity.Direccion;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.DireccionRepository;
import co.edu.unbosque.proyectoBases.service.DireccionService;

public class DireccionServiceTest {

	private DireccionRepository direccionRepository;
	private DireccionService direccionService;

	@BeforeEach
	public void setUp() {

		direccionRepository = Mockito.mock(DireccionRepository.class);
		direccionService = new DireccionService();
		ReflectionTestUtils.setField(direccionService, "direccionRepository", direccionRepository);

	}

	@Test
	public void crearDireccion() {

		DireccionDTO dto = new DireccionDTO();
		dto.setIdDireccion(1);
		dto.setViaPrincipal("Calle");
		dto.setNumeroVia(80);
		dto.setLetraVia("A");
		dto.setSufijo("Bis");
		dto.setNumeroGenerador(45);
		dto.setPlaca(20);
		dto.setIdBarrio(5);
		dto.setIdCiudad(10);

		direccionService.crear(dto);

		verify(direccionRepository, times(1)).crearDireccion(1, "Calle", 80, "A", "Bis", 45, 20, 5, 10);
	}

	@Test
	public void obtenerTodas() {

		Barrio barrio = new Barrio();
		barrio.setIdBarrio(5);

		Ciudad ciudad = new Ciudad();
		ciudad.setIdCiudad(10);

		Direccion direccion = new Direccion();
		direccion.setIdDireccion(1);
		direccion.setViaPrincipal("Carrera");
		direccion.setNumeroVia(15);
		direccion.setLetraVia("B");
		direccion.setSufijo("Sur");
		direccion.setNumeroGenerador(30);
		direccion.setPlaca(25);
		direccion.setBarrio(barrio);
		direccion.setCiudad(ciudad);

		ArrayList<Direccion> lista = new ArrayList<>();
		lista.add(direccion);

		when(direccionRepository.obtenerTodas()).thenReturn(lista);

		ArrayList<DireccionDTO> resultado = direccionService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdDireccion());
		assertEquals("Carrera", resultado.get(0).getViaPrincipal());
		assertEquals(15, resultado.get(0).getNumeroVia());
		assertEquals("B", resultado.get(0).getLetraVia());
		assertEquals("Sur", resultado.get(0).getSufijo());
		assertEquals(30, resultado.get(0).getNumeroGenerador());
		assertEquals(25, resultado.get(0).getPlaca());
		assertEquals(5, resultado.get(0).getIdBarrio());
		assertEquals(10, resultado.get(0).getIdCiudad());
	}

	@Test
	public void obtenerTodas_SinDatos() {

		when(direccionRepository.obtenerTodas()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> direccionService.obtenerTodas());
	}

	@Test
	public void obtenerPorId() {

		Barrio barrio = new Barrio();
		barrio.setIdBarrio(2);

		Ciudad ciudad = new Ciudad();
		ciudad.setIdCiudad(3);

		Direccion direccion = new Direccion();
		direccion.setIdDireccion(15);
		direccion.setViaPrincipal("Diagonal");
		direccion.setNumeroVia(40);
		direccion.setLetraVia("C");
		direccion.setSufijo("Este");
		direccion.setNumeroGenerador(22);
		direccion.setPlaca(18);
		direccion.setBarrio(barrio);
		direccion.setCiudad(ciudad);

		when(direccionRepository.obtenerPorId(15)).thenReturn(direccion);

		DireccionDTO dto = direccionService.obtenerPorId(15);

		assertEquals(15, dto.getIdDireccion());
		assertEquals("Diagonal", dto.getViaPrincipal());
		assertEquals(40, dto.getNumeroVia());
		assertEquals("C", dto.getLetraVia());
		assertEquals("Este", dto.getSufijo());
		assertEquals(22, dto.getNumeroGenerador());
		assertEquals(18, dto.getPlaca());
		assertEquals(2, dto.getIdBarrio());
		assertEquals(3, dto.getIdCiudad());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(direccionRepository.obtenerPorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> direccionService.obtenerPorId(100));
	}

	@Test
	public void eliminarDireccion() {

		Direccion direccion = new Direccion();
		direccion.setIdDireccion(1);

		when(direccionRepository.obtenerPorId(1)).thenReturn(direccion);

		direccionService.eliminar(1);

		verify(direccionRepository, times(1)).eliminarDireccion(1);
	}

	@Test
	public void eliminarDireccion_NoExiste() {

		when(direccionRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> direccionService.eliminar(1));
	}

}
