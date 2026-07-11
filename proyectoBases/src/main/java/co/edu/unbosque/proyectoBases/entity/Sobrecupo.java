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
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id_sobrecupo")
	private int idSobrecupo;

	@Column(name = "porcentaje_sobrecupo")
	private double porcentajeSobrecupo;

	@Column(name = "valor_maximo")
	private double valorMaximo;

	@ManyToOne
	@JoinColumn(name = "id_pareja")
	private Pareja pareja;

	public Sobrecupo() {
	}

	public Sobrecupo(int idSobrecupo, double porcentajeSobrecupo, double valorMaximo, Pareja pareja) {
		super();
		this.idSobrecupo = idSobrecupo;
		this.porcentajeSobrecupo = porcentajeSobrecupo;
		this.valorMaximo = valorMaximo;
		this.pareja = pareja;
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

	public Pareja getPareja() {
		return pareja;
	}

	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSobrecupo, pareja, porcentajeSobrecupo, valorMaximo);
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
		return idSobrecupo == other.idSobrecupo && Objects.equals(pareja, other.pareja)
				&& Double.doubleToLongBits(porcentajeSobrecupo) == Double.doubleToLongBits(other.porcentajeSobrecupo)
				&& Double.doubleToLongBits(valorMaximo) == Double.doubleToLongBits(other.valorMaximo);
	}
}