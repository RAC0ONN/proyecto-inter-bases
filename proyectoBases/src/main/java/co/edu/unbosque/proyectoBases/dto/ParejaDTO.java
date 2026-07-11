package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class ParejaDTO {

	private int idPareja;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String nombreUsuario;
	private String contraseniaUsuario;
	private double cupoAsignado;
	private String correoElectronico;
	private int idCliente;

	public ParejaDTO() {
	}

	public ParejaDTO(int idPareja, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String nombreUsuario, String contraseniaUsuario, double cupoAsignado,
			String correoElectronico, int idCliente) {
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
		this.idCliente = idCliente;
	}

	public int getIdPareja() {
		return idPareja;
	}

	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contraseniaUsuario, correoElectronico, cupoAsignado, idCliente, idPareja, nombreUsuario,
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
		ParejaDTO other = (ParejaDTO) obj;
		return Objects.equals(contraseniaUsuario, other.contraseniaUsuario)
				&& Objects.equals(correoElectronico, other.correoElectronico)
				&& Double.doubleToLongBits(cupoAsignado) == Double.doubleToLongBits(other.cupoAsignado)
				&& idCliente == other.idCliente && idPareja == other.idPareja
				&& Objects.equals(nombreUsuario, other.nombreUsuario)
				&& Objects.equals(primerApellido, other.primerApellido)
				&& Objects.equals(primerNombre, other.primerNombre)
				&& Objects.equals(segundoApellido, other.segundoApellido)
				&& Objects.equals(segundoNombre, other.segundoNombre);
	}

}