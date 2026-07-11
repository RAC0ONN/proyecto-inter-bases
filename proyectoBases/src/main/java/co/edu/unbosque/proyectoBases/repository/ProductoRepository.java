package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Producto;
import jakarta.transaction.Transactional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	@Query("""
			SELECT p
			FROM Producto p
			WHERE p.idProducto = ?1
			""")
	Producto existePorId(int id);

	@Query("""
			SELECT p
			FROM Producto p
			""")
	ArrayList<Producto> obtenerProductos();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO PRODUCTO (id_producto, nombre, precio) VALUES (?1, ?2, ?3)", nativeQuery = true)
	void crearProducto(int id, String nombre, double precio);

	@Modifying
	@Transactional
	@Query(value = "UPDATE PRODUCTO SET nombre = ?1, precio = ?2 WHERE id_producto = ?3", nativeQuery = true)
	void actualizarProducto(String nombre, double precio, int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM PRODUCTO WHERE id_producto = ?1", nativeQuery = true)
	void borrarProducto(int id);
}