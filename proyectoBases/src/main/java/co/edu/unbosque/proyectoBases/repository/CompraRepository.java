package co.edu.unbosque.proyectoBases.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Compra;
import jakarta.transaction.Transactional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

	@Query(value = "SELECT * FROM compra", nativeQuery = true)
	List<Compra> obtenerTodas();

	@Query(value = "SELECT * FROM COMPRA WHERE id_compra = ?1", nativeQuery = true)
	Compra obtenerPorId(int idCompra);

	@Query(value = "SELECT * FROM COMPRA WHERE id_pareja = ?1", nativeQuery = true)
	ArrayList<Compra> obtenerPorPareja(int idPareja);

	@Query(value = "SELECT COALESCE(SUM(monto), 0) FROM COMPRA WHERE id_pareja = ?1", nativeQuery = true)
	double obtenerMontoTotalPorPareja(int idPareja);

	@Query(value = "SELECT COALESCE(MAX(id_compra), 0) + 1 FROM COMPRA", nativeQuery = true)
	int obtenerSiguienteId();

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO COMPRA ( hora, monto, fecha, id_pareja, id_almacen)
			VALUES (?1, ?2, ?3, ?4, ?5)
			""", nativeQuery = true)
	void crearCompra(LocalTime hora, double monto, LocalDate fecha, int idPareja, int idAlmacen);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM COMPRA WHERE id_compra = ?1", nativeQuery = true)
	void eliminarCompra(int idCompra);
}