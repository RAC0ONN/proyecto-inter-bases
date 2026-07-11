package co.edu.unbosque.proyectoBases.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Direccion")
public class Direccion {

	@Id
	
	@Column(name = "id_direccion")
	private int idDireccion;

	@Column(name = "via_principal")
	private String viaPrincipal;

	@Column(name = "numero_via")
	private int numeroVia;

	@Column(name = "letra_via")
	private String letraVia;

	@Column(name = "sufijo")
	private String sufijo;

	@Column(name = "numero_generador")
	private int numeroGenerador;

	@Column(name = "placa")
	private Integer placa;
	@ManyToOne
	@JoinColumn(name = "id_barrio")
	private Barrio barrio;

	@ManyToOne
	@JoinColumn(name = "id_ciudad")
	private Ciudad ciudad;

	@OneToOne(mappedBy = "direccion")
	private Almacen almacen;

	public Direccion() {
	}

	public Direccion(int idDireccion, String viaPrincipal, int numeroVia, String letraVia, String sufijo,
			int numeroGenerador, Integer placa, Barrio barrio, Ciudad ciudad) {
		super();
		this.idDireccion = idDireccion;
		this.viaPrincipal = viaPrincipal;
		this.numeroVia = numeroVia;
		this.letraVia = letraVia;
		this.sufijo = sufijo;
		this.numeroGenerador = numeroGenerador;
		this.placa = placa;
		this.barrio = barrio;
		this.ciudad = ciudad;
	}

	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getViaPrincipal() {
		return viaPrincipal;
	}

	public void setViaPrincipal(String viaPrincipal) {
		this.viaPrincipal = viaPrincipal;
	}

	public int getNumeroVia() {
		return numeroVia;
	}

	public void setNumeroVia(int numeroVia) {
		this.numeroVia = numeroVia;
	}

	public String getLetraVia() {
		return letraVia;
	}

	public void setLetraVia(String letraVia) {
		this.letraVia = letraVia;
	}

	public String getSufijo() {
		return sufijo;
	}

	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}

	public int getNumeroGenerador() {
		return numeroGenerador;
	}

	public void setNumeroGenerador(int numeroGenerador) {
		this.numeroGenerador = numeroGenerador;
	}

	public Integer getPlaca() {
		return placa;
	}

	public void setPlaca(Integer placa) {
		this.placa = placa;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(almacen, barrio, ciudad, idDireccion, letraVia, numeroGenerador, numeroVia, placa, sufijo,
				viaPrincipal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return Objects.equals(almacen, other.almacen) && Objects.equals(barrio, other.barrio)
				&& Objects.equals(ciudad, other.ciudad) && idDireccion == other.idDireccion
				&& Objects.equals(letraVia, other.letraVia) && numeroGenerador == other.numeroGenerador
				&& numeroVia == other.numeroVia && Objects.equals(placa, other.placa)
				&& Objects.equals(sufijo, other.sufijo) && Objects.equals(viaPrincipal, other.viaPrincipal);
	}

}