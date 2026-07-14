package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.CompraDTO;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import co.edu.unbosque.proyectoBases.entity.Compra;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.entity.Restriccion;
import co.edu.unbosque.proyectoBases.exceptions.RecursoCompraRestringidaException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoCupoInsuficienteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.AlmacenRepository;
import co.edu.unbosque.proyectoBases.repository.CompraRepository;
import co.edu.unbosque.proyectoBases.repository.ParejaRepository;
import co.edu.unbosque.proyectoBases.repository.RestriccionRepository;
import co.edu.unbosque.proyectoBases.repository.SobrecupoRepository;
import co.edu.unbosque.proyectoBases.service.CompraService;

public class CompraServiceTest {

	private CompraRepository compraRepository;
	private ParejaRepository parejaRepository;
	private AlmacenRepository almacenRepository;
	private RestriccionRepository restriccionRepository;
	private SobrecupoRepository sobrecupoRepository;
	private CompraService compraService;

	@BeforeEach
	public void setUp() {

		compraRepository = Mockito.mock(CompraRepository.class);
		parejaRepository = Mockito.mock(ParejaRepository.class);
		almacenRepository = Mockito.mock(AlmacenRepository.class);
		restriccionRepository = Mockito.mock(RestriccionRepository.class);
		sobrecupoRepository = Mockito.mock(SobrecupoRepository.class);
		compraService = new CompraService();

		ReflectionTestUtils.setField(compraService, "compraRepository", compraRepository);
		ReflectionTestUtils.setField(compraService, "parejaRepository", parejaRepository);
		ReflectionTestUtils.setField(compraService, "almacenRepository", almacenRepository);
		ReflectionTestUtils.setField(compraService, "restriccionRepository", restriccionRepository);
		ReflectionTestUtils.setField(compraService, "sobrecupoRepository", sobrecupoRepository);
	}

	/*@Test
	public void crearCompra() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);
		pareja.setCupoAsignado(5000);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(2);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);
		when(almacenRepository.obtenerPorId(2)).thenReturn(almacen);
		when(restriccionRepository.obtenerPorPareja(1)).thenReturn(new ArrayList<>());
		when(compraRepository.obtenerMontoTotalPorPareja(1)).thenReturn(1000.0);
		when(sobrecupoRepository.obtenerMontoAprobadoTotalPorPareja(1)).thenReturn(0.0);
		when(compraRepository.obtenerSiguienteId()).thenReturn(10);

		CompraDTO dto = new CompraDTO();
		dto.setIdPareja(1);
		dto.setIdAlmacen(2);
		dto.setMonto(500);
		dto.setFecha(LocalDate.of(2025, 6, 10));
		dto.setHora(LocalTime.of(10, 0));

		int id = compraService.crear(dto);

		assertEquals(10, id);

		verify(compraRepository).obtenerSiguienteId();
		verify(compraRepository).crearCompra(eq(10), any(LocalTime.class), eq(500.0), any(LocalDate.class), eq(1),
				eq(2));
	}
	*/

	@Test
	public void crearCompra_ParejaNoExiste() {

		when(parejaRepository.obtenerPorId(1)).thenReturn(null);

		CompraDTO dto = new CompraDTO();
		dto.setIdPareja(1);

		assertThrows(RecursoNoExistenteException.class, () -> compraService.crear(dto));
	}

	@Test
	public void crearCompra_AlmacenNoExiste() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);
		when(almacenRepository.obtenerPorId(2)).thenReturn(null);

		CompraDTO dto = new CompraDTO();
		dto.setIdPareja(1);
		dto.setIdAlmacen(2);

		assertThrows(RecursoNoExistenteException.class, () -> compraService.crear(dto));
	}

	@Test
	public void crearCompra_RestriccionHoraria() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);
		pareja.setCupoAsignado(5000);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(2);

		Restriccion restriccion = new Restriccion();
		restriccion.setDiaSemana("MARTES");
		restriccion.setHoraInicio(LocalTime.of(8, 0));
		restriccion.setHoraFin(LocalTime.of(12, 0));

		ArrayList<Restriccion> restricciones = new ArrayList<>();
		restricciones.add(restriccion);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);
		when(almacenRepository.obtenerPorId(2)).thenReturn(almacen);
		when(restriccionRepository.obtenerPorPareja(1)).thenReturn(restricciones);

		CompraDTO dto = new CompraDTO();
		dto.setIdPareja(1);
		dto.setIdAlmacen(2);
		dto.setMonto(100);
		dto.setFecha(LocalDate.of(2025, 6, 10));
		dto.setHora(LocalTime.of(9, 0));

		assertThrows(RecursoCompraRestringidaException.class, () -> compraService.crear(dto));
	}

	@Test
	public void crearCompra_CupoInsuficiente() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);
		pareja.setCupoAsignado(1000);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(2);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);
		when(almacenRepository.obtenerPorId(2)).thenReturn(almacen);
		when(restriccionRepository.obtenerPorPareja(1)).thenReturn(new ArrayList<>());
		when(compraRepository.obtenerMontoTotalPorPareja(1)).thenReturn(900.0);
		when(sobrecupoRepository.obtenerMontoAprobadoTotalPorPareja(1)).thenReturn(50.0);

		CompraDTO dto = new CompraDTO();
		dto.setIdPareja(1);
		dto.setIdAlmacen(2);
		dto.setMonto(500);

		assertThrows(RecursoCupoInsuficienteException.class, () -> compraService.crear(dto));
	}

	/*@Test
	public void crearCompra_UsandoSobrecupo() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);
		pareja.setCupoAsignado(1000);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(2);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);
		when(almacenRepository.obtenerPorId(2)).thenReturn(almacen);
		when(restriccionRepository.obtenerPorPareja(1)).thenReturn(new ArrayList<>());
		when(compraRepository.obtenerMontoTotalPorPareja(1)).thenReturn(950.0);
		when(sobrecupoRepository.obtenerMontoAprobadoTotalPorPareja(1)).thenReturn(500.0);
		when(compraRepository.obtenerSiguienteId()).thenReturn(20);

		CompraDTO dto = new CompraDTO();
		dto.setIdPareja(1);
		dto.setIdAlmacen(2);
		dto.setMonto(300);
		dto.setFecha(LocalDate.of(2025, 6, 11));
		dto.setHora(LocalTime.of(14, 0));

		int id = compraService.crear(dto);

		assertEquals(20, id);

		verify(compraRepository).obtenerSiguienteId();
		verify(compraRepository).crearCompra(eq(20), any(LocalTime.class), eq(300.0), any(LocalDate.class), eq(1),
				eq(2));
	}
*/
	@Test
	public void obtenerTodas() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(2);

		Compra compra = new Compra();
		compra.setIdCompra(10);
		compra.setFecha(LocalDate.of(2025, 6, 10));
		compra.setHora(LocalTime.of(10, 30));
		compra.setMonto(250.0);
		compra.setPareja(pareja);
		compra.setAlmacen(almacen);

		ArrayList<Compra> compras = new ArrayList<>();
		compras.add(compra);

		when(compraRepository.obtenerTodas()).thenReturn(compras);

		ArrayList<CompraDTO> resultado = compraService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(10, resultado.get(0).getIdCompra());
		assertEquals(250.0, resultado.get(0).getMonto());
		assertEquals(1, resultado.get(0).getIdPareja());
		assertEquals(2, resultado.get(0).getIdAlmacen());
	}

	@Test
	public void obtenerTodas_SinDatos() {

		when(compraRepository.obtenerTodas()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> compraService.obtenerTodas());
	}

	@Test
	public void obtenerPorId() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(5);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(3);

		Compra compra = new Compra();
		compra.setIdCompra(20);
		compra.setMonto(500.0);
		compra.setFecha(LocalDate.of(2025, 5, 15));
		compra.setHora(LocalTime.of(14, 45));
		compra.setPareja(pareja);
		compra.setAlmacen(almacen);

		when(compraRepository.obtenerPorId(20)).thenReturn(compra);

		CompraDTO dto = compraService.obtenerPorId(20);

		assertEquals(20, dto.getIdCompra());
		assertEquals(500.0, dto.getMonto());
		assertEquals(5, dto.getIdPareja());
		assertEquals(3, dto.getIdAlmacen());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(compraRepository.obtenerPorId(50)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> compraService.obtenerPorId(50));
	}

	@Test
	public void eliminarCompra() {

		Compra compra = new Compra();
		compra.setIdCompra(1);

		when(compraRepository.obtenerPorId(1)).thenReturn(compra);

		compraService.eliminar(1);

		verify(compraRepository, times(1)).eliminarCompra(1);
	}

	@Test
	public void eliminarCompra_NoExiste() {

		when(compraRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> compraService.eliminar(1));
	}

}
