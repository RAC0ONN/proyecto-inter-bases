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
@Table(name = "Cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private int idCliente;

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

	@Column(name = "cupo_total")
	private double cupoTotal;

	@OneToMany(mappedBy = "cliente")
	private List<Pareja> parejas;

	public Cliente() {
	}

	public Cliente(int idCliente, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String nombreUsuario, String contraseniaUsuario, String correoElectronico,
			double cupoTotal) {
		super();
		this.idCliente = idCliente;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nombreUsuario = nombreUsuario;
		this.contraseniaUsuario = contraseniaUsuario;
		this.correoElectronico = correoElectronico;
		this.cupoTotal = cupoTotal;
		this.parejas = new ArrayList<Pareja>();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public double getCupoTotal() {
		return cupoTotal;
	}

	public void setCupoTotal(double cupoTotal) {
		this.cupoTotal = cupoTotal;
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contraseniaUsuario, correoElectronico, cupoTotal, idCliente, nombreUsuario, parejas,
				primerApellido, primerNombre, segundoApellido, segundoNombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(contraseniaUsuario, other.contraseniaUsuario)
				&& Objects.equals(correoElectronico, other.correoElectronico)
				&& Double.doubleToLongBits(cupoTotal) == Double.doubleToLongBits(other.cupoTotal)
				&& idCliente == other.idCliente && Objects.equals(nombreUsuario, other.nombreUsuario)
				&& Objects.equals(parejas, other.parejas) && Objects.equals(primerApellido, other.primerApellido)
				&& Objects.equals(primerNombre, other.primerNombre)
				&& Objects.equals(segundoApellido, other.segundoApellido)
				&& Objects.equals(segundoNombre, other.segundoNombre);
	}

}