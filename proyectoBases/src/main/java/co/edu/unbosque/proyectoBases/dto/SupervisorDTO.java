package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class SupervisorDTO {

	private int idSupervisor;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String nombreUsuario;
	private String contraseniaUsuario;
	private String correoElectronico;
	private int idAlmacen;

	public SupervisorDTO() {
	}

	public SupervisorDTO(int idSupervisor, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String nombreUsuario, String contraseniaUsuario, String correoElectronico,
			int idAlmacen) {
		super();
		this.idSupervisor = idSupervisor;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.nombreUsuario = nombreUsuario;
		this.contraseniaUsuario = contraseniaUsuario;
		this.correoElectronico = correoElectronico;
		this.idAlmacen = idAlmacen;
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

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contraseniaUsuario, correoElectronico, idAlmacen, idSupervisor, nombreUsuario,
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
		SupervisorDTO other = (SupervisorDTO) obj;
		return Objects.equals(contraseniaUsuario, other.contraseniaUsuario)
				&& Objects.equals(correoElectronico, other.correoElectronico) && idAlmacen == other.idAlmacen
				&& idSupervisor == other.idSupervisor && Objects.equals(nombreUsuario, other.nombreUsuario)
				&& Objects.equals(primerApellido, other.primerApellido)
				&& Objects.equals(primerNombre, other.primerNombre)
				&& Objects.equals(segundoApellido, other.segundoApellido)
				&& Objects.equals(segundoNombre, other.segundoNombre);
	}

}