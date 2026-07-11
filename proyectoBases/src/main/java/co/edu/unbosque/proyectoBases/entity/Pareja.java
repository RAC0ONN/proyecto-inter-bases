package co.edu.unbosque.proyectoBases.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pareja")
public class Pareja {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id_pareja")
	private int idPareja;

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

	@Column(name = "cupo_asignado")
	private double cupoAsignado;

	@Column(name = "correo_electronico", unique = true)
	private String correoElectronico;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "pareja")
	private List<Restriccion> restricciones;

	@OneToMany(mappedBy = "pareja")
	private List<Compra> compras;

	public Pareja() {
	}

	public Pareja(int idPareja, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String nombreUsuario, String contraseniaUsuario, double cupoAsignado,
			String correoElectronico, Cliente cliente) {
		super();
		this.idPareja = idPareja;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nombreUsuario = nombreUsuario;
		this.contraseniaUsuario = contraseniaUsuario;
		this.cupoAsignado = cupoAsignado;
		this.correoElectronico = correoElectronico;
		this.cliente = cliente;

		this.restricciones = new ArrayList<Restriccion>();
		this.compras = new ArrayList<Compra>();
	}

	public int getIdPareja() {
		return idPareja;
	}

	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public double getCupoAsignado() {
		return cupoAsignado;
	}

	public void setCupoAsignado(double cupoAsignado) {
		this.cupoAsignado = cupoAsignado;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public List<Restriccion> getRestricciones() {
		return restricciones;
	}

	public void setRestricciones(List<Restriccion> restricciones) {
		this.restricciones = restricciones;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, compras, contraseniaUsuario, correoElectronico, cupoAsignado, idPareja,
				nombreUsuario, primerApellido, primerNombre, restricciones, segundoApellido, segundoNombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pareja other = (Pareja) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(compras, other.compras)
				&& Objects.equals(contraseniaUsuario, other.contraseniaUsuario)
				&& Objects.equals(correoElectronico, other.correoElectronico)
				&& Double.doubleToLongBits(cupoAsignado) == Double.doubleToLongBits(other.cupoAsignado)
				&& idPareja == other.idPareja && Objects.equals(nombreUsuario, other.nombreUsuario)
				&& Objects.equals(primerApellido, other.primerApellido)
				&& Objects.equals(primerNombre, other.primerNombre)
				&& Objects.equals(restricciones, other.restricciones)
				&& Objects.equals(segundoApellido, other.segundoApellido)
				&& Objects.equals(segundoNombre, other.segundoNombre);

	}

}