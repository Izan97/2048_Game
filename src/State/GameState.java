package State;





public class GameState {

	private int [ ][ ] _board;
	private int puntos;
	private int maxPuntos;
	private boolean fin;
	
	// segun el pdf hay que hacer metodos get para todos
	
	public GameState (int[][] board, int puntos, int maxPuntos, boolean fin){
		this._board = board;
		this.puntos = puntos;
		this.maxPuntos = maxPuntos;
		this.fin = fin;		
	}
	
	public int [][] getTablero(){
		
		return this._board;
	}
	
	public int getPoints(){
		
		return this.puntos;
	}
	
	public int getMaxPoints(){
		
		return this.maxPuntos;
	}
	
	public boolean getfin(){
		
		return this.fin;
	}
	
	
}
