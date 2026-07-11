package co.edu.unbosque.proyectoBases.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.unbosque.proyectoBases.entity.Barrio;

public interface BarrioRepository extends JpaRepository<Barrio, Integer> {

	@Query("""
			SELECT b
			FROM Barrio b
			WHERE b.idBarrio = ?1
			""")
	Barrio existePorId(int id);

	@Query("""
			SELECT b
			FROM Barrio b
			WHERE LOWER(b.nombre) = lower(?1)
			""")
	Barrio existePorNombre(String Nombre);

	@Query("""
			SELECT b
			FROM Barrio b
			""")
	ArrayList<Barrio> obtenerBarrios();
}
