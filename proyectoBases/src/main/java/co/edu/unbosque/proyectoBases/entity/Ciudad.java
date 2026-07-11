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
@Table(name = "Ciudad")
public class Ciudad {

	@Id

	@Column(name = "id_ciudad")
	private int idCiudad;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "ciudad")
	private List<Direccion> direcciones;

	public Ciudad() {
	}

	public Ciudad(int idCiudad, String nombre) {
		super();
		this.idCiudad = idCiudad;
		this.nombre = nombre;
		this.direcciones = new ArrayList<Direccion>();
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

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(direcciones, idCiudad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		return Objects.equals(direcciones, other.direcciones) && idCiudad == other.idCiudad
				&& Objects.equals(nombre, other.nombre);
	}
}