package co.edu.unbosque.proyectoBases.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RecursoNoExistenteException.class)
	public ResponseEntity<Map<String, Object>> manejarNoExistente(RecursoNoExistenteException ex) {
		return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(RecursoSinDatosException.class)
	public ResponseEntity<Map<String, Object>> manejarSinDatos(RecursoSinDatosException ex) {
		return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(RecursoExistentePorIdException.class)
	public ResponseEntity<Map<String, Object>> manejarExistentePorId(RecursoExistentePorIdException ex) {
		return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
	}

	@ExceptionHandler(RecursoExistentePorNombreException.class)
	public ResponseEntity<Map<String, Object>> manejarExistentePorNombre(RecursoExistentePorNombreException ex) {
		return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
	}

	@ExceptionHandler(RecursoLimiteCupoExcedidoException.class)
	public ResponseEntity<Map<String, Object>> manejarLimiteCupo(RecursoLimiteCupoExcedidoException ex) {
		return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
	}

	@ExceptionHandler(RecursoCupoInsuficienteException.class)
	public ResponseEntity<Map<String, Object>> manejarCupoInsuficiente(RecursoCupoInsuficienteException ex) {
		return construirRespuesta(HttpStatus.PAYMENT_REQUIRED, ex.getMessage());
	}

	@ExceptionHandler(RecursoEstadoInvalidoException.class)
	public ResponseEntity<Map<String, Object>> manejarEstadoInvalido(RecursoEstadoInvalidoException ex) {
		return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
	}

	@ExceptionHandler(RecursoCompraRestringidaException.class)
	public ResponseEntity<Map<String, Object>> manejarCompraRestringida(RecursoCompraRestringidaException ex) {
		return construirRespuesta(HttpStatus.FORBIDDEN, ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {
		Map<String, String> errores = new LinkedHashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("message", "Datos inválidos en la solicitud");
		body.put("errores", errores);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> manejarGeneral(Exception ex) {
		return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor: " + ex.getMessage());
	}

	private ResponseEntity<Map<String, Object>> construirRespuesta(HttpStatus status, String mensaje) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("message", mensaje);
		return ResponseEntity.status(status).body(body);
	}

}
