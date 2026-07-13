package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.SupervisorDTO;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import co.edu.unbosque.proyectoBases.entity.Supervisor;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.SupervisorRepository;
import co.edu.unbosque.proyectoBases.service.SupervisorService;

public class SupervisorServiceTest {

	private SupervisorRepository supervisorRepository;
	private SupervisorService supervisorService;

	@BeforeEach
	public void setUp() {

		supervisorRepository = Mockito.mock(SupervisorRepository.class);
		supervisorService = new SupervisorService();
		ReflectionTestUtils.setField(supervisorService, "supervisorRepository", supervisorRepository);
	}

	@Test
	public void crearSupervisor() {

		SupervisorDTO dto = new SupervisorDTO();
		dto.setPrimerNombre("Miguel");
		dto.setSegundoNombre("Angel");
		dto.setPrimerApellido("Castro");
		dto.setSegundoApellido("Rojas");
		dto.setNombreUsuario("mcastro");
		dto.setContraseniaUsuario("clave123");
		dto.setCorreoElectronico("miguel@empresa.com");
		dto.setIdAlmacen(12);

		supervisorService.crear(dto);

		verify(supervisorRepository).crearSupervisor("Miguel", "Angel", "Castro", "Rojas", "mcastro", "clave123",
				"miguel@empresa.com", 12);
	}

	@Test
	public void obtenerTodos() {

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(15);

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(7);
		supervisor.setPrimerNombre("Natalia");
		supervisor.setSegundoNombre("Sofia");
		supervisor.setPrimerApellido("Ramirez");
		supervisor.setSegundoApellido("Muñoz");
		supervisor.setNombreUsuario("nramirez");
		supervisor.setContraseniaUsuario("pass987");
		supervisor.setCorreoElectronico("natalia@empresa.com");
		supervisor.setAlmacen(almacen);

		ArrayList<Supervisor> lista = new ArrayList<>();
		lista.add(supervisor);

		when(supervisorRepository.obtenerTodos()).thenReturn(lista);

		ArrayList<SupervisorDTO> resultado = supervisorService.obtenerTodos();

		assertEquals(1, resultado.size());
		assertEquals(7, resultado.get(0).getIdSupervisor());
		assertEquals("Natalia", resultado.get(0).getPrimerNombre());
		assertEquals("Sofia", resultado.get(0).getSegundoNombre());
		assertEquals("Ramirez", resultado.get(0).getPrimerApellido());
		assertEquals("Muñoz", resultado.get(0).getSegundoApellido());
		assertEquals("nramirez", resultado.get(0).getNombreUsuario());
		assertEquals("pass987", resultado.get(0).getContraseniaUsuario());
		assertEquals("natalia@empresa.com", resultado.get(0).getCorreoElectronico());
		assertEquals(15, resultado.get(0).getIdAlmacen());
	}

	@Test
	public void obtenerTodos_SinDatos() {

		when(supervisorRepository.obtenerTodos()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> supervisorService.obtenerTodos());
	}

	@Test
	public void obtenerPorId() {

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(4);

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(10);
		supervisor.setPrimerNombre("Laura");
		supervisor.setSegundoNombre("Maria");
		supervisor.setPrimerApellido("Ruiz");
		supervisor.setSegundoApellido("Torres");
		supervisor.setNombreUsuario("lruiz");
		supervisor.setContraseniaUsuario("abc");
		supervisor.setCorreoElectronico("laura@correo.com");
		supervisor.setAlmacen(almacen);

		when(supervisorRepository.obtenerPorId(10)).thenReturn(supervisor);

		SupervisorDTO dto = supervisorService.obtenerPorId(10);

		assertEquals(10, dto.getIdSupervisor());
		assertEquals("Laura", dto.getPrimerNombre());
		assertEquals("Maria", dto.getSegundoNombre());
		assertEquals("Ruiz", dto.getPrimerApellido());
		assertEquals("Torres", dto.getSegundoApellido());
		assertEquals("lruiz", dto.getNombreUsuario());
		assertEquals("abc", dto.getContraseniaUsuario());
		assertEquals("laura@correo.com", dto.getCorreoElectronico());
		assertEquals(4, dto.getIdAlmacen());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(supervisorRepository.obtenerPorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> supervisorService.obtenerPorId(100));
	}

	@Test
	public void eliminarSupervisor() {

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(1);

		when(supervisorRepository.obtenerPorId(1)).thenReturn(supervisor);

		supervisorService.eliminar(1);

		verify(supervisorRepository, times(1)).eliminarSupervisor(1);
	}

	@Test
	public void eliminarSupervisor_NoExiste() {

		when(supervisorRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> supervisorService.eliminar(1));
	}

	@Test
	public void autenticarSupervisor() {

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(5);
		supervisor.setPrimerNombre("Carlos");
		supervisor.setNombreUsuario("carlos");
		supervisor.setContraseniaUsuario("1234");

		ArrayList<Supervisor> lista = new ArrayList<>();
		lista.add(supervisor);

		when(supervisorRepository.obtenerTodos()).thenReturn(lista);

		SupervisorDTO dto = supervisorService.autenticar("carlos", "1234");

		assertEquals(5, dto.getIdSupervisor());
		assertEquals("Carlos", dto.getPrimerNombre());
	}

	@Test
	public void autenticarSupervisor_ContraseniaIncorrecta() {

		Supervisor supervisor = new Supervisor();
		supervisor.setNombreUsuario("carlos");
		supervisor.setContraseniaUsuario("1234");

		ArrayList<Supervisor> lista = new ArrayList<>();
		lista.add(supervisor);

		when(supervisorRepository.obtenerTodos()).thenReturn(lista);

		assertThrows(RecursoNoExistenteException.class, () -> supervisorService.autenticar("carlos", "0000"));
	}

	@Test
	public void autenticarSupervisor_UsuarioNoExiste() {

		ArrayList<Supervisor> lista = new ArrayList<>();

		Supervisor supervisor = new Supervisor();
		supervisor.setNombreUsuario("juan");
		supervisor.setContraseniaUsuario("123");

		lista.add(supervisor);

		when(supervisorRepository.obtenerTodos()).thenReturn(lista);

		assertThrows(RecursoNoExistenteException.class, () -> supervisorService.autenticar("carlos", "123"));
	}

	@Test
	public void autenticarSupervisor_SinDatos() {

		when(supervisorRepository.obtenerTodos()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> supervisorService.autenticar("carlos", "1234"));
	}

}
