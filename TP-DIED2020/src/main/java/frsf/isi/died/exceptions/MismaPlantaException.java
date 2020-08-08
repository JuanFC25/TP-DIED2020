package frsf.isi.died.exceptions;

public class MismaPlantaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8563372099256890776L;
	
	public MismaPlantaException() {
		super("La planta de origen es igual a la de destino, seleccione otra.");
	}
}
