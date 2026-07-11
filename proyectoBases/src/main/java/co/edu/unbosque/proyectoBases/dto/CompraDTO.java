package co.edu.unbosque.proyectoBases.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class CompraDTO {

	private int idCompra;
	private LocalTime hora;
	private double monto;
	private LocalDate fecha;
	private int idPareja;
	private int idAlmacen;

	public CompraDTO() {
	}

	public CompraDTO(int idCompra, LocalTime hora, double monto, LocalDate fecha, int idPareja, int idAlmacen) {
		super();
		this.idCompra = idCompra;
		this.hora = hora;
		this.monto = monto;
		this.fecha = fecha;
		this.idPareja = idPareja;
		this.idAlmacen = idAlmacen;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getIdPareja() {
		return idPareja;
	}

	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, hora, idAlmacen, idCompra, idPareja, monto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompraDTO other = (CompraDTO) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora) && idAlmacen == other.idAlmacen
				&& idCompra == other.idCompra && idPareja == other.idPareja
				&& Double.doubleToLongBits(monto) == Double.doubleToLongBits(other.monto);
	}

}