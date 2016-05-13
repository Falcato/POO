package classes;
/**
 * 
 * Implements a dealer in a blackjack game. The dealer
 * is the main character of a blackjack game as he is
 * the one who gives both money and cards to players.
 * He controls the table and all its variables.
 */
public class Dealer {
	
	Hand hand;
	private Shoe shoe;
	Player players[] = new Player[6];
	int CntPlayers = 0;
	
	
	/**
	 * Receives the shoe that is to be associated
	 * with this dealer.
	 * @param shoe shoe to be associated
	 */
	void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	/**
	 * Returns the current associated shoe.
	 * @return shoe associated with dealer
	 */
	public Shoe getShoe() {
		return shoe;
	}
	
	/**
	 * Constructor for class Dealer.
	 */
	public Dealer(){
	}
	
	/**
	 * Associates a player to this dealer.
	 * @param p1 player to be "added" to dealer
	 */
	void addPlayer(Player p1){
		players[CntPlayers] = p1;
		CntPlayers ++;
	}
	
	/**
	 * Prints the dealer's current hand to the
	 * terminal.
	 */
	public void getHand() {
		System.out.println("Dealer's hand " + hand.toString());
	}
	
	/**
	 * Deals the initial cards to himself and the players.
	 * @param simMode associated with simulation mode,
	 * prevents prints on the terminal
	 */
	void Deal(int simMode) {
		
		hand = new Hand(shoe.getCard(), shoe.getCard(), false);
		if(simMode == 0)
			getHand();
		
		for(int i = 0; i < CntPlayers; i++){
			players[i].hands[0] = new Hand(shoe.getCard(), shoe.getCard(), true);
			if(simMode == 0)
				players[i].getHand(0);
			players[i].nrHands = 0;
		}
	}
	
	/**
	 * Takes a card from the shoe and gives
	 * it to himself, turning the card that
	 * was faced down, if there was any.
	 */
	void getCard(){
		Card c = shoe.getCard();
		if(c.turned == false)
			c.turned = true;
		hand.addCard(c);
		hand.turnCard();
		//getHand();
	}
	
	/**
	 * Takes a card from the shoe and
	 * gives it to a certain player's hand.
	 * @param p player to be given the card
	 * @param handNr number of the player's
	 * hand to be given the card
	 */
	void giveCard(Player p, int handNr){
		Card c = shoe.getCard();
		if(c.turned == false)
			c.turned = true;
		p.hands[handNr].addCard(c);
		//p.getHand(handNr);
	}
	
	/**
	 * Gives money to a certain player, depending
	 * on the bets made and outcome of the hand.
	 * @param p player to be given the money
	 * @param money amount of money to be given
	 */
	void giveMoney(Player p, float money){
		p.setMoney(p.getMoney() + money);
	}
	
}
