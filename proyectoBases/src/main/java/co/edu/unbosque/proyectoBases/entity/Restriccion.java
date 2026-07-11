package co.edu.unbosque.proyectoBases.entity;

import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Restriccion")
public class Restriccion {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id_restriccion")
	private int idRestriccion;

	@Column(name = "dia_semana")
	private String diaSemana;

	@Column(name = "hora_inicio")
	private LocalTime horaInicio;

	@Column(name = "hora_fin")
	private LocalTime horaFin;

	@ManyToOne
	@JoinColumn(name = "id_pareja")
	private Pareja pareja;

	public Restriccion() {
	}

	public Restriccion(int idRestriccion, String diaSemana, LocalTime horaInicio, LocalTime horaFin, Pareja pareja) {
		super();
		this.idRestriccion = idRestriccion;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.pareja = pareja;
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

	public Pareja getPareja() {
		return pareja;
	}

	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diaSemana, horaFin, horaInicio, idRestriccion, pareja);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restriccion other = (Restriccion) obj;
		return Objects.equals(diaSemana, other.diaSemana) && Objects.equals(horaFin, other.horaFin)
				&& Objects.equals(horaInicio, other.horaInicio) && idRestriccion == other.idRestriccion
				&& Objects.equals(pareja, other.pareja);
	}

}