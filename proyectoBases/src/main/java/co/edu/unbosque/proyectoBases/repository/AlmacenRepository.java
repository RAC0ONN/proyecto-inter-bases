package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Almacen;
import jakarta.transaction.Transactional;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {

	@Query(value = "SELECT * FROM ALMACEN WHERE id_almacen = ?1", nativeQuery = true)
	Almacen obtenerPorId(int idAlmacen);

	@Query(value = "SELECT * FROM ALMACEN", nativeQuery = true)
	ArrayList<Almacen> obtenerTodos();

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO ALMACEN (id_almacen, nombre, id_direccion)
			VALUES (?1, ?2, ?3)
			""", nativeQuery = true)
	void crearAlmacen(int id, String nombre, int idDireccion);

	@Modifying
	@Transactional
	@Query(value = "UPDATE ALMACEN SET nombre = ?1, id_direccion = ?2 WHERE id_almacen = ?3", nativeQuery = true)
	void actualizarAlmacen(String nombre, int idDireccion, int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ALMACEN WHERE id_almacen = ?1", nativeQuery = true)
	void eliminarAlmacen(int idAlmacen);
}