package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.ClienteDTO;
import co.edu.unbosque.proyectoBases.entity.Cliente;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.ClienteRepository;
import co.edu.unbosque.proyectoBases.service.ClienteService;

public class ClienteServiceTest {

	private ClienteRepository clienteRepository;
	private ClienteService clienteService;

	@BeforeEach
	public void setUp() {
		
		clienteRepository = Mockito.mock(ClienteRepository.class);
		clienteService = new ClienteService();
		ReflectionTestUtils.setField(clienteService, "clienteRepository", clienteRepository);
	
	}

	@Test
	public void crearCliente() {

		ClienteDTO dto = new ClienteDTO();
		dto.setPrimerNombre("Juan");
		dto.setSegundoNombre("Carlos");
		dto.setPrimerApellido("Perez");
		dto.setSegundoApellido("Lopez");
		dto.setNombreUsuario("juan");
		dto.setContraseniaUsuario("1234");
		dto.setCorreoElectronico("juanCarlo2@test.com");
		dto.setCupoTotal(5000);

		clienteService.crear(dto);

		verify(clienteRepository, times(1)).crearCliente("Juan", "Carlos", "Perez", "Lopez", "juan", "1234",
				"juanCarlo2@test.com", 5000);
	}

	@Test
	public void obtenerTodos() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setPrimerNombre("Juan");
		cliente.setSegundoNombre("Carlos");
		cliente.setPrimerApellido("Perez");
		cliente.setSegundoApellido("Lopez");
		cliente.setNombreUsuario("juan");
		cliente.setContraseniaUsuario("1234");
		cliente.setCorreoElectronico("juan@test.com");
		cliente.setCupoTotal(5000);

		ArrayList<Cliente> lista = new ArrayList<>();
		lista.add(cliente);

		when(clienteRepository.obtenerTodos()).thenReturn(lista);

		ArrayList<ClienteDTO> resultado = clienteService.obtenerTodos();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdCliente());
		assertEquals("Juan", resultado.get(0).getPrimerNombre());
		assertEquals("Perez", resultado.get(0).getPrimerApellido());
		assertEquals("juan", resultado.get(0).getNombreUsuario());
		assertEquals(5000, resultado.get(0).getCupoTotal());
	}

	@Test
	public void obtenerTodos_SinDatos() {

		when(clienteRepository.obtenerTodos()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> clienteService.obtenerTodos());
	}

	@Test
	public void obtenerPorId() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(3);
		cliente.setPrimerNombre("Maria");
		cliente.setPrimerApellido("Gomez");

		when(clienteRepository.obtenerPorId(3)).thenReturn(cliente);

		ClienteDTO dto = clienteService.obtenerPorId(3);

		assertEquals(3, dto.getIdCliente());
		assertEquals("Maria", dto.getPrimerNombre());
		assertEquals("Gomez", dto.getPrimerApellido());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(clienteRepository.obtenerPorId(30)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> clienteService.obtenerPorId(30));
	}

	@Test
	public void eliminarCliente() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(5);

		when(clienteRepository.obtenerPorId(5)).thenReturn(cliente);

		clienteService.eliminar(5);

		verify(clienteRepository, times(1)).eliminarCliente(5);
	}

	@Test
	public void eliminarCliente_NoExiste() {

		when(clienteRepository.obtenerPorId(5)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> clienteService.eliminar(5));
	}

	@Test
	public void autenticarCliente() {

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setNombreUsuario("juan");
		cliente.setContraseniaUsuario("1234");
		cliente.setPrimerNombre("Juan");

		ArrayList<Cliente> lista = new ArrayList<>();
		lista.add(cliente);

		when(clienteRepository.obtenerTodos()).thenReturn(lista);

		ClienteDTO dto = clienteService.autenticar("juan", "1234");

		assertEquals("Juan", dto.getPrimerNombre());
		assertEquals("juan", dto.getNombreUsuario());
	}

	@Test
	public void autenticarCliente_ContrasenaIncorrecta() {

		Cliente cliente = new Cliente();
		cliente.setNombreUsuario("juan");
		cliente.setContraseniaUsuario("1234");

		ArrayList<Cliente> lista = new ArrayList<>();
		lista.add(cliente);

		when(clienteRepository.obtenerTodos()).thenReturn(lista);

		assertThrows(RecursoNoExistenteException.class, () -> clienteService.autenticar("juan", "abcd"));
	}

	@Test
	public void autenticarCliente_UsuarioNoExiste() {

		ArrayList<Cliente> lista = new ArrayList<>();

		Cliente cliente = new Cliente();
		cliente.setNombreUsuario("pedro");
		cliente.setContraseniaUsuario("123");

		lista.add(cliente);

		when(clienteRepository.obtenerTodos()).thenReturn(lista);

		assertThrows(RecursoNoExistenteException.class, () -> clienteService.autenticar("juan", "123"));
	}

	@Test
	public void autenticarCliente_SinDatos() {

		when(clienteRepository.obtenerTodos()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> clienteService.autenticar("juan", "1234"));
	}

}
