package Body;

public class Position {
	
	
	private int row;
	private int col;
	
	

	
	// Constructora habitual
	public  Position (int row,  int col){
		this.row = row;
		this.col = col;
	}

	// Constructura vacia
	//public  Position (){}


	

	public int getRow(){	
		return row;
	}
	
	
	public int getCol(){			
		return col;
	}
	
	
	public Position getNeighbour(Direction dir){
		
		Position pos = null;
		
		
		
		
		switch (dir) {
		// No nos hace falta el size  porque lo controlamos en el board
		
		case UP: //if( row  != size -1 )
			pos = new Position(row + 1, col);  // cell[row][col]
		break;
		case DOWN: //if( row != size -1 ) 
			pos = new Position(row - 1, col);
		break;
		case LEFT: //if( col != 0 )
			pos = new Position(row, col + 1);
		break;
		case RIGHT: //if( col != size - 1 )
			pos = new Position(row , col - 1);
		break;
		
		
		}
			
				
		return pos;
	}
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Position [row = " + row + " , col = " + col + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

}
