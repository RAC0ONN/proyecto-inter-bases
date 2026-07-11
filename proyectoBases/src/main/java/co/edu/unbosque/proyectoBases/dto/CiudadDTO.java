package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class CiudadDTO {
	private int idCiudad;
	private String nombre;

	public CiudadDTO() {

	}

	public CiudadDTO(int idCiudad, String nombre) {
		super();
		this.idCiudad = idCiudad;
		this.nombre = nombre;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCiudad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CiudadDTO other = (CiudadDTO) obj;
		return idCiudad == other.idCiudad && Objects.equals(nombre, other.nombre);
	}

}
