package classes;

/**
 * 
 * The abstract class game is the "interface"
 * for all the game modes. It contains only
 * one operation that is the game itself.
 * This operation will then be realized by
 * the classes that extend this class.
 * The game can be seen as a table of a
 * blackjack game.
 */
public abstract class Game {

	Dealer d1;
	Player p1;
	Shoe shoe;
	String[] args;
	
	/**
	 * The actual BlackJack game with all
	 * its options and different plays
	 * for every player in the dealer's
	 * table. Includes the plays, betting,
	 * deals.
	 * Creates the dealer and player, the shoe
	 * to be played with.
	 * Goes through the player's play and the dealer's
	 * play in a while cycle, until the player runs
	 * out of money or the "q" command is entered.
	 */
	abstract void game();
	
}
