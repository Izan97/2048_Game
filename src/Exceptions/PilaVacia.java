package Exceptions;

public class PilaVacia extends Exception{

	private static final long serialVersionUID = 7917894178997326638L;
	private String mensaje;
	
	public PilaVacia (){
		super("La pila esta vacia ");
	}
	
	public void completoMsg(String msj){
		this.mensaje= msj;
	}
	public void muestraError(){

		System.out.println (this.getMessage() + this.mensaje);
	}


}
