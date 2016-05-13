package classes;
/**
 *
 * The main function, which reads the input arguments
 * and decides which game mode to be played.
 */
public class Main {
	
	/**
	 * Receives the input arguments by the user and
	 * based on those arguments creates one of three
	 * game modes.
	 * 
	 * @param args input arguments
	 */
	public static void main(String[] args) {
		
		Game game = null;
		
		if(args[0].equals("-i")) 
			game = new Interactive(args);
		else if(args[0].equals("-d")) 
			game = new Debug(args);
		else if(args[0].equals("-s")) 
			game = new Simulation(args);
		else
			System.out.println("Invalid!!");
		
		game.game();
		
	}
}

