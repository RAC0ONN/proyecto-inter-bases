package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.SobrecupoDTO;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.entity.Sobrecupo;
import co.edu.unbosque.proyectoBases.entity.Supervisor;
import co.edu.unbosque.proyectoBases.exceptions.RecursoEstadoInvalidoException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.AlmacenRepository;
import co.edu.unbosque.proyectoBases.repository.ParejaRepository;
import co.edu.unbosque.proyectoBases.repository.SobrecupoRepository;
import co.edu.unbosque.proyectoBases.repository.SupervisorRepository;
import co.edu.unbosque.proyectoBases.service.SobrecupoService;

public class SobrecupoServiceTest {

	private SobrecupoRepository sobrecupoRepository;
	private ParejaRepository parejaRepository;
	private AlmacenRepository almacenRepository;
	private SupervisorRepository supervisorRepository;
	private SobrecupoService sobrecupoService;

	@BeforeEach
	public void setUp() {
		sobrecupoRepository = Mockito.mock(SobrecupoRepository.class);
		parejaRepository = Mockito.mock(ParejaRepository.class);
		almacenRepository = Mockito.mock(AlmacenRepository.class);
		supervisorRepository = Mockito.mock(SupervisorRepository.class);

		sobrecupoService = new SobrecupoService();
		ReflectionTestUtils.setField(sobrecupoService, "sobrecupoRepository", sobrecupoRepository);
		ReflectionTestUtils.setField(sobrecupoService, "parejaRepository", parejaRepository);
		ReflectionTestUtils.setField(sobrecupoService, "almacenRepository", almacenRepository);
		ReflectionTestUtils.setField(sobrecupoService, "supervisorRepository", supervisorRepository);
	}
/*
	@Test
	public void solicitar_DeberiaCrearSobrecupoPendienteConElSupervisorDelAlmacen() {
		Pareja pareja = new Pareja();
		pareja.setIdPareja(3);

		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(1);

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(7);

		SobrecupoDTO dto = new SobrecupoDTO();
		dto.setIdPareja(3);
		dto.setIdAlmacen(1);
		dto.setMontoSobrecupo(200000);

		when(parejaRepository.obtenerPorId(3)).thenReturn(pareja);
		when(almacenRepository.obtenerPorId(1)).thenReturn(almacen);
		when(supervisorRepository.obtenerPorAlmacen(1)).thenReturn(Optional.of(supervisor));
		when(sobrecupoRepository.obtenerSiguienteId()).thenReturn(1);

		sobrecupoService.solicitar(dto);

		verify(sobrecupoRepository, times(1)).crearSobrecupo(1, 7, 3, SobrecupoService.PENDIENTE, 200000);
	}
*/
	@Test
	public void solicitar_DeberiaLanzarExcepcionSiElAlmacenNoTieneSupervisor() {
		Pareja pareja = new Pareja();
		pareja.setIdPareja(3);
		Almacen almacen = new Almacen();
		almacen.setIdAlmacen(1);

		SobrecupoDTO dto = new SobrecupoDTO();
		dto.setIdPareja(3);
		dto.setIdAlmacen(1);
		dto.setMontoSobrecupo(200000);

		when(parejaRepository.obtenerPorId(3)).thenReturn(pareja);
		when(almacenRepository.obtenerPorId(1)).thenReturn(almacen);
		when(supervisorRepository.obtenerPorAlmacen(1)).thenReturn(Optional.empty());

		assertThrows(RecursoNoExistenteException.class, () -> sobrecupoService.solicitar(dto));
	}

	@Test
	public void escalarACliente_DeberiaPasarAEsperandoClienteSiEscalaEsTrue() {
		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(1);
		sobrecupo.setEstadoSobrecupo(SobrecupoService.PENDIENTE);

		when(sobrecupoRepository.obtenerPorId(1)).thenReturn(sobrecupo);

		sobrecupoService.escalarACliente(1, true);

		verify(sobrecupoRepository, times(1)).actualizarEstado(SobrecupoService.ESPERANDO_CLIENTE, 1);
	}

	@Test
	public void escalarACliente_DeberiaRechazarDirectoSiEscalaEsFalse() {
		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(1);
		sobrecupo.setEstadoSobrecupo(SobrecupoService.PENDIENTE);

		when(sobrecupoRepository.obtenerPorId(1)).thenReturn(sobrecupo);

		sobrecupoService.escalarACliente(1, false);

		verify(sobrecupoRepository, times(1)).actualizarEstado(SobrecupoService.RECHAZADO, 1);
	}

	@Test
	public void escalarACliente_DeberiaLanzarExcepcionSiYaFueProcesada() {
		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(1);
		sobrecupo.setEstadoSobrecupo(SobrecupoService.APROBADO);

		when(sobrecupoRepository.obtenerPorId(1)).thenReturn(sobrecupo);

		assertThrows(RecursoEstadoInvalidoException.class, () -> sobrecupoService.escalarACliente(1, true));
	}

	@Test
	public void responderCliente_DeberiaAprobarSiEstaEsperandoCliente() {
		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(1);
		sobrecupo.setEstadoSobrecupo(SobrecupoService.ESPERANDO_CLIENTE);

		when(sobrecupoRepository.obtenerPorId(1)).thenReturn(sobrecupo);

		sobrecupoService.responderCliente(1, true);

		verify(sobrecupoRepository, times(1)).actualizarEstado(SobrecupoService.APROBADO, 1);
	}

	@Test
	public void responderCliente_DeberiaLanzarExcepcionSiNoEstaEsperandoCliente() {
		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(1);
		sobrecupo.setEstadoSobrecupo(SobrecupoService.PENDIENTE);

		when(sobrecupoRepository.obtenerPorId(1)).thenReturn(sobrecupo);

		assertThrows(RecursoEstadoInvalidoException.class, () -> sobrecupoService.responderCliente(1, true));
	}

	@Test
	public void obtenerTodas() {
		Pareja pareja = new Pareja();
		pareja.setIdPareja(3);

		Supervisor supervisor = new Supervisor();
		supervisor.setIdSupervisor(7);

		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(1);
		sobrecupo.setEstadoSobrecupo(SobrecupoService.PENDIENTE);
		sobrecupo.setMontoSobrecupo(300000);
		sobrecupo.setPareja(pareja);
		sobrecupo.setSupervisor(supervisor);

		ArrayList<Sobrecupo> lista = new ArrayList<>();
		lista.add(sobrecupo);

		when(sobrecupoRepository.findAll()).thenReturn(lista);

		ArrayList<SobrecupoDTO> resultado = sobrecupoService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdSobrecupo());
		assertEquals(SobrecupoService.PENDIENTE, resultado.get(0).getEstadoSobrecupo());
		assertEquals(300000.0, resultado.get(0).getMontoSobrecupo());
		assertEquals(3, resultado.get(0).getIdPareja());
		assertEquals(7, resultado.get(0).getIdSupervisor());
	}

	@Test
	public void obtenerTodas_SinDatos() {
		when(sobrecupoRepository.findAll()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> sobrecupoService.obtenerTodas());
	}

	@Test
	public void obtenerPorId_NoExiste() {
		when(sobrecupoRepository.obtenerPorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> sobrecupoService.obtenerPorId(100));
	}

	@Test
	public void obtenerPorPareja_SinDatos() {
		when(sobrecupoRepository.obtenerPorPareja(4)).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> sobrecupoService.obtenerPorPareja(4));
	}

	@Test
	public void obtenerPorSupervisor_SinDatos() {
		when(sobrecupoRepository.obtenerPorSupervisor(7)).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> sobrecupoService.obtenerPorSupervisor(7));
	}

	@Test
	public void obtenerPorCliente_SinDatos() {
		when(sobrecupoRepository.obtenerPorCliente(1)).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> sobrecupoService.obtenerPorCliente(1));
	}

	@Test
	public void eliminarSobrecupo() {
		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(1);

		when(sobrecupoRepository.obtenerPorId(1)).thenReturn(sobrecupo);

		sobrecupoService.eliminar(1);

		verify(sobrecupoRepository, times(1)).eliminarSobrecupo(1);
	}

	@Test
	public void eliminarSobrecupo_NoExiste() {
		when(sobrecupoRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> sobrecupoService.eliminar(1));
	}

}
