package Rules;

import java.util.Random;

import Body.Board;
import Body.Cell;
import Body.Position;

public class RulesInverse  implements GameRules{

	private final int STOP_VALUE = 1;

	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		double alea;
		int numero;

		alea = rand.nextDouble();

		if (alea < 0.9)    // el 90% de las veces 
			numero = 2048;
		else      
			numero = 1024;

		board.setCell(pos, numero);

	}

	@Override
	public int merge(Cell self, Cell other) {
		int suma = 0;

		if ( other.getValor() == self.getValor() && self.getValor() != 0){ // Si vale lo vismo esta celda que la vecina...

			self.setValor(self.getValor()/2); // Nuestra celda con el nuevo valor
			other.setValor(0);// la celda vecina a 0

			suma = self.getValor();
			switch (self.getValor()) {
	        	case 2048: suma = 2;	   
	        	break;
	        	
	        	case 1024: suma = 4;	   
	        	break;
	        	
	        	case 512: suma = 8;	   
	        	break;
	        	
	        	
	        	case 256: suma = 16;	   
	        	break;
	        	
	        	case 128: suma = 32;	   
	        	break;
	        	
	        	case 64: suma = 64;	   
	        	break;
	        	
	        	case 32: suma = 128;	   
	        	break;
	        	
	        	
	        	case 16: suma = 256;	   
	        	break;
	        	
	        	case 8: suma = 512;	   
	        	break;
	        	
	        	case 4: suma = 1024;	   
	        	break;
	        	
	        	case 2: suma = 2048;	   
	        	break;
	        	
	        	default: suma = 0;
	        	break;
	 }

		}
		return suma;
	}

	@Override
	public int getWinValue(Board board) {

		return board.getMinValue();
	}

	@Override
	public boolean win(Board board) {

		if ( board.getMinValue() == this.STOP_VALUE)
			return true;
		else
			return false;
	}

	@Override
	public boolean noMerge(Cell self, Cell other) {
		boolean sePuede;

		if ( other.getValor() == self.getValor() && self.getValor() != 0){ // Si vale lo vismo esta celda que la vecina...
			sePuede = true;
		}
		else
			sePuede = false;
		return sePuede;
	}

}
