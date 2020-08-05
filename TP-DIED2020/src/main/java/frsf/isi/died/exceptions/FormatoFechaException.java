package frsf.isi.died.exceptions;

public class FormatoFechaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4623548078650857478L;

	public FormatoFechaException() {
		super("La fecha no esta ingresada correctamente. El formato es: dd-mm-aaaa");
	}
	
}
