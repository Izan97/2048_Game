package Commands;

import Body.Game;

public class ResetCommand extends NoParamsCommand {

	public ResetCommand() {
	
		super ("Reset", "Se reinicia el juego");
	}

	@Override
	public boolean execute(Game game) {
		
		game.reset();
		return true;
		
		
	}



}
