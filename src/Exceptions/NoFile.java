package Exceptions;

public class NoFile extends Exception {


	private static final long serialVersionUID = -5507963758767192595L;
	private String msg;
	public NoFile(String msg){
		this.msg= msg;
		
	}
	public void muestraError(){
				
		System.out.println ("No existe o ha ocurrido un error con el archivo: " + this.msg);
	}
	
}
