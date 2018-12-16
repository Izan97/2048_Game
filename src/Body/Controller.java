package Body;

import java.io.IOException;
import java.util.Scanner;

import Commands.Command;
import Commands.CommandParser;
import Exceptions.ErrorDeLectura;
import Exceptions.ErroresLoadCommand;
import Exceptions.FinDePartida;
import Exceptions.NoFile;
import Exceptions.NoMove;
import Exceptions.PilaVacia;

public class Controller {

	private Game game; // Partida
	private Scanner in; // Para leer de la consola las ordenes del usuario
	private Command comando;


	public Controller(Game juego, Scanner in){

		this.in = in;
		this.game = juego;
	}


	public void run(){
		String entradaTeclado;
		String [] usuario;
		boolean muestro;
		boolean terminado = false; 

		game.iniBoard();

		System.out.println(game.toString());

		while ( terminado == false){
			try {

				System.out.println ("Command > ");
				entradaTeclado = in.nextLine (); //Invocamos un metodo sobre un objeto Scanner
				usuario = entradaTeclado.split(" +");
				comando = CommandParser.parseCommand(usuario);			
				muestro = comando.execute(game);				
				if (muestro)
					showBoard();
				game.comprobarEstadoGame();
			}

			catch(NoFile e){
				e.muestraError();
			}
			catch (ErroresLoadCommand e) {
				e.muestraError();
			}
			catch(ErrorDeLectura e){
				System.out.println (e.getMessage());
			} 
			catch (PilaVacia e) {
				e.muestraError();
			}
			catch(NoMove e){
				System.out.println (e.getMessage());
				showBoard();
			}
			catch(IOException e){
				System.out.println("Ha ocurrido algun error con el fichero");
			}
			catch (ArrayIndexOutOfBoundsException e){			
				System.out.println("Se ha detectado un error en el tamaño del tablero ");			
			}
			catch (NumberFormatException excepcion) {
				System.out.println("Error con el formato de los numeros");

			}	
					
			catch (FinDePartida e){
				e.muestraFinal();
				terminado = true;
			}
			

		} // FIN DEL WHILE


	}//FIN DEL METODO

	public void showBoard(){
		System.out.println(game.toString());

	}



}
