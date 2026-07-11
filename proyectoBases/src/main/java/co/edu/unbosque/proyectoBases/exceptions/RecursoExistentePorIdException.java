package co.edu.unbosque.proyectoBases.exceptions;

public class RecursoExistentePorIdException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RecursoExistentePorIdException (String msj) {
		super (msj);
	}
	
}
