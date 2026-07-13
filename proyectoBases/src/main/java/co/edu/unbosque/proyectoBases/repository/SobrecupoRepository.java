package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Sobrecupo;
import jakarta.transaction.Transactional;

@Repository
public interface SobrecupoRepository extends JpaRepository<Sobrecupo, Integer> {

	@Query(value = "SELECT * FROM SOBRECUPO WHERE id_sobrecupo = ?1", nativeQuery = true)
	Sobrecupo obtenerPorId(int idSobrecupo);

	@Query(value = "SELECT * FROM SOBRECUPO WHERE id_pareja = ?1", nativeQuery = true)
	ArrayList<Sobrecupo> obtenerPorPareja(int idPareja);

	@Query(value = "SELECT * FROM SOBRECUPO WHERE id_supervisor = ?1 ORDER BY id_sobrecupo DESC", nativeQuery = true)
	ArrayList<Sobrecupo> obtenerPorSupervisor(int idSupervisor);

	@Query(value = """
			SELECT s.* FROM SOBRECUPO s
			JOIN PAREJA p ON s.id_pareja = p.id_pareja
			WHERE p.id_cliente = ?1
			ORDER BY s.id_sobrecupo DESC
			""", nativeQuery = true)
	ArrayList<Sobrecupo> obtenerPorCliente(int idCliente);

	@Query(value = "SELECT COALESCE(SUM(monto_sobrecupo), 0) FROM SOBRECUPO WHERE id_pareja = ?1 AND estado_sobrecupo = 'APROBADO'", nativeQuery = true)
	double obtenerMontoAprobadoTotalPorPareja(int idPareja);

	@Query(value = "SELECT COALESCE(MAX(id_sobrecupo), 0) + 1 FROM SOBRECUPO", nativeQuery = true)
	int obtenerSiguienteId();

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO SOBRECUPO (id_sobrecupo, id_supervisor, id_pareja, estado_sobrecupo, monto_sobrecupo)
			VALUES (?1, ?2, ?3, ?4, ?5)
			""", nativeQuery = true)
	void crearSobrecupo(int id, int idSupervisor, int idPareja, String estadoSobrecupo, double montoSobrecupo);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM SOBRECUPO WHERE id_sobrecupo = ?1", nativeQuery = true)
	void eliminarSobrecupo(int idSobrecupo);

	@Modifying
	@Transactional
	@Query(value = "UPDATE SOBRECUPO SET estado_sobrecupo = ?1 WHERE id_sobrecupo = ?2", nativeQuery = true)
	void actualizarEstado(String estadoSobrecupo, int idSobrecupo);

}