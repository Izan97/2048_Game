package Commands;


import java.util.Scanner;

import Exceptions.ErrorDeLectura;
import Exceptions.NoFile;



public class CommandParser {
	
	
	private final static Command[]comandos = {new HelpCommand(),  new ResetCommand(),new ExitCommand(), 
		new MoveCommand(), new UndoCommand(), new RedoCommand(), new PlayCommand(), new SaveCommand("Defecto"),
		new LoadCommand("Defecto")};
	
	
	public static Command parseCommand(String[] commandWords) throws ErrorDeLectura, NoFile {

		
		boolean buscado = false;
		Scanner teclado = new Scanner (System.in);
	
		Command comando = null;
		
		int i = 0;
		
		while(i < comandos.length && !buscado){
			comando = comandos[i].parse(commandWords,teclado);
			if(comando != null)
				buscado = true;
			else
				i++;
		}
		if (comando == null)
			throw new ErrorDeLectura();
		
		return comando;
	}
	


	public static void commandHelp(){
		
		for(int i=0; i< CommandParser.comandos.length; i++)
			System.out.println(CommandParser.comandos[i].helpText());
	}

	
}
