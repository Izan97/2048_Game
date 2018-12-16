package Commands;


import java.util.Scanner;

import Body.Direction;
import Body.Game;
import Exceptions.ErrorDeLectura;
import Exceptions.FinDePartida;
import Exceptions.NoMove;
import Exceptions.PilaVacia;


public class MoveCommand extends Command {

	private Direction dir = null;

	public MoveCommand() {
		super("Move", "Mueve en una direccion");

	}

	@Override
	public boolean execute(Game game) throws PilaVacia, FinDePartida, NoMove {

		game.move(dir);
		return true;

	}

	@Override
	public Command parse(String[] commandWords,Scanner in) throws ErrorDeLectura {

		if (commandWords.length == 2){
			if(commandWords[0].equalsIgnoreCase("MOVE")){  // move + direction
				this.dir = Direction.parse(commandWords[1].toLowerCase());
				if (this.dir == null)
					throw new ErrorDeLectura();
				return this;
			}
			else
				return null;

		}	
		return null;
	}


}
