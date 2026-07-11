package co.edu.unbosque.proyectoBases.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Almacen")
public class Almacen {

	@Id

	@Column(name = "id_almacen")
	private int idAlmacen;

	@Column(name = "nombre")
	private String nombre;

	@OneToOne
	@JoinColumn(name = "id_direccion")
	private Direccion direccion;

	@OneToMany(mappedBy = "almacen")
	private List<Supervisor> supervisores;

	@OneToMany(mappedBy = "almacen")
	private List<Compra> compras;

	public Almacen() {
	}

	public Almacen(int idAlmacen, String nombre, Direccion direccion) {
		super();
		this.idAlmacen = idAlmacen;
		this.nombre = nombre;
		this.direccion = direccion;
		this.supervisores = new ArrayList<Supervisor>();
		this.compras = new ArrayList<Compra>();
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

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public List<Supervisor> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(List<Supervisor> supervisores) {
		this.supervisores = supervisores;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	@Override
	public int hashCode() {
		return Objects.hash(compras, direccion, idAlmacen, nombre, supervisores);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Almacen other = (Almacen) obj;
		return Objects.equals(compras, other.compras) && Objects.equals(direccion, other.direccion)
				&& idAlmacen == other.idAlmacen && Objects.equals(nombre, other.nombre)
				&& Objects.equals(supervisores, other.supervisores);
	}

}