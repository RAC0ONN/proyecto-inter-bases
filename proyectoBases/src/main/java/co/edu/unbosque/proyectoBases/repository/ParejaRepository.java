package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Pareja;
import jakarta.transaction.Transactional;

@Repository
public interface ParejaRepository extends JpaRepository<Pareja, Integer> {

	@Query(value = "SELECT * FROM PAREJA WHERE id_pareja = ?1", nativeQuery = true)
	Pareja obtenerPorId(int idPareja);

	@Query(value = "SELECT * FROM PAREJA", nativeQuery = true)
	ArrayList<Pareja> obtenerTodas();

	@Query(value = "SELECT * FROM PAREJA WHERE id_cliente = ?1", nativeQuery = true)
	ArrayList<Pareja> obtenerPorCliente(int idCliente);

		@Query(value = "SELECT COALESCE(SUM(cupo_asignado), 0) FROM PAREJA WHERE id_cliente = ?1", nativeQuery = true)
	double obtenerCupoAsignadoTotal(int idCliente);

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO PAREJA 
			(primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, nombre_usuario, contrasenia_usuario, cupo_asignado, correo_electronico, id_cliente)
			VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)
			""", nativeQuery = true)
	void crearPareja(String pNombre, String sNombre, String pApellido, String sApellido, String usuario,
			String contrasenia, double cupoAsignado, String correo, int idCliente);

	@Modifying
	@Transactional
	@Query(value = """
			UPDATE PAREJA SET 
			primer_nombre = ?1, segundo_nombre = ?2, primer_apellido = ?3, segundo_apellido = ?4, 
			nombre_usuario = ?5, contrasenia_usuario = ?6, cupo_asignado = ?7, correo_electronico = ?8, id_cliente = ?9 
			WHERE id_pareja = ?10
			""", nativeQuery = true)
	void actualizarPareja(String pNombre, String sNombre, String pApellido, String sApellido, String usuario,
			String contrasenia, double cupoAsignado, String correo, int idCliente, int idPareja);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM PAREJA WHERE id_pareja = ?1", nativeQuery = true)
	void eliminarPareja(int idPareja);
}