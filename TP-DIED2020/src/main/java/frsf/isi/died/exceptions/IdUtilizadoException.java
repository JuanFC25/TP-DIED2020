package frsf.isi.died.exceptions;

public class IdUtilizadoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5171427147103887022L;
	
	public IdUtilizadoException() {
		super("El id ingresado ya esta en uso, ingrese otro.");
	}
}
