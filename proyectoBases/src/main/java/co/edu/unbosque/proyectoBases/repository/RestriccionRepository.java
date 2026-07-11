package co.edu.unbosque.proyectoBases.repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Restriccion;
import jakarta.transaction.Transactional;

@Repository
public interface RestriccionRepository extends JpaRepository<Restriccion, Integer> {

	@Query(value = "SELECT * FROM restriccion", nativeQuery = true)
	List<Restriccion> obtenerTodas();
	
	@Query(value = "SELECT * FROM RESTRICCION WHERE id_restriccion = ?1", nativeQuery = true)
	Restriccion obtenerPorId(int idRestriccion);

	@Query(value = "SELECT * FROM RESTRICCION WHERE id_pareja = ?1", nativeQuery = true)
	ArrayList<Restriccion> obtenerPorPareja(int idPareja);

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO RESTRICCION (id_restriccion, dia_semana, hora_inicio, hora_fin, id_pareja)
			VALUES (?1, ?2, ?3, ?4, ?5)
			""", nativeQuery = true)
	void crearRestriccion(int id, String diaSemana, LocalTime horaInicio, LocalTime horaFin, int idPareja);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM RESTRICCION WHERE id_restriccion = ?1", nativeQuery = true)
	void eliminarRestriccion(int idRestriccion);
}