package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class AlmacenDTO {

	private int idAlmacen;
	private String nombre;
	private int idDireccion;

	public AlmacenDTO() {
	}

	public AlmacenDTO(int idAlmacen, String nombre, int idDireccion) {
		super();
		this.idAlmacen = idAlmacen;
		this.nombre = nombre;
		this.idDireccion = idDireccion;
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAlmacen, idDireccion, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlmacenDTO other = (AlmacenDTO) obj;
		return idAlmacen == other.idAlmacen && idDireccion == other.idDireccion && Objects.equals(nombre, other.nombre);
	}

}