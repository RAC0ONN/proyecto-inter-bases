package co.edu.unbosque.proyectoBases.entity;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sobrecupo")
public class Sobrecupo {

	@Id
	@Column(name = "id_sobrecupo")
	private int idSobrecupo;

	@ManyToOne
	@JoinColumn(name = "id_supervisor")
	private Supervisor supervisor;

	@ManyToOne
	@JoinColumn(name = "id_pareja")
	private Pareja pareja;

	@Column(name = "estado_sobrecupo")
	private String estadoSobrecupo;

	@Column(name = "monto_sobrecupo")
	private double montoSobrecupo;

	public Sobrecupo() {
	}

	public Sobrecupo(int idSobrecupo, Supervisor supervisor, Pareja pareja, String estadoSobrecupo,
			double montoSobrecupo) {
		super();
		this.idSobrecupo = idSobrecupo;
		this.supervisor = supervisor;
		this.pareja = pareja;
		this.estadoSobrecupo = estadoSobrecupo;
		this.montoSobrecupo = montoSobrecupo;
	}

	public int getIdSobrecupo() {
		return idSobrecupo;
	}

	public void setIdSobrecupo(int idSobrecupo) {
		this.idSobrecupo = idSobrecupo;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public Pareja getPareja() {
		return pareja;
	}

	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
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
		return Objects.hash(estadoSobrecupo, idSobrecupo, montoSobrecupo, pareja, supervisor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sobrecupo other = (Sobrecupo) obj;
		return Objects.equals(estadoSobrecupo, other.estadoSobrecupo) && idSobrecupo == other.idSobrecupo
				&& Double.doubleToLongBits(montoSobrecupo) == Double.doubleToLongBits(other.montoSobrecupo)
				&& Objects.equals(pareja, other.pareja) && Objects.equals(supervisor, other.supervisor);
	}
}