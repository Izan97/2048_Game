package Body;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import Exceptions.ErroresLoadCommand;
import Rules.GameRules;


public class Board {

	private final static String saltoLinea = System.getProperty("line.separator");
	private Cell [ ][ ] _board;
	private int _boardSize;

	public Board(int dim) {		
		this._boardSize = dim;
		this._board = new Cell [dim][dim];
		for ( int i = 0; i < dim; i++){
			for (int j = 0; j < dim; j++)
				_board[i][j] = new Cell(0);
		}

	}


	public int getSize(){
		return this._boardSize;
	}


	public void setCell(Position pos, int value){

		_board[pos.getRow()][pos.getCol()].setValor(value);

	}



	public Cell  getCell (Position pos){

		return _board[pos.getRow()][pos.getCol()];

	}


	public int[][] getState(){
		int [ ][ ] boardInt =  new int[_boardSize][_boardSize];

		for ( int i = 0; i < _boardSize; i++){
			for (int j = 0; j < _boardSize; j++)
				boardInt[i][j] = _board[i][j].getValor() ;
		}


		return boardInt;
	}

	public void setState(int[][] aState){
		this._boardSize = aState.length;
		//System.out.println(this._boardSize)
		this._board = new Cell [this._boardSize][this._boardSize];
		for ( int i = 0; i < this._boardSize; i++){
			for (int j = 0; j < this._boardSize; j++)
				_board[i][j] = new Cell(0);
		}
		////////
		
		for ( int i = 0; i < _boardSize; i++){
			for (int j = 0; j < _boardSize; j++)
				_board[i][j].setValor(aState[i][j]);
		}	
	}


	public MoveResults executeMove(Direction dir, GameRules rules){ 
		int suma = 0;
		boolean movido = false;


		switch (dir) {

		case UP: 

			movido = moverCeros(dir);

			for(int i = 0 ; i < _boardSize - 1 ; i++){
				for ( int j = 0 ; j < _boardSize ; j++){
					suma = movimiento (dir, i, j, rules) + suma;										
				}
			}

			moverCeros(dir);		



			break;


		case DOWN: 


			movido = moverCeros(dir);

			for(int i = _boardSize - 1 ; i > 0; i--){
				for ( int j = _boardSize - 1 ; j >= 0; j--){
					suma = movimiento (dir, i, j, rules) + suma;
				}
			}

			moverCeros(dir);




			break;


		case RIGHT:

			movido = moverCeros(dir);

			for(int i = _boardSize - 1 ; i >= 0 ; i += -1){
				for ( int j = _boardSize - 1 ; j > 0; j += -1){
					suma = movimiento (dir, i, j, rules) + suma;
				}
			}


			moverCeros(dir);

			break;


		case LEFT: 

			movido = moverCeros(dir);

			for(int i = 0 ; i < _boardSize  ; i++){
				for ( int j = 0 ; j < _boardSize - 1; j++){

					suma = movimiento (dir, i, j, rules) + suma;
				}
			}


			moverCeros(dir);



			break;


		}

		if (suma != 0)
			movido = true;



		MoveResults misPuntos = new MoveResults(suma,movido);

		return misPuntos;
	}




	//Desplaza los ceros en funcion dir para que se haga la fusion correctamente

	private  boolean moverCeros(Direction dir){

		boolean cambiado, heMovido = false;
		switch (dir) {

		case UP: 

			for(int i = 0; i < _boardSize -1; i++){
				for ( int j = 0; j < _boardSize; j++){

					// para cada fila (i)
					if (  _board[i][j].getValor() == 0){
						int c = i + 1;
						cambiado = false;
						while ( c <_boardSize  && cambiado !=true ){	

							if (  _board[c][j].getValor() != 0){

								_board[i][j].setValor(_board[c][j].getValor());
								_board[c][j].setValor(0);
								cambiado = true;
								heMovido = true;


							} // fin del if

							c++;

						}

					}
				}
			}


			break;

		case DOWN: 

			for(int i = _boardSize - 1 ; i >= 0; i--){
				for ( int j = _boardSize - 1 ; j >= 0; j--){

					// para cada fila (i)
					if (  _board[i][j].getValor() == 0){
						int c = i-1;
						cambiado = false;
						while ( c >= 0 && cambiado !=true ){	

							if (  _board[c][j].getValor() != 0){

								_board[i][j].setValor(_board[c][j].getValor());
								_board[c][j].setValor(0);
								cambiado = true;
								heMovido = true;


							} // fin del if

							c--;

						}

					}
				}
			}

			break;



		case RIGHT: 

			for(int i =  0; i < _boardSize; i++){
				for ( int j = _boardSize - 1 ; j >= 0; j--){

					// para cada fila (i)
					if (  _board[i][j].getValor() == 0){
						int c = j-1;
						cambiado = false;
						while ( c >= 0 && cambiado !=true ){	

							if (  _board[i][c].getValor() != 0){

								_board[i][j].setValor(_board[i][c].getValor());
								_board[i][c].setValor(0);
								cambiado = true;
								heMovido = true;


							} // fin del if

							c--;

						}

					}
				}
			}



			break;


		case LEFT: 



			for(int i =  0; i < _boardSize; i++){
				for ( int j = 0; j < _boardSize; j++){

					// para cada fila (i)
					if (  _board[i][j].getValor() == 0){
						int c = j + 1;
						cambiado = false;
						while ( c < _boardSize && cambiado !=true ){	

							if (  _board[i][c].getValor() != 0){

								_board[i][j].setValor(_board[i][c].getValor());
								_board[i][c].setValor(0);
								cambiado = true;
								heMovido = true;


							} // fin del if

							c++;

						}

					}
				}
			}




			break;

		}


		return heMovido;

	}




	//Metodo auxiliar que dado un Dir, i, j ; fusiona las celdas en esa direction y nos devuelve los puntos de la misma

	private int movimiento(Direction dir, int a, int b, GameRules rules){
		int result = 0;
		// a y b son los indices

		Position pos = new Position(a,b);
		Position vecina = pos.getNeighbour(dir);
		/*
		if (_board[pos.getRow()][pos.getCol()].doMerge(_board[vecina.getRow()][vecina.getCol()])){
			result = _board[pos.getRow()][pos.getCol()].getValor();
		}
		 */
		result = _board[pos.getRow()][pos.getCol()].doMerge(_board[vecina.getRow()][vecina.getCol()], rules);


		return result;

	}




	//Metodo auxiliar que nos dice si el tablero tiene todas las celdas llenas

	public boolean isfull(){

		//boolean sol = true;

		for(int i = 0 ; i < _boardSize ; i++){
			for ( int j = 0 ; j < _boardSize ; j++){
				if (_board[i][j].isEmpty())
					return false;  // sol = false
			}
		}

		return true; // return sol
	}




	// Metodo auxilar que nos devuelve cierto si el tablero esta lleno pero existe una fusion posible

	public boolean seguirOnoSeguir(GameRules rules){
		boolean youCan = true;

		if ( isfull() == true){
			youCan = false;
			/////////////////////////////////
			int i = 0;
			while ( i < _boardSize  && youCan == false){
				int j = 0;
				while( j < _boardSize - 1 && youCan == false) {
					youCan =  _board[i][j].doMergeNoMove(_board[i][j + 1], rules);
					j++;
				}
				i++;
			}
			/////////////////////////////////
			int c = 0;
			while ( c < _boardSize - 1 && youCan == false){
				int d = 0;
				while( d < _boardSize  && youCan == false) {
					youCan =  _board[c][d].doMergeNoMove(_board[c + 1][d], rules);
					d++;
				}
				c++;
			}			
		}


		return youCan;
	}

	public int getMaxValue(){

		int auxMax = 0;

		for(int i = 0 ; i < _boardSize  ; i++){
			for ( int j = 0 ; j < _boardSize; j++){
				if (_board[i][j].getValor() > auxMax)
					auxMax = _board[i][j].getValor();
			}
		}
		return auxMax;
	}

	public int getMinValue(){

		int auxMin = 10000;

		for(int i = 0 ; i < _boardSize  ; i++){
			for ( int j = 0 ; j < _boardSize; j++){
				if ((_board[i][j].getValor() < auxMin) && (_board[i][j].getValor()!= 0))
					auxMin = _board[i][j].getValor();
			}
		}
		return auxMin;
	}





	public void store(BufferedWriter fileW) throws IOException {

		for (int i = 0; i < this._boardSize; i++){
			for (int j = 0; j < this._boardSize; j++){
				fileW.write(_board[i][j].getValor() + " ");
			}
			fileW.newLine();			
		}

	}
	public boolean load(BufferedReader fileR) throws IOException, ErroresLoadCommand {
		
		
		String fila, numeros[];
		int vueltas;
		boolean exito = true;

		//try{	
			fila = fileR.readLine(); // primera fila de numeros
			numeros = fila.split(" +"); // numeros spliteados
			vueltas = numeros.length;
		//////////////////////////////////
			//nuevas celdas del tablero
			this._boardSize = numeros.length;
			this._board = new Cell [vueltas][vueltas];
			for ( int i = 0; i < vueltas; i++){
				for (int j = 0; j < vueltas; j++)
					_board[i][j] = new Cell(0);
			}
	/////////////////////////////////////		
			
			//this._boardSize = numeros.length;
	
				for (int i = 0 ; i < vueltas; i++){
					if (numeros.length != vueltas)
						throw new ErroresLoadCommand("Algo no ha ido bien mientras rellenaba el tablero");
					for (int j = 0 ; j < vueltas; j++){
						_board[i][j].setValor(Integer.parseInt(numeros[j]));
						if (_board[i][j].getValor() < 0){							
							throw new NumberFormatException();
							
						}
					}
					if(i < vueltas){
						fila = fileR.readLine();
						numeros = fila.split(" +");  // numeros spliteados
					}
					
				}
				if (!fila.equalsIgnoreCase(""))
					throw new ErroresLoadCommand("Error en las lineas entre el board y la ultima");

	//	} catch(NumberFormatException  e){
		//	System.err.println("Error con el formato de los numeros");
		//	exito = false;
	//	}	

		return exito;
	}

	public String toString(){

		String tablero = " ";
		for ( int i = 0; i < _boardSize; i++){
			if( i != 0)
				tablero = tablero  + "|" +  saltoLinea;
			else
				tablero = tablero + saltoLinea;
			for (int j = 0; j < _boardSize; j++)
				tablero = tablero + "|   "+ _board[i][j].getValor()+"   ";

		}
		tablero = tablero + "|";


		return tablero;

	}	


}
