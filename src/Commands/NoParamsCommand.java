package Commands;




import java.util.Scanner;

import Body.Game;
import Exceptions.FinDePartida;
import Exceptions.PilaVacia;

public abstract class NoParamsCommand extends Command{

	public NoParamsCommand(String commandInfo, String helpInfo) {
		
		super(commandInfo, helpInfo);
	}

	@Override
	public abstract boolean execute(Game game) throws PilaVacia, FinDePartida ;
		

	@Override
	public Command parse(String[] commandWords,Scanner in) {
		
		if (this.commandName.equalsIgnoreCase(commandWords[0]))
			return this;
		else 
			return null;
		
	}



}
