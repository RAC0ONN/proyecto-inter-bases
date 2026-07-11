package co.edu.unbosque.proyectoBases.entity;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Compra_Producto")
public class CompraProducto {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id_compra_producto")
	private int idCompraProducto;

	@ManyToOne
	@JoinColumn(name = "id_compra")
	private Compra compra;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	public CompraProducto() {
	}

	public CompraProducto(int idCompraProducto, Compra compra, Producto producto) {
		super();
		this.idCompraProducto = idCompraProducto;
		this.compra = compra;
		this.producto = producto;
	}

	public int getIdCompraProducto() {
		return idCompraProducto;
	}

	public void setIdCompraProducto(int idCompraProducto) {
		this.idCompraProducto = idCompraProducto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(compra, idCompraProducto, producto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompraProducto other = (CompraProducto) obj;
		return Objects.equals(compra, other.compra) && idCompraProducto == other.idCompraProducto
				&& Objects.equals(producto, other.producto);
	}

}