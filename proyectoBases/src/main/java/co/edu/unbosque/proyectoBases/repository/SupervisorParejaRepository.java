package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.SupervisorPareja;
import jakarta.transaction.Transactional;

@Repository
public interface SupervisorParejaRepository extends JpaRepository<SupervisorPareja, Integer> {

	@Query(value = "SELECT * FROM Supervisor_Pareja WHERE id_pareja = ?1", nativeQuery = true)
	SupervisorPareja obtenerPorPareja(int idPareja);

	@Query(value = "SELECT * FROM Supervisor_Pareja WHERE id_supervisor = ?1", nativeQuery = true)
	ArrayList<SupervisorPareja> obtenerPorSupervisor(int idSupervisor);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Supervisor_Pareja (id_supervisor, id_pareja) VALUES (?1, ?2)", nativeQuery = true)
	void crearSupervisorPareja(int idSupervisor, int idPareja);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Supervisor_Pareja WHERE id_pareja = ?1", nativeQuery = true)
	void eliminarSupervisorPareja(int idPareja);
}