package frsf.isi.died.exceptions;

public class CampoVacioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2563244160375469851L;

	public CampoVacioException(String campo) {
		super("Error en el campo " + campo + ": El campo esta vacio.");
	}
}
