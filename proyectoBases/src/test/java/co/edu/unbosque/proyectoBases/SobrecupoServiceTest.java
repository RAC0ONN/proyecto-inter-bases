package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.SobrecupoDTO;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import co.edu.unbosque.proyectoBases.entity.Sobrecupo;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.SobrecupoRepository;
import co.edu.unbosque.proyectoBases.service.SobrecupoService;

public class SobrecupoServiceTest {

	private SobrecupoRepository sobrecupoRepository;
	private SobrecupoService sobrecupoService;

	@BeforeEach
	public void setUp() {

		sobrecupoRepository = Mockito.mock(SobrecupoRepository.class);
		sobrecupoService = new SobrecupoService();
		ReflectionTestUtils.setField(sobrecupoService, "sobrecupoRepository", sobrecupoRepository);
		
	}

	@Test
	public void crearSobrecupo() {

		SobrecupoDTO dto = new SobrecupoDTO();
		dto.setIdSobrecupo(1);
		dto.setPorcentajeSobrecupo(20.0);
		dto.setValorMaximo(500000.0);
		dto.setIdPareja(3);

		sobrecupoService.crear(dto);

		verify(sobrecupoRepository, times(1)).crearSobrecupo(1, 20.0, 500000.0, 3);
	}

	@Test
	public void obtenerTodas() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(3);

		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(1);
		sobrecupo.setPorcentajeSobrecupo(15.0);
		sobrecupo.setValorMaximo(300000.0);
		sobrecupo.setPareja(pareja);

		ArrayList<Sobrecupo> lista = new ArrayList<>();
		lista.add(sobrecupo);

		when(sobrecupoRepository.findAll()).thenReturn(lista);

		ArrayList<SobrecupoDTO> resultado = sobrecupoService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdSobrecupo());
		assertEquals(15.0, resultado.get(0).getPorcentajeSobrecupo());
		assertEquals(300000.0, resultado.get(0).getValorMaximo());
		assertEquals(3, resultado.get(0).getIdPareja());
	}

	@Test
	public void obtenerTodas_SinDatos() {

		when(sobrecupoRepository.findAll()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> sobrecupoService.obtenerTodas());
	}

	@Test
	public void obtenerPorId() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(8);

		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(5);
		sobrecupo.setPorcentajeSobrecupo(10.0);
		sobrecupo.setValorMaximo(150000.0);
		sobrecupo.setPareja(pareja);

		when(sobrecupoRepository.obtenerPorId(5)).thenReturn(sobrecupo);

		SobrecupoDTO dto = sobrecupoService.obtenerPorId(5);

		assertEquals(5, dto.getIdSobrecupo());
		assertEquals(10.0, dto.getPorcentajeSobrecupo());
		assertEquals(150000.0, dto.getValorMaximo());
		assertEquals(8, dto.getIdPareja());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(sobrecupoRepository.obtenerPorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> sobrecupoService.obtenerPorId(100));
	}

	@Test
	public void obtenerPorPareja() {

		Pareja pareja = new Pareja();
		pareja.setIdPareja(4);

		Sobrecupo sobrecupo = new Sobrecupo();
		sobrecupo.setIdSobrecupo(2);
		sobrecupo.setPorcentajeSobrecupo(25.0);
		sobrecupo.setValorMaximo(600000.0);
		sobrecupo.setPareja(pareja);

		ArrayList<Sobrecupo> lista = new ArrayList<>();
		lista.add(sobrecupo);

		when(sobrecupoRepository.obtenerPorPareja(4)).thenReturn(lista);

		ArrayList<SobrecupoDTO> resultado = sobrecupoService.obtenerPorPareja(4);

		assertEquals(1, resultado.size());
		assertEquals(2, resultado.get(0).getIdSobrecupo());
		assertEquals(25.0, resultado.get(0).getPorcentajeSobrecupo());
		assertEquals(600000.0, resultado.get(0).getValorMaximo());
		assertEquals(4, resultado.get(0).getIdPareja());
	}

	@Test
	public void obtenerPorPareja_SinDatos() {

		when(sobrecupoRepository.obtenerPorPareja(4)).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> sobrecupoService.obtenerPorPareja(4));
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
