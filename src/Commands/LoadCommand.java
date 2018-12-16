package Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Body.Game;
import Body.GameType;
import Exceptions.ErroresLoadCommand;
import Exceptions.FinDePartida;
import Exceptions.NoFile;
import Exceptions.PilaVacia;


/* This code supposes the following attribute declarations : 
 * private boolean filename_confirmed; 
 * public static final filenameInUseMsg  = "The file already exists ; do you want to overwrite it ? (Y/N)"; 
 * You may also need to add a throws clause to the declarations 
 */

public class LoadCommand extends Command {

	
	
	private String fichero = null;
	private GameType tipoJ = null;
	
	
	
	public LoadCommand (String nombreF) {

		super("Load", "Carga una partida");
		this.fichero = nombreF;
	}





	public boolean execute(Game game) throws ErroresLoadCommand, FinDePartida, PilaVacia {
		BufferedReader fileR = null;
		try {
			FileReader file = new FileReader(this.fichero);
			fileR = new BufferedReader(file);
			if ( fileR == null)
				return false;
			else{
				tipoJ = game.load(fileR);
				if (tipoJ != null)
					System.out.println("Partida cargada correctamente --> " + tipoJ);
				return true;
			}
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al ejecutar el comando load con este nombre: "+ this.fichero);
			return false;
		}
	}


	public Command parse(String[] commandWords, Scanner in) throws NoFile {
		Command command = null;
		
		if ( commandWords.length == 2){
			if(commandWords[0].equalsIgnoreCase("Load")){  // move + direction			   
				//fileName = confirmFileNameStringForWrite(commandWords[1], in);
				File file = new File(commandWords[1]);  // crea el file
				if (!file .exists())
					throw new NoFile(commandWords[1]);
				command = new LoadCommand(commandWords[1]);
				}			
			}
		
		return command;		
		
	}

/*
	private String confirmFileNameStringForWrite(String filenameString, Scanner in) { 
		String loadName = filenameString; 
		filename_confirmed = false; 
		while (!filename_confirmed) {			
			if (MyStringUtils.validFileName(loadName)) {  // Si es valido el nombre
				File file = new File(loadName);  // crea el file
				if (!file .exists())  // si no existe
					loadName = null; 
					else {  //Si existe...
					filename_confirmed = true;  // archivo valido y me salgo del bucle
					// Devuelve el nombre del fichero ya en condiciones ( puede ser null )
				} 
			} 
			else { 
				//Si el nombre no es valido 
				System.out.print (" El nombre de fichero no es valido, repitemelo pls : ");
				String[] nombreNuevo = in.nextLine().toLowerCase().trim().split(" +");
				loadName = nombreNuevo[0];
			} 
		} 
		return loadName; 
	}

*/


	


}
