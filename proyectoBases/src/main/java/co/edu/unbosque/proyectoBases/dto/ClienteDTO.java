package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class ClienteDTO {
	private int idCliente;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String nombreUsuario;
	private String contraseniaUsuario;
	private String correoElectronico;
	private double cupoTotal;

	public ClienteDTO() {

	}

	public ClienteDTO(int idCliente, String primerNombre, String segundoNombre, String primerApellido,
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

	@Override
	public int hashCode() {
		return Objects.hash(contraseniaUsuario, correoElectronico, cupoTotal, idCliente, nombreUsuario, primerApellido,
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
		ClienteDTO other = (ClienteDTO) obj;
		return Objects.equals(contraseniaUsuario, other.contraseniaUsuario)
				&& Objects.equals(correoElectronico, other.correoElectronico)
				&& Double.doubleToLongBits(cupoTotal) == Double.doubleToLongBits(other.cupoTotal)
				&& idCliente == other.idCliente && Objects.equals(nombreUsuario, other.nombreUsuario)
				&& Objects.equals(primerApellido, other.primerApellido)
				&& Objects.equals(primerNombre, other.primerNombre)
				&& Objects.equals(segundoApellido, other.segundoApellido)
				&& Objects.equals(segundoNombre, other.segundoNombre);
	}

}
