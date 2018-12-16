package Exceptions;

public class ErroresLoadCommand extends Exception{

	private static final long serialVersionUID = -7842524326930888676L;
	private String mensaje;

	public ErroresLoadCommand (String estado){		

		this.mensaje = estado;		
	}

	public void muestraError(){

		System.out.println ("Error cargando el fichero --> " + mensaje);
	}


}







	

