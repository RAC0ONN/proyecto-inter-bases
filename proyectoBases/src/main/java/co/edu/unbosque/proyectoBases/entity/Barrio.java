package co.edu.unbosque.proyectoBases.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Barrio")
public class Barrio {
	@Id

	@Column(name = "id_barrio")
	private int idBarrio;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany(mappedBy = "barrio")
	private List<Direccion> direcciones;

	public Barrio() {

	}

	public Barrio(int idBarrio, String nombre, List<Direccion> direcciones) {
		super();
		this.idBarrio = idBarrio;
		this.nombre = nombre;
		this.direcciones = new ArrayList<Direccion>();
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

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(direcciones, idBarrio, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barrio other = (Barrio) obj;
		return Objects.equals(direcciones, other.direcciones) && idBarrio == other.idBarrio
				&& Objects.equals(nombre, other.nombre);
	}

}
