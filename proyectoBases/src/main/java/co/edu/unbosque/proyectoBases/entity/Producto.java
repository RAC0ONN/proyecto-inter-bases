package co.edu.unbosque.proyectoBases.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto {

	@Id

	@Column(name = "id_producto")
	private int idProducto;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private double precio; 
	
	@ManyToMany(mappedBy = "productos")
	private List<Compra> compras;

	public Producto() {
	}

	public Producto(int idProducto, String nombre, double precio) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.compras = new ArrayList<Compra>();
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	@Override
	public int hashCode() {
		return Objects.hash(compras, idProducto, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(compras, other.compras) && idProducto == other.idProducto
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}
}