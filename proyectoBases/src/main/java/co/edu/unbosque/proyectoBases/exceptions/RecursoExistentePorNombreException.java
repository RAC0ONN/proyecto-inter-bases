package co.edu.unbosque.proyectoBases.exceptions;

public class RecursoExistentePorNombreException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RecursoExistentePorNombreException (String msj) {
		super (msj);
	}
	
}
