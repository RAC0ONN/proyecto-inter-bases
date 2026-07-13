package co.edu.unbosque.proyectoBases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.unbosque.proyectoBases.exceptions.GlobalExceptionHandler;
import co.edu.unbosque.proyectoBases.exceptions.RecursoCompraRestringidaException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoCupoInsuficienteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoEstadoInvalidoException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoExistentePorIdException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoExistentePorNombreException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoLimiteCupoExcedidoException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoNoExistenteException;
import co.edu.unbosque.proyectoBases.exceptions.RecursoSinDatosException;

public class GlobalExceptionHandlerTest {

	private GlobalExceptionHandler handler;

	@BeforeEach
	public void setUp() {
		handler = new GlobalExceptionHandler();
	}

	@Test
	public void manejarNoExistente() {

		ResponseEntity<Map<String, Object>> respuesta = handler
				.manejarNoExistente(new RecursoNoExistenteException("No existe"));

		assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
		assertEquals(404, respuesta.getBody().get("status"));
		assertEquals("No existe", respuesta.getBody().get("message"));
		assertNotNull(respuesta.getBody().get("timestamp"));
	}

	@Test
	public void manejarSinDatos() {

		ResponseEntity<Map<String, Object>> respuesta = handler
				.manejarSinDatos(new RecursoSinDatosException("Sin datos"));

		assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
		assertEquals(404, respuesta.getBody().get("status"));
		assertEquals("Sin datos", respuesta.getBody().get("message"));
	}

	@Test
	public void manejarExistentePorId() {

		ResponseEntity<Map<String, Object>> respuesta = handler
				.manejarExistentePorId(new RecursoExistentePorIdException("ID repetido"));

		assertEquals(HttpStatus.CONFLICT, respuesta.getStatusCode());
		assertEquals(409, respuesta.getBody().get("status"));
		assertEquals("ID repetido", respuesta.getBody().get("message"));
	}

	@Test
	public void manejarExistentePorNombre() {

		ResponseEntity<Map<String, Object>> respuesta = handler
				.manejarExistentePorNombre(new RecursoExistentePorNombreException("Nombre repetido"));

		assertEquals(HttpStatus.CONFLICT, respuesta.getStatusCode());
		assertEquals(409, respuesta.getBody().get("status"));
		assertEquals("Nombre repetido", respuesta.getBody().get("message"));
	}

	@Test
	public void manejarLimiteCupo() {

		ResponseEntity<Map<String, Object>> respuesta = handler
				.manejarLimiteCupo(new RecursoLimiteCupoExcedidoException("Cupo excedido"));

		assertEquals(HttpStatus.CONFLICT, respuesta.getStatusCode());
		assertEquals(409, respuesta.getBody().get("status"));
		assertEquals("Cupo excedido", respuesta.getBody().get("message"));
	}

	@Test
	public void manejarCupoInsuficiente() {

		ResponseEntity<Map<String, Object>> respuesta = handler
				.manejarCupoInsuficiente(new RecursoCupoInsuficienteException("Cupo insuficiente"));

		assertEquals(HttpStatus.PAYMENT_REQUIRED, respuesta.getStatusCode());
		assertEquals(402, respuesta.getBody().get("status"));
		assertEquals("Cupo insuficiente", respuesta.getBody().get("message"));
	}

	@Test
	public void manejarEstadoInvalido() {

		ResponseEntity<Map<String, Object>> respuesta = handler
				.manejarEstadoInvalido(new RecursoEstadoInvalidoException("Estado inválido"));

		assertEquals(HttpStatus.CONFLICT, respuesta.getStatusCode());
		assertEquals(409, respuesta.getBody().get("status"));
		assertEquals("Estado inválido", respuesta.getBody().get("message"));
	}

	@Test
	public void manejarCompraRestringida() {

		ResponseEntity<Map<String, Object>> respuesta = handler
				.manejarCompraRestringida(new RecursoCompraRestringidaException("Compra restringida"));

		assertEquals(HttpStatus.FORBIDDEN, respuesta.getStatusCode());
		assertEquals(403, respuesta.getBody().get("status"));
		assertEquals("Compra restringida", respuesta.getBody().get("message"));
	}

	@Test
	public void manejarGeneral() {

		ResponseEntity<Map<String, Object>> respuesta = handler.manejarGeneral(new Exception("Error inesperado"));

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatusCode());
		assertEquals(500, respuesta.getBody().get("status"));
		assertEquals("Error interno del servidor: Error inesperado", respuesta.getBody().get("message"));
	}

}
