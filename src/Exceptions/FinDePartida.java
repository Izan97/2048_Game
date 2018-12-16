package Exceptions;

public class FinDePartida extends Exception {

	
	
	private static final long serialVersionUID = 5655613443651211781L;
	private String mensaje;
	
	public FinDePartida(String estado){		
		
		this.mensaje = estado;		
	}

	
	public void muestraFinal(){
				
		System.out.println ("Partida terminada --> " + mensaje);
	}
	

}
