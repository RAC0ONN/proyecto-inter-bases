package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Cliente;
import jakarta.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(value = "SELECT * FROM CLIENTE WHERE id_cliente = ?1", nativeQuery = true)
	Cliente obtenerPorId(int idCliente);

	@Query(value = "SELECT * FROM CLIENTE", nativeQuery = true)
	ArrayList<Cliente> obtenerTodos();

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO CLIENTE
			(primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, nombre_usuario, contrasenia_usuario, correo_electronico, cupo_total)
			VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)
			""", nativeQuery = true)
	void crearCliente(String pNombre, String sNombre, String pApellido, String sApellido, String usuario,
			String contrasenia, String correo, double cupoTotal);

	@Modifying
	@Transactional
	@Query(value = "DELETE " + "FROM CLIENTE WHERE id_cliente = ?1", nativeQuery = true)
	void eliminarCliente(int idCliente);
	
	@Query(value = "SELECT * FROM CLIENTE WHERE nombre_usuario = ?1", nativeQuery = true)
	Optional<Cliente> buscarPorUsuario(String nombreUsuario);
}