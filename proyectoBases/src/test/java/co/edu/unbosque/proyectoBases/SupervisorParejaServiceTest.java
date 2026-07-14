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
		dto.setIdSupervisor(2);
		dto.setIdPareja(3);

		when(supervisorParejaRepository.obtenerPorPareja(3)).thenReturn(null);

		supervisorParejaService.crear(dto);

		verify(supervisorParejaRepository, times(1)).crearSupervisorPareja(2, 3);
	}

	@Test
	public void crearSupervisorPareja_DeberiaLanzarExcepcionSiLaParejaYaTieneSupervisor() {
		SupervisorParejaDTO dto = new SupervisorParejaDTO();
		dto.setIdSupervisor(2);
		dto.setIdPareja(3);

		when(supervisorParejaRepository.obtenerPorPareja(3)).thenReturn(new SupervisorPareja());

		assertThrows(RecursoNoExistenteException.class, () -> supervisorParejaService.crear(dto));

		verify(supervisorParejaRepository, never()).crearSupervisorPareja(anyInt(), anyInt());
	}

	@Test
	public void obtenerTodas() {
		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(2);

		SupervisorPareja entidad = new SupervisorPareja();
		entidad.setIdPareja(5);
		entidad.setSupervisor(supervisor);

		ArrayList<SupervisorPareja> lista = new ArrayList<>();
		lista.add(entidad);

		when(supervisorParejaRepository.findAll()).thenReturn(lista);

		ArrayList<SupervisorParejaDTO> resultado = supervisorParejaService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(2, resultado.get(0).getIdSupervisor());
		assertEquals(5, resultado.get(0).getIdPareja());
	}

	@Test
	public void obtenerTodas_SinDatos() {
		when(supervisorParejaRepository.findAll()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> supervisorParejaService.obtenerTodas());
	}

	@Test
	public void obtenerPorPareja() {
		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(8);

		SupervisorPareja entidad = new SupervisorPareja();
		entidad.setIdPareja(10);
		entidad.setSupervisor(supervisor);

		when(supervisorParejaRepository.obtenerPorPareja(10)).thenReturn(entidad);

		SupervisorParejaDTO dto = supervisorParejaService.obtenerPorPareja(10);

		assertEquals(8, dto.getIdSupervisor());
		assertEquals(10, dto.getIdPareja());
	}

	@Test
	public void obtenerPorPareja_NoExiste() {
		when(supervisorParejaRepository.obtenerPorPareja(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> supervisorParejaService.obtenerPorPareja(100));
	}

	@Test
	public void obtenerPorSupervisor() {
		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(7);

		SupervisorPareja entidad = new SupervisorPareja();
		entidad.setIdPareja(15);
		entidad.setSupervisor(supervisor);

		ArrayList<SupervisorPareja> lista = new ArrayList<>();
		lista.add(entidad);

		when(supervisorParejaRepository.obtenerPorSupervisor(7)).thenReturn(lista);

		ArrayList<SupervisorParejaDTO> resultado = supervisorParejaService.obtenerPorSupervisor(7);

		assertEquals(1, resultado.size());
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
		entidad.setIdPareja(3);

		when(supervisorParejaRepository.obtenerPorPareja(3)).thenReturn(entidad);

		supervisorParejaService.eliminar(3);

		verify(supervisorParejaRepository, times(1)).eliminarSupervisorPareja(3);
	}

	@Test
	public void eliminarSupervisorPareja_NoExiste() {
		when(supervisorParejaRepository.obtenerPorPareja(3)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> supervisorParejaService.eliminar(3));
	}
}