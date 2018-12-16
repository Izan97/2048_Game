package Body;

public class MoveResults {

	// solo guarda las puntuaciones, cambiarlas etc
	
	
	private boolean moved; // si es true se puede mover
							// si es false no se puede mover
	
	
	private int points; //para almacenar el numero de puntos obtenidos en el movimiento
						
	//private int maxToken; //para llevar el valor mas alto tras el movimiento
	
	
	
	public MoveResults(int points, boolean mover){
	 this.points = points;	
	//this.maxToken = maximo;
	this.moved = mover;
	}
	
	
	
	
	
	
	public int getPoints(){
			
		return this.points;
	}
	
	/*
	public int getMaximo(){
		
		return this.maxToken;
	}
	*/
	public boolean getMoved(){
		
			return this.moved;
		}

	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
