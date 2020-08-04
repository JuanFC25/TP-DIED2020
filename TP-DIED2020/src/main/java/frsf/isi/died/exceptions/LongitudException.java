package frsf.isi.died.exceptions;

public class LongitudException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8589418172673191396L;

	public LongitudException(String campo,String msg) {
		super(msg);
	}
	
	public LongitudException(String campo) {
		super("Error en el campo: "+campo+ ", la longitud no es correcta");
	}
	
}
