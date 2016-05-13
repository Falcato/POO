package classes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * The shoe class is where the cards to be played with
 * are stored. Only the dealer has access to the shoe.
 * The shoe represents a real shoe in a blackjack game.
 * It is associated to a dealer and can be generated
 * by the number of decks in interactive and simulation
 * modes or can be read from a file in debug mode.
 */
public class Shoe {

	int nrDecks;
	private int nrDecksLeft;
	private int shuffles;
	private ArrayList<Card> shoe = new ArrayList<Card>();
	private int nrCardsTaken;
	private int nrCards;
	private int runningCnt;
	private int shufflesDone;
	
	private int aceFive;
	
	/**
	 * Constructor for class Shoe. Receives the number of total decks,
	 * an array of cards (for debug mode, read from the input file),
	 * the game mode and the percentage of the shoe to be used before
	 * a shuffle is performed.
	 * Initializes the local variables and, depending on the game mode,
	 * initializes the shoe, if in debug mode, by the array of cards,
	 * otherwise by generating the number of cards according to the
	 * number of decks.
	 * 
	 * @param nrDecks input parameter of the number of decks to be used
	 * 				  1 if in debug mode
	 * @param cardsinit array of cards to be used in the debug mode
	 * @param mode input parameter regarding the game mode
	 * @param shuffles input parameter regarding the percentage of the shoe
	 * 					to be used before shuffling
	 */
	public Shoe(int nrDecks, ArrayList<Card> cardsinit, String mode, int shuffles) {
		
		this.nrDecks = nrDecks;
		this.nrDecksLeft = nrDecks;		
		this.nrCardsTaken = 0;
		this.nrCards = 0;
		this.shuffles = shuffles;
		shufflesDone = 0;
		runningCnt = 0;
		aceFive = 0;
		
		if(mode.equals("-d")){
			for(int i = 0; i < nrDecks * cardsinit.size(); i++){
				shoe.add(cardsinit.get(i));
				nrCards++;
			}
		}else if(mode.equals("-i") || mode.equals("-s")){
			
		
			for(int i = 0; i < nrDecks; i++){
				Card cards = new Card("A" + "S");
				shoe.add(cards);
				Card cardd = new Card("A" + "D");
				shoe.add(cardd);
				Card cardc = new Card("A" + "C");
				shoe.add(cardc);
				Card cardh = new Card("A" + "H");
				shoe.add(cardh);
				nrCards += 4;
			}
			
			for(int i = 2; i < 11; i++){
				for(int j = 0; j < nrDecks; j++){
					Card cards = new Card(i + "S");
					shoe.add(cards);
					Card cardd = new Card(i + "D");
					shoe.add(cardd);
					Card cardc = new Card(i + "C");
					shoe.add(cardc);
					Card cardh = new Card(i + "H");
					shoe.add(cardh);
					nrCards += 4;
				}
			}
			for(int i = 0; i < nrDecks; i++){
				Card cards = new Card("J" + "S");
				shoe.add(cards);
				Card cardd = new Card("J" + "D");
				shoe.add(cardd);
				Card cardc = new Card("J" + "C");
				shoe.add(cardc);
				Card cardh = new Card("J" + "H");
				shoe.add(cardh);
				nrCards += 4;
			}
			for(int i = 0; i < nrDecks; i++){
				Card cards = new Card("Q" + "S");
				shoe.add(cards);
				Card cardd = new Card("Q" + "D");
				shoe.add(cardd);
				Card cardc = new Card("Q" + "C");
				shoe.add(cardc);
				Card cardh = new Card("Q" + "H");
				shoe.add(cardh);
				nrCards += 4;
			}
			for(int i = 0; i < nrDecks; i++){
				Card cards = new Card("K" + "S");
				shoe.add(cards);
				Card cardd = new Card("K" + "D");
				shoe.add(cardd);
				Card cardc = new Card("K" + "C");
				shoe.add(cardc);
				Card cardh = new Card("K" + "H");
				shoe.add(cardh);
				nrCards += 4;
			}
		}
	}
	
	/**
	 * Return the number of decks left, according to
	 * the number of cards taken from the shoe and the
	 * initial number of decks.
	 * 
	 * @return number of decks left
	 */
	int getNrDecksLeft() {
		return nrDecksLeft;
	}

	/**
	 * Returns the number of cards taken from the shoe,
	 * until a shuffle is done. Then it is reset.
	 * 
	 * @return number of cards taken
	 */
	int getNrCardsTaken() {
		return nrCardsTaken;
	}

	/**
	 * Returns the number of total cards in the shoe.
	 * The parameter is only modified in the constructor
	 * of the shoe.
	 * 
	 * @return number cards in the shoe
	 */
	int getNrCards() {
		return nrCards;
	}
	
	/**
	 * Returns the number of shuffles performed since the
	 * beggining of the game. This variable is used in
	 * simulation mode.
	 * 
	 * @return number of shuffles performed
	 */
	int getShufflesDone(){
		return shufflesDone;
	}
	
	/**
	 * Returns the value of the running count which is
	 * used in the high-low strategy.
	 * 
	 * @return value of the running count
	 */
	int getRunningCnt(){
		return runningCnt;
	}
	
	/**
	 * Returns the value of the ace-five variable,
	 * which is used in the ace-five strategy.
	 * 
	 * @return value of ace-five
	 */
	int getAceFive(){
		return aceFive;
	}
	
	/**
	 * Adds a card to the shoe, it is used in the shoe
	 * constructor and when a card is taken from the shoe
	 * it is used to readd it. Receives the card to be
	 * added.
	 * 
	 * @param c card to be added
	 */
	void addCard(Card c){
		shoe.add(c);
	}
	
	/**
	 * Shuffles the shoe.
	 */
	void Shuffle(){
		Collections.shuffle(shoe);
	}
	
	/**
	 * Receives a card as argument and based on the value
	 * of the received card returns the card's running
	 * count value, as specified in the project.
	 * This value is to be added to the runningCnt attribute.
	 * 
	 * @param c card analysed
	 * @return the card's running count value
	 */
	int runningCntValue(Card c){
		if(c.getValue() < 7) return 1;
		else if(c.getValue() < 10) return 0;
		else return -1;
	}
	
	/**
	 * Receives a card as argument an returns the value
	 * of the ace-five value of the card. This value
	 * is to be added to the aceFive attribute.
	 * 
	 * @param c card to be analysed
	 * @return card's ace-five value
	 */
	int aceFiveValue(Card c){
		if(c.getValue() == 5) return 1;
		else if(c.getValue() == 11) return -1;
		else return 0;
	}
	
	/**
	 * Gets a card from the shoe and returns it. This
	 * method is only used by the dealer as the player
	 * is not supposed to access the shoe.
	 * The number of decks left, number of cards taken,
	 * running count value, ace-five value and shuffles
	 * performed attributes are set and/or reset in this
	 * function.
	 * If the card taken is an ace, it's value is reset to
	 * 11 as it may be 1.
	 * If the conditions to shuffle are met the shuffling
	 * is called and the attributes are reset.
	 * 
	 * @return card taken from the shoe
	 */
	Card getCard() {
		
		nrDecksLeft = nrDecks - (nrCardsTaken) / 52;
		Card c = shoe.get(nrCardsTaken++);
		if(c.getValue() == 1) c.setValue("A");
		
		runningCnt += runningCntValue(c);
		aceFive += aceFiveValue(c);
		
		shoe.add(c);
		if(nrCardsTaken == nrCards * shuffles / 100){
			aceFive = 0;
			runningCnt = 0;
			Shuffle();
			shufflesDone++;
			System.out.println("Shuffling Shoe...");
			
			nrCardsTaken = 0;
			nrDecksLeft = nrDecks;
		}
		return c;
	}
}
