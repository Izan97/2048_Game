package Rules;

import java.util.Random;

import Body.Board;
import Body.Cell;
import Body.Position;

public class RulesFib implements GameRules {

	private final int STOP_VALUE = 144;


	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {

		double alea;
		int numero;

		alea = rand.nextDouble();

		if (alea < 0.9)    // el 90% de las veces 
			numero = 1;
		else      
			numero = 2;

		board.setCell(pos, numero);

	}

	@Override
	public int merge(Cell self, Cell other) { 
		// fusiona si son celdas tal que una es la sucesion de la otra de fibonacci
		int  n, k, next, suma = 0; 

		n = self.getValor();
		k = other.getValor();

		if ( n == 1 && k == 1){
			self.mutador(k); // Nuestra celda con el nuevo valor
			other.setValor(0);// la celda vecina a 0
			suma = self.getValor();
		}		

		/////////////////////////////////////////////////////////////////////		
		// si primero va n y despues k

		next = nextFib(n);

		if (next == k){
			self.mutador(k); // Nuestra celda con el nuevo valor
			other.setValor(0);// la celda vecina a 0
			suma = self.getValor();			
		}
		else {
			// si primero va k y despues n

			next = nextFib(k);
			if (next == n){
				self.mutador(k); // Nuestra celda con el nuevo valor
				other.setValor(0);// la celda vecina a 0
				suma = self.getValor();			
			}

		}
		//////////////////////////////////////////////////////////////////////////					
		return suma;
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

	@Override
	public boolean noMerge(Cell self, Cell other) {

		boolean sePuede = false;
		int  n, k, next; 

		
		n = self.getValor();
		k = other.getValor();
		
		if ( n == 1 && k == 1){
			sePuede = true;
		}		

		// si primero va n y despues k

		next = nextFib(n);

		if (next == k){
			sePuede = true;			
		}
		// si primero va k y despues n

		next = nextFib(k);
		
		if (next == n){
			sePuede = true;		
		}
		

		
		return sePuede;

	}


	private  int nextFib(int previous) {

		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);

	}

}
