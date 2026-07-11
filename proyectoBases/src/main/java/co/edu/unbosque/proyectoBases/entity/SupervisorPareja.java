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
@Table(name = "SupervisorPareja")
public class SupervisorPareja {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id_supervisor_pareja")
	private int idSupervisorPareja;

	@ManyToOne
	@JoinColumn(name = "id_supervisor")
	private Supervisor supervisor;

	@ManyToOne
	@JoinColumn(name = "id_pareja")
	private Pareja pareja;

	public SupervisorPareja() {
	}

	public SupervisorPareja(int idSupervisorPareja, Supervisor supervisor, Pareja pareja) {
		super();
		this.idSupervisorPareja = idSupervisorPareja;
		this.supervisor = supervisor;
		this.pareja = pareja;
	}

	public int getIdSupervisorPareja() {
		return idSupervisorPareja;
	}

	public void setIdSupervisorPareja(int idSupervisorPareja) {
		this.idSupervisorPareja = idSupervisorPareja;
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

	@Override
	public int hashCode() {
		return Objects.hash(idSupervisorPareja, pareja, supervisor);
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
		return idSupervisorPareja == other.idSupervisorPareja && Objects.equals(pareja, other.pareja)
				&& Objects.equals(supervisor, other.supervisor);
	}
}