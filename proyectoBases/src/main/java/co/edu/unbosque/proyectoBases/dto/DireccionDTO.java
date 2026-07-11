package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class DireccionDTO {
	private int idDireccion;
	private String viaPrincipal;
	private int numeroVia;
	private String letraVia;
	private String sufijo;
	private int numeroGenerador;
	private Integer placa;
	private int idBarrio;
	private int idCiudad;

	public DireccionDTO() {

	}

	public DireccionDTO(int idDireccion, String viaPrincipal, int numeroVia, String letraVia, String sufijo,
			int numeroGenerador, Integer placa, int idBarrio, int idCiudad) {
		super();
		this.idDireccion = idDireccion;
		this.viaPrincipal = viaPrincipal;
		this.numeroVia = numeroVia;
		this.letraVia = letraVia;
		this.sufijo = sufijo;
		this.numeroGenerador = numeroGenerador;
		this.placa = placa;
		this.idBarrio = idBarrio;
		this.idCiudad = idCiudad;
	}

	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
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

	public int getIdBarrio() {
		return idBarrio;
	}

	public void setIdBarrio(int idBarrio) {
		this.idBarrio = idBarrio;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idBarrio, idCiudad, idDireccion, letraVia, numeroGenerador, numeroVia, placa, sufijo,
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
		DireccionDTO other = (DireccionDTO) obj;
		return idBarrio == other.idBarrio && idCiudad == other.idCiudad && idDireccion == other.idDireccion
				&& Objects.equals(letraVia, other.letraVia) && numeroGenerador == other.numeroGenerador
				&& numeroVia == other.numeroVia && Objects.equals(placa, other.placa)
				&& Objects.equals(sufijo, other.sufijo) && Objects.equals(viaPrincipal, other.viaPrincipal);
	}

}
