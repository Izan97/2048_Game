package Rules;

import java.util.Random;

import Body.Board;
import Body.Cell;
import Body.Position;

public interface GameRules {



	void addNewCellAt(Board board, Position pos, Random rand); //Incorpora una nueva celda con valor 
	//aleatorio en la posici�n pos del tablero board

	int merge(Cell self, Cell other); // fusiona dos celdas y devuelve el n�mero de puntos obtenidos

	boolean noMerge(Cell self, Cell other); // Comprueba si se puede fusionar o no
	
	int getWinValue(Board board); // Devuelve el mejor valor de tablero seg�n las reglas del juego, comprobando 
	// si es un valor ganador y si se ha ganado el juego,


	boolean win(Board board);// Devuelve si el juego se ha ganado o no
	
	default boolean lose(Board board ) {  // Devuelve si el juego se ha perdido o no
		boolean lose;		
		if(board.seguirOnoSeguir(this) == true){

			lose = false;
		}
		else
			lose = true;
		
		return lose;

	}

	default Board createBoard(int size){	// Crea crea y devuelve un tablero size � size

		return new Board(size);

	} 

	default boolean addNewCell(Board board, Random rand){  // Elige una posici�n libre de board e invoca el m�todo 
		boolean terminado = false;							   		// addNewCellAt() para a�adir una celda en esa posici�n
		Position pos;
		int i, j;
		Cell celdaAux;
		
		if (!board.isfull())
		while ( terminado == false){
			//usar myRandom

			i = rand.nextInt((board.getSize()-1)+1);
			j = rand.nextInt((board.getSize()-1)+1);

			pos = new Position (i, j);
			celdaAux = board.getCell(pos);


			if (celdaAux.getValor() == 0){
				terminado = true;
				addNewCellAt(board, new Position (i, j), rand);
			}

		}

		return terminado;
	} 



	default void initBoard(Board board, int numCells, Random rand){ // Inicializa board eligiendo numCells
																	// posiciones libres, e invoca el m�todo 																	// addNewCellAt() para a�adir nuevas celdas en esas posiciones.
		for(int i = numCells; i > 0; i--)
			addNewCell(board, rand);
		
	}   
																		





}
