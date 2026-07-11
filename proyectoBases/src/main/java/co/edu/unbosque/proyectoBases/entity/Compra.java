package co.edu.unbosque.proyectoBases.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id_compra")
	private int idCompra;

	@Column(name = "hora")
	private LocalTime hora;

	@Column(name = "monto")
	private double monto; 

	@Column(name = "fecha")
	private LocalDate fecha;

	@ManyToOne
	@JoinColumn(name = "id_pareja")
	private Pareja pareja;

	@ManyToOne
	@JoinColumn(name = "id_almacen")
	private Almacen almacen;

	@ManyToMany
	@JoinTable(name = "Compra_Producto", 
		joinColumns = @JoinColumn(name = "id_compra"), 
		inverseJoinColumns = @JoinColumn(name = "id_producto")
	)
	private List<Producto> productos;

	public Compra() {
	}

	public Compra(int idCompra, LocalTime hora, double monto, LocalDate fecha, Pareja pareja, Almacen almacen) {
		super();
		this.idCompra = idCompra;
		this.hora = hora;
		this.monto = monto;
		this.fecha = fecha;
		this.pareja = pareja;
		this.almacen = almacen;
		this.productos = new ArrayList<Producto>();
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

	public Pareja getPareja() {
		return pareja;
	}

	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(almacen, fecha, hora, idCompra, monto, pareja, productos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		return Objects.equals(almacen, other.almacen) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(hora, other.hora) && idCompra == other.idCompra
				&& Double.doubleToLongBits(monto) == Double.doubleToLongBits(other.monto)
				&& Objects.equals(pareja, other.pareja) && Objects.equals(productos, other.productos);
	}

}