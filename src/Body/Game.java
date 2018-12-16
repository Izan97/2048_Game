package Body;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import Exceptions.ErroresLoadCommand;
import Exceptions.FinDePartida;
import Exceptions.NoMove;
import Exceptions.PilaVacia;
import Rules.GameRules;
import State.GameState;
import State.GameStateStack;


public class Game {


	private Board board; // Tablero
	private int size; // Dimension del tablero
	private int initCells;
	private long seed;// Numero de baldosas no nulas iniciales
	private Random myRandom;// Comportamiento aleatorio del juego


	private int elMaximo;
	private int puntos;
	private boolean finalizada;


	private GameStateStack undoStack = new GameStateStack();
	private GameStateStack redoStack = new GameStateStack();
	private GameRules currentRules;
	private GameType miTipoJ;



	public Game(GameType tipo,int dim, int cells, long seed) {
		this.miTipoJ = tipo;
		this.currentRules = tipo.getRules();
		this.size = dim;
		this.initCells = cells; // pone cells casillas con valor 4 o 2
		this.seed = seed;
		this.finalizada = false;
		this.elMaximo = 0;
		this.puntos = 0;

	}

	public void iniBoard(){
		this.board = currentRules.createBoard(size);
		myRandom = new Random(seed);
		currentRules.initBoard(board, initCells, myRandom);		
		elMaximo = currentRules.getWinValue(board);
	}


	public void reset(){

		iniBoard();
		this.puntos = 0;

	}

	private void anadirRandom(){

		currentRules.addNewCell(board, myRandom);

	}




	public void move(Direction dir) throws PilaVacia, FinDePartida, NoMove{
		MoveResults aux;
		int vaciar;
		GameState miEstado;
		miEstado = getState();
		//guardarStateActual(undoStack);

		aux = board.executeMove(dir,currentRules); // tienes que igualarlo a un objeto de tipo moveresults
		puntos = aux.getPoints() + puntos;
		elMaximo = currentRules.getWinValue(board);
		if (aux.getMoved()){
			undoStack.push(miEstado);
			//if (!board.isfull() ){
			anadirRandom();
			//}
		}
		else
			throw new NoMove();
			

		if (currentRules.lose(board) == false){
			finalizada = false;
			vaciar = redoStack.tellMe();
			for ( int i = vaciar; i > 0 ; i--)
				redoStack.pop();
		}
	}




	public void guardarStateActual(GameStateStack pila){

		GameState miEstado;

		miEstado = getState();

		pila.push(miEstado);

	}





	public void undo() throws PilaVacia {

		GameState miEstado;
		try{

			guardarStateActual(redoStack); // push del estado actual
			miEstado = undoStack.pop();
			setState(miEstado);

		}

		catch (PilaVacia e){		
			setState(redoStack.pop());
			e.completoMsg("no se ha ejecutado el undo ");
			throw e;
		}

	}


	public void redo() throws PilaVacia{
		GameState miEstado;
		try{

			miEstado = getState();
			undoStack.push(miEstado);
			miEstado = redoStack.pop();

			setState(miEstado);	
		}
		catch (PilaVacia e){		
			undoStack.pop();
			e.completoMsg("no se ha ejecutado el redo ");
			throw e;
		}


	}


	public GameState getState(){

		int [ ][ ] board;
		board = this.board.getState();

		GameState miEstado = new GameState(board, this.puntos, this.elMaximo, this.finalizada);

		return miEstado;

	}

	public void setState(GameState aState){

		this.elMaximo = aState.getMaxPoints();
		this.puntos = aState.getPoints();
		this.finalizada = aState.getfin();
		this.board.setState(aState.getTablero());

	}

	public boolean comprobarEstadoGame() throws FinDePartida{
	
		
		if ( currentRules.win(board))
			throw new FinDePartida("Has ganado");
		
		if (currentRules.lose(board))
			throw new FinDePartida("Has perdido");
		
		

		return finalizada;
	}



	public void changeEstado() throws FinDePartida{

		finalizada = true;
		throw new FinDePartida("Has pulsado Exit");	

	}

	public void changeRules(GameType tipo){

		this.miTipoJ = tipo;
		this.currentRules = tipo.getRules();

	}


	public void playGame(int tama,int inicell,long seed){


		this.initCells = inicell;
		this.size = tama;
		this.seed = seed;
		reset();

	}

	
	public void store (BufferedWriter fileW) throws IOException{
		
		/*
		 	This file stores a saved 2048 game
			4 0 0 0
			0 0 2 0
			0 0 0 0
			2 0 0 0
			2 4 original
		 
		*/
		//try {
			
			fileW.write("This file stores a saved 2048 game");
			fileW.newLine();
			fileW.newLine();
			board.store(fileW);
			fileW.newLine();
			fileW.write(""+this.initCells + " ");
			fileW.write(""+ this.puntos + " ");
			fileW.write(this.miTipoJ.externalise());
			fileW.flush();
						
			
		
	}
	
	
public GameType load (BufferedReader fileR) throws FinDePartida, ErroresLoadCommand, PilaVacia, IOException{
		String fila, ultimaLinea[];
		int vaciar;
		GameType volatil = null;
		
		int icells, punt ;
		/*
		 	This file stores a saved 2048 game
		 	
			4 0 0 0
			0 0 2 0
			0 0 0 0
			2 0 0 0
			
			2 4 original
		 */
		GameState copia = getState();
		int sizeCopia = this.size;
		try {
			
			fila = fileR.readLine(); //("This file stores a saved 2048 game");
			if (fila.equalsIgnoreCase("This file stores a saved 2048 game")){
				fila = fileR.readLine();
				if (!fila.equalsIgnoreCase(""))// enter
					throw new ErroresLoadCommand("Formato de '/n' erroneo");
				board.load(fileR); // devuelve un booleano (excepcion)
				this.size = board.getSize(); // tamano del tablero nuevo
				fila = fileR.readLine();
				ultimaLinea = fila.split(" +");
				if (ultimaLinea.length != 3)
					throw new ErroresLoadCommand("Formato de espacios erroneos");
				icells = Integer.parseInt(ultimaLinea[0]);			
				punt = Integer.parseInt(ultimaLinea[1]);
				if (icells > this.size * this.size || icells < 0)
					throw new ErroresLoadCommand("Initcells supera el valor maximo");
				GameType nType =  GameType.parse(ultimaLinea[2]);
				if ( nType  == null)
					throw new ErroresLoadCommand("El gameType es incorrecto");
				changeRules(nType);
				volatil = nType;
				this.elMaximo = currentRules.getWinValue(board);
				this.initCells = icells;
				this.puntos = punt;
				
				
				//////// Vaciar las pilas
				vaciar = redoStack.tellMe();
				for ( int i = vaciar; i > 0 ; i--)
					redoStack.pop();
				vaciar = undoStack.tellMe();
				for ( int i = vaciar; i > 0 ; i--)
					undoStack.pop();
				fileR.close();
				
			}
			else
				throw new ErroresLoadCommand("La primera linea es incorrecta");			
		}
		catch (ErroresLoadCommand e) {
			this.size = sizeCopia;
			setState(copia);
			throw e;
						
		}
		catch (ArrayIndexOutOfBoundsException e){			
			this.size = sizeCopia;
			setState(copia);
			throw e;
			
		}
		catch(IOException e){
			this.size = sizeCopia;
			setState(copia);
			throw e;
			
		}
		catch (NumberFormatException excepcion) {
			this.size = sizeCopia;
			setState(copia);
			throw excepcion;
			
		}	
		
		return volatil;
		
	}

	public String toString(){
		String juego= "";

			juego = board.toString() + "\n Best Value: " + elMaximo + "      Score: "+ 
					puntos;
	
		return juego;

	}


}
