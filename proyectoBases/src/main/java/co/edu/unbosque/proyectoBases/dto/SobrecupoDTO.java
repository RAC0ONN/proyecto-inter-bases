package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class SobrecupoDTO {

	private int idSobrecupo;
	private double porcentajeSobrecupo;
	private double valorMaximo;
	private int idPareja;

	public SobrecupoDTO() {
	}

	public SobrecupoDTO(int idSobrecupo, double porcentajeSobrecupo, double valorMaximo, int idPareja) {
		super();
		this.idSobrecupo = idSobrecupo;
		this.porcentajeSobrecupo = porcentajeSobrecupo;
		this.valorMaximo = valorMaximo;
		this.idPareja = idPareja;
	}

	public int getIdSobrecupo() {
		return idSobrecupo;
	}

	public void setIdSobrecupo(int idSobrecupo) {
		this.idSobrecupo = idSobrecupo;
	}

	public double getPorcentajeSobrecupo() {
		return porcentajeSobrecupo;
	}

	public void setPorcentajeSobrecupo(double porcentajeSobrecupo) {
		this.porcentajeSobrecupo = porcentajeSobrecupo;
	}

	public double getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public int getIdPareja() {
		return idPareja;
	}

	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPareja, idSobrecupo, porcentajeSobrecupo, valorMaximo);
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
		return idPareja == other.idPareja && idSobrecupo == other.idSobrecupo
				&& Double.doubleToLongBits(porcentajeSobrecupo) == Double.doubleToLongBits(other.porcentajeSobrecupo)
				&& Double.doubleToLongBits(valorMaximo) == Double.doubleToLongBits(other.valorMaximo);
	}

}