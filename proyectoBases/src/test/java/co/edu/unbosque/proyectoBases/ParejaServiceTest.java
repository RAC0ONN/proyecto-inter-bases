package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.ParejaDTO;
import co.edu.unbosque.proyectoBases.entity.Cliente;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.exceptions.RecursoLimiteCupoExcedidoException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.ClienteRepository;
import co.edu.unbosque.proyectoBases.repository.ParejaRepository;
import co.edu.unbosque.proyectoBases.service.ParejaService;

public class ParejaServiceTest {

	private ParejaRepository parejaRepository;
	private ClienteRepository clienteRepository;
	private ParejaService parejaService;

	@BeforeEach
	public void setUp() {

		parejaRepository = Mockito.mock(ParejaRepository.class);
		clienteRepository = Mockito.mock(ClienteRepository.class);
		parejaService = new ParejaService();

		ReflectionTestUtils.setField(parejaService, "parejaRepository", parejaRepository);
		ReflectionTestUtils.setField(parejaService, "clienteRepository", clienteRepository);
	}

	@Test
	public void crearPareja() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setCupoTotal(5000);

		when(clienteRepository.obtenerPorId(1)).thenReturn(cliente);
		when(parejaRepository.obtenerCupoAsignadoTotal(1)).thenReturn(1000.0);

		ParejaDTO dto = new ParejaDTO();
		dto.setPrimerNombre("Juan");
		dto.setSegundoNombre("Carlos");
		dto.setPrimerApellido("Perez");
		dto.setSegundoApellido("Lopez");
		dto.setNombreUsuario("juan");
		dto.setContraseniaUsuario("123");
		dto.setCorreoElectronico("juan@test.com");
		dto.setCupoAsignado(2000);
		dto.setIdCliente(1);

		parejaService.crear(dto);

		verify(parejaRepository, times(1)).crearPareja("Juan", "Carlos", "Perez", "Lopez", "juan", "123", 2000,
				"juan@test.com", 1);
	}

	@Test
	public void crearPareja_ClienteNoExiste() {

		when(clienteRepository.obtenerPorId(1)).thenReturn(null);

		ParejaDTO dto = new ParejaDTO();
		dto.setIdCliente(1);

		assertThrows(RecursoNoExistenteException.class, () -> parejaService.crear(dto));
	}

	@Test
	public void crearPareja_CupoExcedido() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setCupoTotal(3000);

		when(clienteRepository.obtenerPorId(1)).thenReturn(cliente);
		when(parejaRepository.obtenerCupoAsignadoTotal(1)).thenReturn(2500.0);

		ParejaDTO dto = new ParejaDTO();
		dto.setIdCliente(1);
		dto.setCupoAsignado(1000);

		assertThrows(RecursoLimiteCupoExcedidoException.class, () -> parejaService.crear(dto));
	}

	@Test
	public void actualizarPareja() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setCupoTotal(5000);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);
		when(clienteRepository.obtenerPorId(1)).thenReturn(cliente);
		when(parejaRepository.obtenerCupoAsignadoTotalExcluyendo(1, 1)).thenReturn(1000.0);

		ParejaDTO dto = new ParejaDTO();
		dto.setIdPareja(1);
		dto.setPrimerNombre("Ana");
		dto.setSegundoNombre("Maria");
		dto.setPrimerApellido("Perez");
		dto.setSegundoApellido("Lopez");
		dto.setNombreUsuario("ana");
		dto.setContraseniaUsuario("123");
		dto.setCorreoElectronico("ana@test.com");
		dto.setCupoAsignado(2000);
		dto.setIdCliente(1);

		parejaService.actualizar(dto);

		verify(parejaRepository, times(1)).actualizarPareja("Ana", "Maria", "Perez", "Lopez", "ana", "123", 2000,
				"ana@test.com", 1, 1);
	}

	@Test
	public void actualizarPareja_NoExiste() {

		when(parejaRepository.obtenerPorId(1)).thenReturn(null);

		ParejaDTO dto = new ParejaDTO();
		dto.setIdPareja(1);

		assertThrows(RecursoNoExistenteException.class, () -> parejaService.actualizar(dto));
	}

	@Test
	public void actualizarPareja_ClienteNoExiste() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);
		when(clienteRepository.obtenerPorId(1)).thenReturn(null);

		ParejaDTO dto = new ParejaDTO();
		dto.setIdPareja(1);
		dto.setIdCliente(1);

		assertThrows(RecursoNoExistenteException.class, () -> parejaService.actualizar(dto));
	}

	@Test
	public void actualizarPareja_CupoExcedido() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setCupoTotal(3000);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);
		when(clienteRepository.obtenerPorId(1)).thenReturn(cliente);
		when(parejaRepository.obtenerCupoAsignadoTotalExcluyendo(1, 1)).thenReturn(2800.0);

		ParejaDTO dto = new ParejaDTO();
		dto.setIdPareja(1);
		dto.setIdCliente(1);
		dto.setCupoAsignado(500);

		assertThrows(RecursoLimiteCupoExcedidoException.class, () -> parejaService.actualizar(dto));
	}

	@Test
	public void obtenerTodas() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);
		pareja.setPrimerNombre("Juan");
		pareja.setSegundoNombre("Carlos");
		pareja.setPrimerApellido("Perez");
		pareja.setSegundoApellido("Lopez");
		pareja.setNombreUsuario("juan");
		pareja.setContraseniaUsuario("123");
		pareja.setCorreoElectronico("juan@test.com");
		pareja.setCupoAsignado(2000);
		pareja.setCliente(cliente);

		ArrayList<Pareja> lista = new ArrayList<>();
		lista.add(pareja);

		when(parejaRepository.obtenerTodas()).thenReturn(lista);

		ArrayList<ParejaDTO> resultado = parejaService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals("Juan", resultado.get(0).getPrimerNombre());
		assertEquals(1, resultado.get(0).getIdCliente());
	}

	@Test
	public void obtenerTodas_SinDatos() {

		when(parejaRepository.obtenerTodas()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> parejaService.obtenerTodas());
	}

	@Test
	public void obtenerPorId() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(2);

		Pareja pareja = new Pareja();
		pareja.setIdPareja(5);
		pareja.setPrimerNombre("Laura");
		pareja.setCliente(cliente);

		when(parejaRepository.obtenerPorId(5)).thenReturn(pareja);

		ParejaDTO dto = parejaService.obtenerPorId(5);

		assertEquals(5, dto.getIdPareja());
		assertEquals("Laura", dto.getPrimerNombre());
		assertEquals(2, dto.getIdCliente());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(parejaRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> parejaService.obtenerPorId(1));
	}

	@Test
	public void obtenerPorCliente() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);
		pareja.setPrimerNombre("Pedro");
		pareja.setCliente(cliente);

		ArrayList<Pareja> lista = new ArrayList<>();
		lista.add(pareja);

		when(parejaRepository.obtenerPorCliente(1)).thenReturn(lista);

		ArrayList<ParejaDTO> resultado = parejaService.obtenerPorCliente(1);

		assertEquals(1, resultado.size());
		assertEquals("Pedro", resultado.get(0).getPrimerNombre());
	}

	@Test
	public void obtenerPorCliente_SinDatos() {

		when(parejaRepository.obtenerPorCliente(1)).thenReturn(null);

		ArrayList<ParejaDTO> resultado = parejaService.obtenerPorCliente(1);

		assertTrue(resultado.isEmpty());
	}

	@Test
	public void obtenerCupoConsumido() {

		when(parejaRepository.obtenerCupoAsignadoTotal(1)).thenReturn(3500.0);

		assertEquals(3500.0, parejaService.obtenerCupoConsumido(1));
	}

	@Test
	public void eliminarPareja() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(1);

		when(parejaRepository.obtenerPorId(1)).thenReturn(pareja);

		parejaService.eliminar(1);

		verify(parejaRepository, times(1)).eliminarPareja(1);
	}

	@Test
	public void eliminarPareja_NoExiste() {

		when(parejaRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> parejaService.eliminar(1));
	}

}
