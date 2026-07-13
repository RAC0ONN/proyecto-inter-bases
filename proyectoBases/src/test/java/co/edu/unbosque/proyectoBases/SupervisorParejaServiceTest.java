package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.SupervisorParejaDTO;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.entity.Supervisor;
import co.edu.unbosque.proyectoBases.entity.SupervisorPareja;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.SupervisorParejaRepository;
import co.edu.unbosque.proyectoBases.service.SupervisorParejaService;

public class SupervisorParejaServiceTest {

	private SupervisorParejaRepository supervisorParejaRepository;
	private SupervisorParejaService supervisorParejaService;

	@BeforeEach
	public void setUp() {

		supervisorParejaRepository = Mockito.mock(SupervisorParejaRepository.class);
		supervisorParejaService = new SupervisorParejaService();
		ReflectionTestUtils.setField(supervisorParejaService, "supervisorParejaRepository", supervisorParejaRepository);
	
	}

	@Test
	public void crearSupervisorPareja() {

		SupervisorParejaDTO dto = new SupervisorParejaDTO();
		dto.setIdSupervisorPareja(1);
		dto.setIdSupervisor(2);
		dto.setIdPareja(3);

		supervisorParejaService.crear(dto);

		verify(supervisorParejaRepository, times(1)).crearSupervisorPareja(1, 2, 3);
	}

	@Test
	public void obtenerTodas() {

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(2);

		Pareja pareja = new Pareja();
		pareja.setIdPareja(5);

		SupervisorPareja entidad = new SupervisorPareja();
		entidad.setIdSupervisorPareja(1);
		entidad.setSupervisor(supervisor);
		entidad.setPareja(pareja);

		ArrayList<SupervisorPareja> lista = new ArrayList<>();
		lista.add(entidad);

		when(supervisorParejaRepository.findAll()).thenReturn(lista);

		ArrayList<SupervisorParejaDTO> resultado = supervisorParejaService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdSupervisorPareja());
		assertEquals(2, resultado.get(0).getIdSupervisor());
		assertEquals(5, resultado.get(0).getIdPareja());
	}

	@Test
	public void obtenerTodas_SinDatos() {

		when(supervisorParejaRepository.findAll()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> supervisorParejaService.obtenerTodas());
	}

	@Test
	public void obtenerPorId() {

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(8);

		Pareja pareja = new Pareja();
		pareja.setIdPareja(10);

		SupervisorPareja entidad = new SupervisorPareja();
		entidad.setIdSupervisorPareja(4);
		entidad.setSupervisor(supervisor);
		entidad.setPareja(pareja);

		when(supervisorParejaRepository.obtenerPorId(4)).thenReturn(entidad);

		SupervisorParejaDTO dto = supervisorParejaService.obtenerPorId(4);

		assertEquals(4, dto.getIdSupervisorPareja());
		assertEquals(8, dto.getIdSupervisor());
		assertEquals(10, dto.getIdPareja());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(supervisorParejaRepository.obtenerPorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> supervisorParejaService.obtenerPorId(100));
	}

	@Test
	public void obtenerPorSupervisor() {

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(7);

		Pareja pareja = new Pareja();
		pareja.setIdPareja(15);

		SupervisorPareja entidad = new SupervisorPareja();
		entidad.setIdSupervisorPareja(20);
		entidad.setSupervisor(supervisor);
		entidad.setPareja(pareja);

		ArrayList<SupervisorPareja> lista = new ArrayList<>();
		lista.add(entidad);

		when(supervisorParejaRepository.obtenerPorSupervisor(7)).thenReturn(lista);

		ArrayList<SupervisorParejaDTO> resultado = supervisorParejaService.obtenerPorSupervisor(7);

		assertEquals(1, resultado.size());
		assertEquals(20, resultado.get(0).getIdSupervisorPareja());
		assertEquals(7, resultado.get(0).getIdSupervisor());
		assertEquals(15, resultado.get(0).getIdPareja());
	}

	@Test
	public void obtenerPorSupervisor_SinDatos() {

		when(supervisorParejaRepository.obtenerPorSupervisor(7)).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> supervisorParejaService.obtenerPorSupervisor(7));
	}

	@Test
	public void eliminarSupervisorPareja() {

		SupervisorPareja entidad = new SupervisorPareja();
		entidad.setIdSupervisorPareja(1);

		when(supervisorParejaRepository.obtenerPorId(1)).thenReturn(entidad);

		supervisorParejaService.eliminar(1);

		verify(supervisorParejaRepository, times(1)).eliminarSupervisorPareja(1);
	}

	@Test
	public void eliminarSupervisorPareja_NoExiste() {

		when(supervisorParejaRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> supervisorParejaService.eliminar(1));
	}

}
