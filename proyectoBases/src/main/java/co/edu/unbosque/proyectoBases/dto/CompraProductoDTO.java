package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class CompraProductoDTO {

	private int idCompraProducto;
	private int idCompra;
	private int idProducto;

	public CompraProductoDTO() {
	}

	public CompraProductoDTO(int idCompraProducto, int idCompra, int idProducto) {
		super();
		this.idCompraProducto = idCompraProducto;
		this.idCompra = idCompra;
		this.idProducto = idProducto;
	}

	public int getIdCompraProducto() {
		return idCompraProducto;
	}

	public void setIdCompraProducto(int idCompraProducto) {
		this.idCompraProducto = idCompraProducto;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCompra, idCompraProducto, idProducto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompraProductoDTO other = (CompraProductoDTO) obj;
		return idCompra == other.idCompra && idCompraProducto == other.idCompraProducto
				&& idProducto == other.idProducto;
	}

}