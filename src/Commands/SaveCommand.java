package Commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Body.Game;
import Exceptions.FinDePartida;
import Exceptions.NoMove;
import Exceptions.PilaVacia;
import Utils.MyStringUtils;



public class SaveCommand extends Command {



	private String fichero = null;
	public static final String filenameInUseMsg  = "The file already exists ; do you want to overwrite it ? (Y/N)";
	private boolean filename_confirmed;



	public SaveCommand (String nombreF) {

		super("Save", "Guarda una partida");
		this.fichero = nombreF;
	}





	public boolean execute(Game game) throws PilaVacia, FinDePartida, NoMove, IOException {
		BufferedWriter fileW = null;
		try {
			FileWriter file = new FileWriter(this.fichero);
			fileW = new BufferedWriter(file);
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al guardar el fichero");
		}

		if ( fileW == null)
			return false;

		game.store(fileW);


		return true;
	}


	public Command parse(String[] commandWords, Scanner in) {
		Command command = null;
		String fileName = null;

		if ( commandWords.length == 2){
			if(commandWords[0].equalsIgnoreCase("Save")){  // move + direction			   
				fileName = confirmFileNameStringForWrite(commandWords[1], in);
				command = new SaveCommand(fileName);
				System.out.println("partida guardada correctamente en el fichero: " + fileName);
			}
		}

		return command;
	}


	private String confirmFileNameStringForWrite(String filenameString, Scanner in) { 
		String loadName = filenameString; 
		filename_confirmed = false; 
		while (!filename_confirmed) {

			if (MyStringUtils.validFileName(loadName)) {  // Si es valido el nombre
				File file = new File(loadName);  // crea el file
				if (!file .exists())  // si no existe
					filename_confirmed = true;  // archivo valido y me salgo del bucle
				else {  //Si existe...
					// Devuelve el nombre del fichero ya en condiciones ( puede ser null )
					loadName = getLoadName(filenameString, in); 
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




	public String getLoadName(String filenameString, Scanner in) { 
		String newFilename = null; 
		boolean yesOrNo = false; 
		while (!yesOrNo) { 
			System.out.print(filenameInUseMsg + ": ");  // le pregunto si quiere sobreescribirlo
			String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+"); 
			if (responseYorN.length == 1) { 
				switch (responseYorN[0]) { 
				case "y": 
					yesOrNo = true; 
					// si quiere sobreescribirlo....
					File fichero = new File(filenameString);
					if( fichero.delete())
					newFilename = filenameString;
					break;

				case "n": 
					yesOrNo = true;
					//si no quiere sobreescribirlo...
					System.out.print("Dime otro nombre de fichero: ");  // le pregunto si quiere sobreescribirlo
					String[] nuevoNombre = in.nextLine().toLowerCase().trim().split(" +");
					if (nuevoNombre.length == 1)
					newFilename = nuevoNombre[0];
					else
						yesOrNo = false;
					break;

				default: 
					//lo esgrito no es valido
					System.out.print("Palabra escrita no valida, te lo repito: ");
				} 
			} 
			else { 
				//lo escrito no es valido
				System.out.print("Frase escrita no valida, te lo repito: ");
			} 
		} 
		return newFilename; 
	}



}

