package Commands;

import Body.Game;



public class HelpCommand extends NoParamsCommand{




	public HelpCommand() {
		super ("Help", "Muestra ayuda del juego");
	}


	@Override
	public boolean execute(Game game) {
		
		CommandParser.commandHelp();
		return true;
		
	}



}
