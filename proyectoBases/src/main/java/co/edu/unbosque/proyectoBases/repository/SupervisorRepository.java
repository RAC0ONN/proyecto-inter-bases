package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Supervisor;
import jakarta.transaction.Transactional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

	@Query(value = "SELECT * FROM SUPERVISOR WHERE id_supervisor = ?1", nativeQuery = true)
	Supervisor obtenerPorId(int idSupervisor);

	@Query(value = "SELECT * FROM SUPERVISOR", nativeQuery = true)
	ArrayList<Supervisor> obtenerTodos();

	@Modifying
	@Transactional
	@Query(value = """
	        INSERT INTO SUPERVISOR 
	        (primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, nombre_usuario, contrasenia_usuario, correo_electronico, id_almacen) 
	        VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)
	        """, nativeQuery = true)
	void crearSupervisor(String pNombre, String sNombre, String pApellido, String sApellido, 
	        String usuario, String contrasenia, String correo, int idAlmacen);
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM SUPERVISOR WHERE id_supervisor = ?1", nativeQuery = true)
	void eliminarSupervisor(int idSupervisor);
	
	@Query(value = "SELECT * FROM SUPERVISOR WHERE nombre_usuario = ?1", nativeQuery = true)
	Optional<Supervisor> buscarPorUsuario(String nombreUsuario);
}