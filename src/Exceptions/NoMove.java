package Exceptions;

public class NoMove extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9169138854496243379L;

	public NoMove (){
		super("No ha habido movimiento");
	}
}
