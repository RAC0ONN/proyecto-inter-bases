package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Direccion;
import jakarta.transaction.Transactional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

	@Query(value = "SELECT * FROM DIRECCION WHERE id_direccion = ?1", nativeQuery = true)
	Direccion obtenerPorId(int idDireccion);

	@Query(value = "SELECT * FROM DIRECCION", nativeQuery = true)
	ArrayList<Direccion> obtenerTodas();

	@Modifying
	@Transactional
	@Query(value = """
			INSERT INTO DIRECCION
			(id_direccion, via_principal, numero_via, letra_via, sufijo, numero_generador, placa, id_barrio, id_ciudad)
			VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)
			""", nativeQuery = true)
	void crearDireccion(int id, String viaPrincipal, int numeroVia, String letraVia, String sufijo, int numeroGenerador,
			Integer placa, int idBarrio, int idCiudad);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM DIRECCION WHERE id_direccion = ?1", nativeQuery = true)
	void eliminarDireccion(int idDireccion);
}