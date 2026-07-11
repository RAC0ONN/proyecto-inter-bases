package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.CompraProducto;
import jakarta.transaction.Transactional;

@Repository
public interface CompraProductoRepository extends JpaRepository<CompraProducto, Integer> {

	@Query(value = "SELECT * FROM COMPRA_PRODUCTO WHERE id_compra_producto = ?1", nativeQuery = true)
	CompraProducto obtenerPorId(int idCompraProducto);

	@Query(value = "SELECT * FROM COMPRA_PRODUCTO WHERE id_compra = ?1", nativeQuery = true)
	ArrayList<CompraProducto> obtenerPorCompra(int idCompra);

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO COMPRA_PRODUCTO (id_compra_producto, id_compra, id_producto)
			VALUES (?1, ?2, ?3)
			""", nativeQuery = true)
	void crearCompraProducto(int id, int idCompra, int idProducto);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM COMPRA_PRODUCTO WHERE id_compra_producto = ?1", nativeQuery = true)
	void eliminarCompraProducto(int idCompraProducto);
}