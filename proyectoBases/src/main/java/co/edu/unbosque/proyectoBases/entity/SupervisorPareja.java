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
@Table(name = "Supervisor_Pareja")
public class SupervisorPareja {

	@Id
	@Column(name = "id_pareja")
	private int idPareja;

	@ManyToOne
	@JoinColumn(name = "id_pareja", insertable = false, updatable = false)
	private Pareja pareja;

	@ManyToOne
	@JoinColumn(name = "id_supervisor")
	private Supervisor supervisor;

	public SupervisorPareja() {
	}

	public SupervisorPareja(int idPareja, Pareja pareja, Supervisor supervisor) {
		super();
		this.idPareja = idPareja;
		this.pareja = pareja;
		this.supervisor = supervisor;
	}

	public int getIdPareja() {
		return idPareja;
	}

	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
	}

	public Pareja getPareja() {
		return pareja;
	}

	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPareja, pareja, supervisor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupervisorPareja other = (SupervisorPareja) obj;
		return idPareja == other.idPareja && Objects.equals(pareja, other.pareja)
				&& Objects.equals(supervisor, other.supervisor);
	}
}