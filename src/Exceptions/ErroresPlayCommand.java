package Exceptions;

public class ErroresPlayCommand extends Exception {
	
	
	private static final long serialVersionUID = 4296379731873342152L;
	private String mensaje1;
	private String mensaje2;
	
	public ErroresPlayCommand (String m1, String m2){
		
		
		this.mensaje1 = m1;
		this.mensaje2 = m2;
		
	}
	
	
	public void muestraError(){
		
		System.err.println ("Ha habido un error al introducir el " + this.mensaje1 + " , el parametro " + 
		this.mensaje2 + " no es valido");
	}
	

}





	
	
	

