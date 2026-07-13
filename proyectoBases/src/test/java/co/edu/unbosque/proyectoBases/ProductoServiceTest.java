package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import co.edu.unbosque.proyectoBases.dto.ProductoDTO;
import co.edu.unbosque.proyectoBases.entity.Producto;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.ProductoRepository;
import co.edu.unbosque.proyectoBases.service.ProductoService;

public class ProductoServiceTest {

	private ProductoRepository productoRepository;
	private ProductoService productoService;

	@BeforeEach
	public void setUp() {

		productoRepository = Mockito.mock(ProductoRepository.class);
		productoService = new ProductoService();

		ReflectionTestUtils.setField(productoService, "productoRepository", productoRepository);
	}

	@Test
	public void crearProducto() {

		ProductoDTO dto = new ProductoDTO();
		dto.setIdProducto(1);
		dto.setNombre("Televisor");
		dto.setPrecio(2500.0);

		productoService.crear(dto);

		verify(productoRepository, times(1)).crearProducto(1, "Televisor", 2500.0);
	}

	@Test
	public void actualizarProducto() {

		Producto producto = new Producto();
		producto.setIdProducto(1);

		when(productoRepository.existePorId(1)).thenReturn(producto);

		ProductoDTO dto = new ProductoDTO();
		dto.setIdProducto(1);
		dto.setNombre("Portátil");
		dto.setPrecio(3200.0);

		productoService.actualizar(dto);

		verify(productoRepository, times(1)).actualizarProducto("Portátil", 3200.0, 1);
	}

	@Test
	public void actualizarProducto_NoExiste() {

		when(productoRepository.existePorId(1)).thenReturn(null);

		ProductoDTO dto = new ProductoDTO();
		dto.setIdProducto(1);
		dto.setNombre("Portátil");
		dto.setPrecio(3200.0);

		assertThrows(RecursoNoExistenteException.class, () -> productoService.actualizar(dto));
	}

	@Test
	public void obtenerTodos() {

		Producto producto = new Producto();
		producto.setIdProducto(1);
		producto.setNombre("Celular");
		producto.setPrecio(1800.0);

		ArrayList<Producto> lista = new ArrayList<>();
		lista.add(producto);

		when(productoRepository.obtenerProductos()).thenReturn(lista);

		ArrayList<ProductoDTO> resultado = productoService.obtenerTodos();

		assertEquals(1, resultado.size());
		assertEquals(1, resultado.get(0).getIdProducto());
		assertEquals("Celular", resultado.get(0).getNombre());
		assertEquals(1800.0, resultado.get(0).getPrecio());
	}

	@Test
	public void obtenerTodos_SinDatos() {

		when(productoRepository.obtenerProductos()).thenReturn(new ArrayList<>());

		assertThrows(RecursoSinDatosException.class, () -> productoService.obtenerTodos());
	}

	@Test
	public void obtenerPorId() {

		Producto producto = new Producto();
		producto.setIdProducto(5);
		producto.setNombre("Impresora");
		producto.setPrecio(950.0);

		when(productoRepository.existePorId(5)).thenReturn(producto);

		ProductoDTO dto = productoService.obtenerPorId(5);

		assertEquals(5, dto.getIdProducto());
		assertEquals("Impresora", dto.getNombre());
		assertEquals(950.0, dto.getPrecio());
	}

	@Test
	public void obtenerPorId_NoExiste() {

		when(productoRepository.existePorId(100)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> productoService.obtenerPorId(100));
	}

	@Test
	public void eliminarProducto() {

		Producto producto = new Producto();
		producto.setIdProducto(1);

		when(productoRepository.existePorId(1)).thenReturn(producto);

		productoService.eliminar(1);

		verify(productoRepository, times(1)).borrarProducto(1);
	}

	@Test
	public void eliminarProducto_NoExiste() {

		when(productoRepository.existePorId(1)).thenReturn(null);

		assertThrows(RecursoNoExistenteException.class, () -> productoService.eliminar(1));
	}

}
