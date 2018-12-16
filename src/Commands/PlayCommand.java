package Commands;

import java.util.Random;
import java.util.Scanner;

import Body.Game;
import Body.GameType;
import Exceptions.ErrorDeLectura;
import Exceptions.ErroresPlayCommand;

public class PlayCommand extends Command{

	protected int boardSize = 4, initialCells = 2;
	protected int sizeVariable, initialCellsVariable;
	protected long randomSeed;
	protected GameType gameType;


	public PlayCommand() {
		super("Play", "Juega al juego 'X' ");

	}

	@Override
	public boolean execute(Game game) {

		game.changeRules(gameType);
		game.playGame(this.sizeVariable,this.initialCellsVariable,randomSeed);
		return true;
	}


	@Override
	public Command parse(String[] commandWords, Scanner teclado) throws ErrorDeLectura {
		Command devuelvo = null;

		if (commandWords.length == 2){
			if(commandWords[0].equalsIgnoreCase("PLAY")){				
				gameType = GameType.parse(commandWords[1].toLowerCase());
			}
			else 
				return null;
			

			if ( gameType != null){

				String entradaTeclado;
				String [] usuario;
				int tama = this.boardSize, inicell = this.initialCells;
				int semilla;
				this.randomSeed = new Random().nextInt(1000);

				boolean terminado = false;


				System.out.println ("Inserta el Size: ");

				while ( terminado == false){

					entradaTeclado = teclado.nextLine (); //Invocamos un metodo sobre un objeto Scanner
					usuario = entradaTeclado.split("\n");

					if(!usuario[0].equalsIgnoreCase("")){

						try{
							tama = Integer.parseInt(usuario[0]); // excepcion de fallo aqui
							if ( tama <= 1 )
								throw new ErroresPlayCommand("size", usuario[0]);
							this.sizeVariable = tama;
							terminado = true;					
						}
						catch (NumberFormatException excepcion) {
							terminado = false;
							System.err.println("No has metido un entero tramposillo, repite anda: ");
						}
						catch (ErroresPlayCommand excepcion) {
							terminado = false;
							excepcion.muestraError();
						}
					}

					else {
						terminado = true;
						System.out.println("Se ha cogido por defecto el valor: " + this.boardSize + "\n");
						this.sizeVariable = this.boardSize;
					}
				}
				terminado = false;
				/////////////////////////////////////////////////////////////////////

				System.out.println ("Inserta el initcells: ");

				while ( terminado == false){

					entradaTeclado = teclado.nextLine (); //Invocamos un metodo sobre un objeto Scanner
					usuario = entradaTeclado.split("\n");

					if(!usuario[0].equalsIgnoreCase("")){
						try{
							inicell = Integer.parseInt(usuario[0]); // excepcion de fallo aqui
							int aux = tama * tama;
							if (0 > inicell )
								throw new ErroresPlayCommand("las celdas iniciales", usuario[0]);
							if ( aux < inicell )
								throw new ErroresPlayCommand("las celdas iniciales", usuario[0]);
							this.initialCellsVariable =inicell;
							terminado = true;
						}
						catch (NumberFormatException excepcion) {
							terminado = false;
							System.err.println("No has metido un entero tramposillo, repite anda: ");
						}
						catch (ErroresPlayCommand excepcion) {
							terminado = false;
							excepcion.muestraError();
						}
					}
					else {
						terminado = true;
						System.out.println("Se ha cogido por defecto el valor: " + this.initialCells + "\n");
						this.initialCellsVariable = this.initialCells;
					}
				}
				terminado = false;

				////////////////////////////////////////////////////////////

				System.out.println ("Inserta el seed: ");

				while ( terminado == false){

					entradaTeclado = teclado.nextLine (); //Invocamos un metodo sobre un objeto Scanner
					usuario = entradaTeclado.split("\n");

					if(!usuario[0].equalsIgnoreCase("")){
						try{
							semilla = Integer.parseInt(usuario[0]); // excepcion de fallo aqui	
							this.randomSeed = new Random().nextInt(semilla);
							terminado = true;
						}
						catch (NumberFormatException excepcion) {
							terminado = false;
							System.err.println("No has metido un entero tramposillo, repite anda: ");
						}
					}
					else {
						terminado = true;
						System.out.println("Se ha cogido por defecto el valor: " + randomSeed + "\n");
					}																			

				}
				// HASTA AQUI TENGO TODOS LOS VALORES YA VALIDOS
				devuelvo = this;
			}
			else
				throw new ErrorDeLectura();
		}
		return devuelvo;
	}
}


