package Commands;

import Body.Game;
import Exceptions.PilaVacia;

public class RedoCommand extends NoParamsCommand {

	public RedoCommand() {
		super("Redo", "Rehace los undo");
		
	}

	@Override
	public boolean execute(Game game) throws PilaVacia {
		game.redo();
		return true;
	}



}
