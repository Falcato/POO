package classes;

/**
 * 
 * The strategy class is the "interface" for both the
 * basic and high-low strategies.
 * It contains only one operation that is the choosing
 * of the advice itself. The advice can use card counting
 * tactics or not, and it's returned in the form of a 
 * String, e.g, "hit", "stand", "split", etc.
 */
public abstract class Strategy {

	Hand dealerHand;
	Hand playerHand;
	
	/**
	 * Constructor for the Strategy class.
	 * For the subclasses of this class this
	 * constructor is called to initialize the
	 * dealer's and player's hands.
	 * 
	 * @param h1 dealer's hand
	 * @param h2 player's hand
	 */
	public Strategy(Hand h1, Hand h2){
		dealerHand = h1;
		playerHand = h2;
	}
	
	
	/**
	 * Strategy used when a split is the next best move
	 * but can't be performed. Hit if hand's value is
	 * bigger or equal to 17, stand otherwise. If it's
	 * a aces hand split the only option is to stand.
	 * 
	 * @param h player's hand
	 * @return the next valid move
	 */
	String altStrategy(Hand h){
		
		if(h.getCard(0).getFigure().equals("A")){
			return "stand";
		}else if(h.getTotalValue() < 17)
			return "hit";
		else
			return "stand";
		
	}
	
	
	/**
	 * Receives the flags referring to the moves: double down,
	 * insure, split and surrender, and produces a string with
	 * the best move to be made, base on those flags.
	 * It's implemented in Basic and HiLo classes, and according
	 * to these strategies' "tables", it returns "hit", "stand", 
	 * "split", etc., based on the best move to be made.
	 * This advice is used in simulation mode to ditacte the next 
	 * move and in the interactive and debug modes to print in the
	 * terminal the next best move.
	 * 
	 * @param dd 1 if possible to double down, 0 otherwise
	 * @param in 1 if possible to insure, 0 otherwise
	 * @param sp 1 if possible to split, 0 otherwise
	 * @param su 1 if possible to surrender, 0 otherwise
	 * @return a string with the next move to be made, based on
	 * the tables and flags passed to the operation.
	 */
	abstract String advice(int dd, int in, int sp, int su);
}
