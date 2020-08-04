package frsf.isi.died.exceptions;

public class FormatoNumericoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7744034000838089933L;

	public FormatoNumericoException(String campo,String msg) {
		super(msg);
	}
	
	public FormatoNumericoException(String campo) {
		super("Error en el campo: "+campo+ ", el dato debe ser numerico");
	}
	
}
