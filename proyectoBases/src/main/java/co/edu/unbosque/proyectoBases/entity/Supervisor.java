package co.edu.unbosque.proyectoBases.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Supervisor")
public class Supervisor {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id_supervisor")
	private int idSupervisor;

	@Column(name = "primer_nombre")
	private String primerNombre;

	@Column(name = "segundo_nombre")
	private String segundoNombre;

	@Column(name = "primer_apellido")
	private String primerApellido;

	@Column(name = "segundo_apellido")
	private String segundoApellido;

	@Column(name = "nombre_usuario", unique = true)
	private String nombreUsuario;

	@Column(name = "contrasenia_usuario")
	private String contraseniaUsuario;

	@Column(name = "correo_electronico", unique = true)
	private String correoElectronico;

	@ManyToOne
	@JoinColumn(name = "id_almacen")
	private Almacen almacen;

	/*
	 * @ManyToMany
	 * 
	 * @JoinTable(name = "Supervisor_Pareja", joinColumns = @JoinColumn(name =
	 * "id_supervisor"), inverseJoinColumns = @JoinColumn(name = "id_pareja") )
	 * private List<Pareja> parejas;
	 */
	public Supervisor() {
	}

	public Supervisor(int idSupervisor, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String nombreUsuario, String contraseniaUsuario, String correoElectronico,
			Almacen almacen) {
		super();
		this.idSupervisor = idSupervisor;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nombreUsuario = nombreUsuario;
		this.contraseniaUsuario = contraseniaUsuario;
		this.correoElectronico = correoElectronico;
		this.almacen = almacen;
		// this.parejas = new ArrayList<Pareja>();
	}

	public int getIdSupervisor() {
		return idSupervisor;
	}

	public void setIdSupervisor(int idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseniaUsuario() {
		return contraseniaUsuario;
	}

	public void setContraseniaUsuario(String contraseniaUsuario) {
		this.contraseniaUsuario = contraseniaUsuario;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	/*
	 * public List<Pareja> getParejas() { return parejas; }
	 * 
	 * public void setParejas(List<Pareja> parejas) { this.parejas = parejas; }
	 */
	@Override
	public int hashCode() {
		return Objects.hash(almacen, contraseniaUsuario, correoElectronico, idSupervisor, nombreUsuario, primerApellido,
				primerNombre, segundoApellido, segundoNombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supervisor other = (Supervisor) obj;
		return Objects.equals(almacen, other.almacen) && Objects.equals(contraseniaUsuario, other.contraseniaUsuario)
				&& Objects.equals(correoElectronico, other.correoElectronico) && idSupervisor == other.idSupervisor
				&& Objects.equals(nombreUsuario, other.nombreUsuario)
				&& Objects.equals(primerApellido, other.primerApellido)
				&& Objects.equals(primerNombre, other.primerNombre)
				&& Objects.equals(segundoApellido, other.segundoApellido)
				&& Objects.equals(segundoNombre, other.segundoNombre);
	}

}