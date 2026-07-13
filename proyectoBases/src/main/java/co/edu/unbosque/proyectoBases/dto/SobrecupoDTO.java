package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class SobrecupoDTO {

	private int idSobrecupo;
	private int idSupervisor;
	private int idPareja;
	private int idAlmacen;
	private String estadoSobrecupo;
	private double montoSobrecupo;

	public SobrecupoDTO() {
	}

	public SobrecupoDTO(int idSobrecupo, int idSupervisor, int idPareja, String estadoSobrecupo,
			double montoSobrecupo) {
		super();
		this.idSobrecupo = idSobrecupo;
		this.idSupervisor = idSupervisor;
		this.idPareja = idPareja;
		this.estadoSobrecupo = estadoSobrecupo;
		this.montoSobrecupo = montoSobrecupo;
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public int getIdSobrecupo() {
		return idSobrecupo;
	}

	public void setIdSobrecupo(int idSobrecupo) {
		this.idSobrecupo = idSobrecupo;
	}

	public int getIdSupervisor() {
		return idSupervisor;
	}

	public void setIdSupervisor(int idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

	public int getIdPareja() {
		return idPareja;
	}

	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
	}

	public String getEstadoSobrecupo() {
		return estadoSobrecupo;
	}

	public void setEstadoSobrecupo(String estadoSobrecupo) {
		this.estadoSobrecupo = estadoSobrecupo;
	}

	public double getMontoSobrecupo() {
		return montoSobrecupo;
	}

	public void setMontoSobrecupo(double montoSobrecupo) {
		this.montoSobrecupo = montoSobrecupo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estadoSobrecupo, idAlmacen, idPareja, idSobrecupo, idSupervisor, montoSobrecupo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SobrecupoDTO other = (SobrecupoDTO) obj;
		return Objects.equals(estadoSobrecupo, other.estadoSobrecupo) && idAlmacen == other.idAlmacen
				&& idPareja == other.idPareja && idSobrecupo == other.idSobrecupo && idSupervisor == other.idSupervisor
				&& Double.doubleToLongBits(montoSobrecupo) == Double.doubleToLongBits(other.montoSobrecupo);
	}

}