package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.CompraProductoDTO;
import co.edu.unbosque.proyectoBases.entity.Compra;
import co.edu.unbosque.proyectoBases.entity.CompraProducto;
import co.edu.unbosque.proyectoBases.entity.Producto;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.CompraProductoRepository;
import co.edu.unbosque.proyectoBases.service.CompraProductoService;

public class CompraProductoServiceTest {

	private CompraProductoRepository compraProductoRepository;
	private CompraProductoService compraProductoService;

	@BeforeEach
	public void setUp() {

		compraProductoRepository = Mockito.mock(CompraProductoRepository.class);
		compraProductoService = new CompraProductoService();
		ReflectionTestUtils.setField(compraProductoService, "compraProductoRepository", compraProductoRepository);
	}

	@Test
	public void crearCompraProducto() {

		CompraProductoDTO dto = new CompraProductoDTO();
		dto.setIdCompraProducto(1);
		dto.setIdCompra(5);
		dto.setIdProducto(10);

		compraProductoService.crear(dto);

		verify(compraProductoRepository, times(1)).crearCompraProducto(1, 5, 10);
	}

	@Test
	public void obtenerTodas() {

		Compra compra = new Compra();
		compra.setIdCompra(5);

		Producto producto = new Producto();
		producto.setIdProducto(10);

		CompraProducto cproducto = new CompraProducto();
		cproducto.setIdCompraProducto(1);
		cproducto.setCompra(compra);
		cproducto.setProducto(producto);

		ArrayList<CompraProducto> lista = new ArrayList<>();
		lista.add(cproducto);

		when(compraProductoRepository.findAll()).thenReturn(lista);

		ArrayList<CompraProductoDTO> resultado = compraProductoService.obtenerTodas();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdCompraProducto());
		assertEquals(5, resultado.get(0).getIdCompra());
		assertEquals(10, resultado.get(0).getIdProducto());
	}

	@Test
	public void obtenerTodas_SinDatos() {

		when(compraProductoRepository.findAll()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> compraProductoService.obtenerTodas());
	}

	@Test
	public void obtenerPorId() {

		Compra compra = new Compra();
		compra.setIdCompra(7);

		Producto producto = new Producto();
		producto.setIdProducto(20);

		CompraProducto cproducto = new CompraProducto();
		cproducto.setIdCompraProducto(2);
		cproducto.setCompra(compra);
		cproducto.setProducto(producto);

		when(compraProductoRepository.obtenerPorId(2)).thenReturn(cproducto);

		CompraProductoDTO dto = compraProductoService.obtenerPorId(2);

		assertEquals(2, dto.getIdCompraProducto());
		assertEquals(7, dto.getIdCompra());
		assertEquals(20, dto.getIdProducto());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(compraProductoRepository.obtenerPorId(10)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> compraProductoService.obtenerPorId(10));
	}

	@Test
	public void obtenerPorCompra() {

		Compra compra = new Compra();
		compra.setIdCompra(3);

		Producto producto = new Producto();
		producto.setIdProducto(15);

		CompraProducto cproducto = new CompraProducto();
		cproducto.setIdCompraProducto(8);
		cproducto.setCompra(compra);
		cproducto.setProducto(producto);

		ArrayList<CompraProducto> lista = new ArrayList<>();
		lista.add(cproducto);

		when(compraProductoRepository.obtenerPorCompra(3)).thenReturn(lista);

		ArrayList<CompraProductoDTO> resultado = compraProductoService.obtenerPorCompra(3);

		assertEquals(1, resultado.size());
		assertEquals(8, resultado.get(0).getIdCompraProducto());
		assertEquals(3, resultado.get(0).getIdCompra());
		assertEquals(15, resultado.get(0).getIdProducto());
	}

	@Test
	public void obtenerPorCompra_SinDatos() {

		when(compraProductoRepository.obtenerPorCompra(3)).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> compraProductoService.obtenerPorCompra(3));
	}

	@Test
	public void eliminarCompraProducto() {

		CompraProducto cproducto = new CompraProducto();
		cproducto.setIdCompraProducto(1);

		when(compraProductoRepository.obtenerPorId(1)).thenReturn(cproducto);

		compraProductoService.eliminar(1);

		verify(compraProductoRepository, times(1)).eliminarCompraProducto(1);
	}

	@Test
	public void eliminarCompraProducto_NoExiste() {

		when(compraProductoRepository.obtenerPorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> compraProductoService.eliminar(1));
	}
}
