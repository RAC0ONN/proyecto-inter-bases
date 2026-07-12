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

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO SOBRECUPO (id_sobrecupo, porcentaje_sobrecupo, valor_maximo, id_pareja)
			VALUES (?1, ?2, ?3, ?4)
			""", nativeQuery = true)
	void crearSobrecupo(int id, double porcentajeSobrecupo, double valorMaximo, int idPareja);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM SOBRECUPO WHERE id_sobrecupo = ?1", nativeQuery = true)
	void eliminarSobrecupo(int idSobrecupo);

	@Modifying
	@Transactional
	@Query(value = "UPDATE SOBRECUPO SET estado_sobrecupo = ?1 WHERE id_sobrecupo = ?2", nativeQuery = true)
	void actualizarEstado(String estadoSobrecupo, int idSobrecupo);
}