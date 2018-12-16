package Commands;

import Body.Game;
import Exceptions.FinDePartida;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		
				super ("Exit", "Se sale del juego");
	}


	public boolean execute(Game game) throws FinDePartida {
		
		game.changeEstado();

		return true;
		
	}





}
