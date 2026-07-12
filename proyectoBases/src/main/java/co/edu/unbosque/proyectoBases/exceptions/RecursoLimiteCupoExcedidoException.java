package co.edu.unbosque.proyectoBases.exceptions;

public class RecursoLimiteCupoExcedidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RecursoLimiteCupoExcedidoException(String msj) {
		super(msj);
	}

}
