package co.edu.unbosque.proyectoBases.exceptions;

public class RecursoNoExistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RecursoNoExistenteException (String msj) {
		super (msj);
	}
	
}
