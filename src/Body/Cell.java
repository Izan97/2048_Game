package Body;

import Rules.GameRules;

public class Cell {
	
	private int  valor;
	
	
	public  Cell (int valor){
		this.valor = valor;
		
	}
	
	public boolean isEmpty(){
		
		boolean empty = false;
		
		if (valor == 0) 
				empty = true;
		
		
		return empty;
	}
	
	
	public int getValor(){
		return valor;
	}
	
	
	public void setValor(int valor){
		 this.valor = valor;
	}
	
	
	public void mutador(int valor){
		
		this.valor = this.valor + valor;
		
		
	}
	
	
	
	public int doMerge(Cell neighbour, GameRules rules){
		
		return rules.merge(this, neighbour);
	}
	
	
	
	
	public boolean doMergeNoMove(Cell neighbour, GameRules rules){
	
		return rules.noMerge(this, neighbour);
	}

}
