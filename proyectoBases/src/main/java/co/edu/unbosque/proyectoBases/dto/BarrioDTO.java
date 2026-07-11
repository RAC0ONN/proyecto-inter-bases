package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class BarrioDTO {
	private int idBarrio;
	private String nombre;

	public BarrioDTO() {

	}

	public BarrioDTO(int idBarrio, String nombre) {
		super();
		this.idBarrio = idBarrio;
		this.nombre = nombre;
	}

	public int getIdBarrio() {
		return idBarrio;
	}

	public void setIdBarrio(int idBarrio) {
		this.idBarrio = idBarrio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idBarrio, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BarrioDTO other = (BarrioDTO) obj;
		return idBarrio == other.idBarrio && Objects.equals(nombre, other.nombre);
	}

}
