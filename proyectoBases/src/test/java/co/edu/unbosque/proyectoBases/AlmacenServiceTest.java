package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.AlmacenDTO;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import co.edu.unbosque.proyectoBases.entity.Direccion;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.AlmacenRepository;
import co.edu.unbosque.proyectoBases.service.AlmacenService;

public class AlmacenServiceTest {

	private AlmacenRepository almacenRepository;
	private AlmacenService almacenService;

	@BeforeEach
	public void setUp() {
		
		almacenRepository = Mockito.mock(AlmacenRepository.class);
		almacenService = new AlmacenService();
		ReflectionTestUtils.setField(almacenService, "almacenRepository", almacenRepository);
		
	}

	@Test
	public void crearAlmacen() {

		AlmacenDTO dto = new AlmacenDTO();
		dto.setIdAlmacen(1);
		dto.setNombre("Almacen Central");
		dto.setIdDireccion(10);

		almacenService.crear(dto);

		verify(almacenRepository, times(1)).crearAlmacen(1, "Almacen Central", 10);
	}

	@Test
	public void actualizarAlmacen() {

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(1);

		when(almacenRepository.obtenerPorId(1)).thenReturn(almacen);

		AlmacenDTO dto = new AlmacenDTO();
		dto.setIdAlmacen(1);
		dto.setNombre("Nuevo Nombre");
		dto.setIdDireccion(20);

		almacenService.actualizar(dto);

		verify(almacenRepository, times(1)).actualizarAlmacen("Nuevo Nombre", 20, 1);
	}

	@Test
	public void actualizarAlmacen_NoExiste() {

		when(almacenRepository.obtenerPorId(1)).thenReturn(null);

		AlmacenDTO dto = new AlmacenDTO();
		dto.setIdAlmacen(1);

		assertThrows(RecursoNoExistenteException.class, () -> {
			almacenService.actualizar(dto);
		});
	}

	@Test
	public void obtenerTodos() {

		Direccion direccion = new Direccion();
		direccion.setIdDireccion(10);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(1);
		almacen.setNombre("Principal");
		almacen.setDireccion(direccion);

		ArrayList<Almacen> almacenes = new ArrayList<>();
		almacenes.add(almacen);

		when(almacenRepository.obtenerTodos()).thenReturn(almacenes);

		ArrayList<AlmacenDTO> resultado = almacenService.obtenerTodos();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdAlmacen());
		assertEquals("Principal", resultado.get(0).getNombre());
		assertEquals(10, resultado.get(0).getIdDireccion());
	}

	@Test
	public void obtenerTodos_SinDatos() {

		when(almacenRepository.obtenerTodos()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> {
			almacenService.obtenerTodos();
		});
	}

	@Test
	public void obtenerPorId() {

		Direccion direccion = new Direccion();
		direccion.setIdDireccion(15);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(5);
		almacen.setNombre("Sucursal Norte");
		almacen.setDireccion(direccion);

		when(almacenRepository.obtenerPorId(5)).thenReturn(almacen);

		AlmacenDTO resultado = almacenService.obtenerPorId(5);

		assertEquals(5, resultado.getIdAlmacen());
		assertEquals("Sucursal Norte", resultado.getNombre());
		assertEquals(15, resultado.getIdDireccion());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(almacenRepository.obtenerPorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> {
			almacenService.obtenerPorId(100);
		});
	}

	@Test
	public void eliminarAlmacen() {

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(1);

		when(almacenRepository.obtenerPorId(1)).thenReturn(almacen);

		almacenService.eliminar(1);

		verify(almacenRepository, times(1)).eliminarAlmacen(1);
	}

	@Test
	public void eliminarAlmacen_NoExiste() {

		when(almacenRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> {
			almacenService.eliminar(1);
		});
	}

}
