package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.proyectoBases.entity.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

	@Query("""
			SELECT c
			FROM Ciudad c
			WHERE c.idCiudad = ?1
			""")
	Ciudad existePorId(int id);

	@Query("""
			SELECT c
			FROM Ciudad c
			WHERE LOWER(c.nombre) = LOWER(?1)
			""")
	Ciudad existePorNombre(String nombre);

	@Query("""
			SELECT c
			FROM Ciudad c
			""")
	ArrayList<Ciudad> obtenerCiudades();
}