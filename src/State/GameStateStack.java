package State;


import Exceptions.PilaVacia;

public class GameStateStack {

	private static final int CAPACITY = 20;
	private GameState[] buffer = new GameState[CAPACITY + 1];

	public GameState pop() throws PilaVacia{
		int h = 0;

		if (buffer[0] != null){
			GameState sol = buffer[0];
			buffer[0] = null;


			while (buffer[h+1] != null && h+1 != CAPACITY){
				buffer[h] = buffer[h+1];
				h++;
				buffer[h] = null;
			}
			
			if (h+1 == CAPACITY)
				buffer[h]= null;
			
			return sol;
		}

		else{
			throw new PilaVacia();		
		}
			
			

	}  

	// Devuelve el �ltimo estado almacenado

	public void push(GameState state) {
		int i = 0;
		boolean fin = false;

		while ( i < CAPACITY && !fin){

			if (buffer[i] == null){

				fin = true;
			}
			i++;
		}

		while ( i > 0){

			buffer[i] = buffer[i -1];

			i--;
		}
		buffer[0] = state;	
	}


	// Almacena un nuevo estado

	public int tellMe() {
		int i = 0;

		for ( int j = 0; j < CAPACITY ; j++)
			if ( buffer[j] != null )
				i++; 

		return i;
	}// Devuelve true si la secuencia est� vac�a

}
