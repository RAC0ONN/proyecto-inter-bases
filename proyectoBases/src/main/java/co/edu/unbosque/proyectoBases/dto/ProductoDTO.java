package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class ProductoDTO {
	private int idProducto;
	private String nombre;
	private double precio;

	public ProductoDTO() {

	}

	public ProductoDTO(int idProducto, String nombre, double precio) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
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

	@Override
	public int hashCode() {
		return Objects.hash(idProducto, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoDTO other = (ProductoDTO) obj;
		return idProducto == other.idProducto && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}

}
