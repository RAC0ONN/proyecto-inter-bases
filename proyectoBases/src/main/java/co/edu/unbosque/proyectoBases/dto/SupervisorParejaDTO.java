package co.edu.unbosque.proyectoBases.dto;

import java.util.Objects;

public class SupervisorParejaDTO {

	private int idSupervisorPareja;
	private int idSupervisor;
	private int idPareja;

	public SupervisorParejaDTO() {
	}

	public SupervisorParejaDTO(int idSupervisorPareja, int idSupervisor, int idPareja) {
		super();
		this.idSupervisorPareja = idSupervisorPareja;
		this.idSupervisor = idSupervisor;
		this.idPareja = idPareja;
	}

	public int getIdSupervisorPareja() {
		return idSupervisorPareja;
	}

	public void setIdSupervisorPareja(int idSupervisorPareja) {
		this.idSupervisorPareja = idSupervisorPareja;
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

	@Override
	public int hashCode() {
		return Objects.hash(idPareja, idSupervisor, idSupervisorPareja);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupervisorParejaDTO other = (SupervisorParejaDTO) obj;
		return idPareja == other.idPareja && idSupervisor == other.idSupervisor
				&& idSupervisorPareja == other.idSupervisorPareja;
	}

}