package Commands;



import java.io.IOException;
import java.util.Scanner;

import Body.Game;
import Exceptions.ErrorDeLectura;
import Exceptions.ErroresLoadCommand;
import Exceptions.FinDePartida;
import Exceptions.NoFile;
import Exceptions.NoMove;
import Exceptions.PilaVacia;


public abstract class Command {
	
	
	private String helpText;
	private String commandText;
	protected final String commandName;
	
	
	
	public Command(String commandInfo, String helpInfo){
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}
	
	public abstract boolean execute(Game game) throws PilaVacia, FinDePartida, NoMove, IOException, ErroresLoadCommand;
	
	public abstract Command parse(String[] commandWords, Scanner in) throws NoFile, ErrorDeLectura;
	
	
	
	public String helpText(){
		
		return " " + commandText + ": " + helpText;
		
		}
}