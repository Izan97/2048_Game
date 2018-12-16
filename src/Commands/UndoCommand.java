package Commands;

import Body.Game;
import Exceptions.PilaVacia;

public class UndoCommand extends NoParamsCommand {

	public UndoCommand() {
		super("Undo", "Hace un movimiento hacia atras");
	}

	@Override
	public boolean execute(Game game) throws PilaVacia {
		game.undo();
		return true;
		
	}


}
