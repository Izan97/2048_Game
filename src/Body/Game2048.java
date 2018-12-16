package Body;

import java.util.Random;
import java.util.Scanner;


public class Game2048 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int aux = 1000;
		int dim;
		int cells;
				
		//  args[0] = tamano del tablero
		//  args [1] = baldosas iniciales ocupadas	
		//  args[2]  = si no existe new Random()
		
		

		if (args.length == 3){
			try{
				aux = Integer.parseInt(args[2]);
			}catch (NumberFormatException excepcion) {

				System.out.println("Datos metidos por parametro incorrectos");
				System.out.println("He creado el tablero con la semilla " + aux);
			}

		}
			
		long seed = new Random().nextInt(aux); 
		//Random aleatorio = new Random(seed);
		// controlar que el numero de celdas iniciales no supere el tama�o del tablero

		Scanner teclado = new Scanner (System.in);
		
		try{
		 dim = Integer.parseInt(args[0]); // tamano
		 cells = Integer.parseInt(args[1]);
		 if ( cells > dim * dim)
			 throw new NumberFormatException();
		}
		catch (NumberFormatException excepcion) {
			 dim = 4;
			 cells = 2;
			System.out.println("Datos metidos por parametro incorrectos");
			System.out.println("He creado el tablero con los valores por defecto");
		}
		
		
		Game miJuego = new Game(GameType.ORIG,dim, cells,seed);
		Controller controlador = new Controller(miJuego,teclado);


		controlador.run();


	}

}