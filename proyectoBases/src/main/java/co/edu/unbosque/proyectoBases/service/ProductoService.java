package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.ProductoDTO;
import co.edu.unbosque.proyectoBases.entity.Producto;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	public void crear(ProductoDTO dto) {
		productoRepository.crearProducto(dto.getIdProducto(), dto.getNombre(), dto.getPrecio());
	}

	public void actualizar(ProductoDTO dto) {
		if (productoRepository.existePorId(dto.getIdProducto()) == null) {
			throw new RecursoNoExistenteException("Error, el producto no existe");
		}
		productoRepository.actualizarProducto(dto.getNombre(), dto.getPrecio(), dto.getIdProducto());
	}

	public ArrayList<ProductoDTO> obtenerTodos() {
		List<Producto> entidades = productoRepository.obtenerProductos();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay productos");
		}
		ArrayList<ProductoDTO> resultado = new ArrayList<>();
		for (Producto p : entidades) {
			resultado.add(mapear(p));
		}
		return resultado;
	}

	public ProductoDTO obtenerPorId(int idProducto) {
		Producto entidad = productoRepository.existePorId(idProducto);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe un producto con ese id");
		}
		return mapear(entidad);
	}

	public void eliminar(int idProducto) {
		if (productoRepository.existePorId(idProducto) == null) {
			throw new RecursoNoExistenteException("No existe un producto con ese id");
		}
		productoRepository.borrarProducto(idProducto);
	}

	private ProductoDTO mapear(Producto p) {
		ProductoDTO dto = new ProductoDTO();
		dto.setIdProducto(p.getIdProducto());
		dto.setNombre(p.getNombre());
		dto.setPrecio(p.getPrecio());
		return dto;
	}
}