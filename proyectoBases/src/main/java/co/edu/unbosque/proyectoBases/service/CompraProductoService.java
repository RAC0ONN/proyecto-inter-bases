package co.edu.unbosque.proyectoBases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectoBases.dto.CompraProductoDTO;
import co.edu.unbosque.proyectoBases.entity.CompraProducto;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;
import co.edu.unbosque.proyectoBases.repository.CompraProductoRepository;

@Service
public class CompraProductoService {

	@Autowired
	private CompraProductoRepository compraProductoRepository;

	public void crear(CompraProductoDTO dto) {
		compraProductoRepository.crearCompraProducto(dto.getIdCompraProducto(), dto.getIdCompra(), dto.getIdProducto());
	}

	public ArrayList<CompraProductoDTO> obtenerTodas() {
		List<CompraProducto> entidades = compraProductoRepository.findAll();
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay registros de productos comprados");
		}
		ArrayList<CompraProductoDTO> resultado = new ArrayList<>();
		for (CompraProducto cp : entidades) {
			resultado.add(mapear(cp));
		}
		return resultado;
	}

	public CompraProductoDTO obtenerPorId(int idCompraProducto) {
		CompraProducto entidad = compraProductoRepository.obtenerPorId(idCompraProducto);
		if (entidad == null) {
			throw new RecursoNoExistenteException("No existe el registro de compra con ese id");
		}
		return mapear(entidad);
	}

	public ArrayList<CompraProductoDTO> obtenerPorCompra(int idCompra) {
		ArrayList<CompraProducto> entidades = compraProductoRepository.obtenerPorCompra(idCompra);
		if (entidades == null || entidades.isEmpty()) {
			throw new RecursoSinDatosException("No hay productos en esa compra");
		}
		ArrayList<CompraProductoDTO> resultado = new ArrayList<>();
		for (CompraProducto cp : entidades) {
			resultado.add(mapear(cp));
		}
		return resultado;
	}

	public void eliminar(int idCompraProducto) {
		if (compraProductoRepository.obtenerPorId(idCompraProducto) == null) {
			throw new RecursoNoExistenteException("No existe el registro de compra con ese id");
		}
		compraProductoRepository.eliminarCompraProducto(idCompraProducto);
	}

	private CompraProductoDTO mapear(CompraProducto cp) {
		CompraProductoDTO dto = new CompraProductoDTO();
		dto.setIdCompraProducto(cp.getIdCompraProducto());
		if (cp.getCompra() != null) {
			dto.setIdCompra(cp.getCompra().getIdCompra());
		}
		if (cp.getProducto() != null) {
			dto.setIdProducto(cp.getProducto().getIdProducto());
		}
		return dto;
	}
}