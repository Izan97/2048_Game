package Rules;

import java.util.Random;

import Body.Board;
import Body.Cell;
import Body.Position;

public class Rules2048 implements GameRules {

	private final int STOP_VALUE = 2048;

	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		double alea;
		int numero;

		alea = rand.nextDouble();

		if (alea < 0.9)    // el 90% de las veces 
			numero = 2;
		else      
			numero = 4;

		board.setCell(pos, numero);

	}

	@Override
	public int merge(Cell self, Cell other) {
		int suma;


		if ( other.getValor() == self.getValor() && self.getValor() != 0){ // Si vale lo vismo esta celda que la vecina...

			self.mutador(self.getValor()); // Nuestra celda con el nuevo valor
			other.setValor(0);// la celda vecina a 0

			suma = self.getValor();
		}
		else 
			suma = 0;



		return suma;
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

	@Override
	public int getWinValue(Board board) {

		return board.getMaxValue();
	}

	@Override
	public boolean win(Board board) {

		if ( board.getMaxValue() == this.STOP_VALUE)
			return true;
		else
			return false;
	}



}
