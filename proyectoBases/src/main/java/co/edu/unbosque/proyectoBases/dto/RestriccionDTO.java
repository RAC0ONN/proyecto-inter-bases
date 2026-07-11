package co.edu.unbosque.proyectoBases.dto;

import java.time.LocalTime;
import java.util.Objects;

public class RestriccionDTO {

	private int idRestriccion;
	private String diaSemana;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private int idPareja;

	public RestriccionDTO() {
	}

	public RestriccionDTO(int idRestriccion, String diaSemana, LocalTime horaInicio, LocalTime horaFin, int idPareja) {
		super();
		this.idRestriccion = idRestriccion;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.idPareja = idPareja;
	}

	public int getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(int idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public int getIdPareja() {
		return idPareja;
	}

	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diaSemana, horaFin, horaInicio, idRestriccion, idPareja);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestriccionDTO other = (RestriccionDTO) obj;
		return Objects.equals(diaSemana, other.diaSemana) && Objects.equals(horaFin, other.horaFin)
				&& Objects.equals(horaInicio, other.horaInicio) && idRestriccion == other.idRestriccion
				&& idPareja == other.idPareja;
	}

}