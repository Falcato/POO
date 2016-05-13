package classes;
/**
 *
 * A player is the one making plays, although it
 * is created a player in each game mode, only
 * in the interactive mode does the player have
 * a chance to freely choose his moves.
 * The player is associated to a dealer and has one
 * or more hands associated to himself.
 */
public class Player{
	
	Dealer dealer;
	private float money;
	int currBet = 0;
	//int insurance = 0;
	int doubleDown = 0;
	int nrHands;
	Hand hands[] = new Hand[4];
	Strategy bs;
	HiLo hls;
	
	/**
	 * Constructor for the class Player.
	 * Receives the dealer to be associated to
	 * this player and sets the number of hands
	 * of this particular player to zero.
	 * 
	 * @param d dealer to be associated
	 */
	public Player(Dealer d){
		dealer = d;
		nrHands = 0;
	}

	/**
	 * Returns the money of the player
	 * @return money of the player
	 */
	float getMoney() {
		return money;
	}

	/**
	 * Sets the money of the player based on the bets
	 * made by that player. Checks if the player's
	 * current money is bigger than zero, otherwise
	 * the bet cannot be made.
	 * 
	 * @param money new value of the player's money
	 * @return 1 if the money is positive
	 * 		   -1 if the money is negative
	 */
	float setMoney(float money) {
		
		if(money < 0){
			System.out.println("Not enough money!!");
			return -1;
		}
		this.money = money;
		return 1;
	}

	/**
	 * Prints a hand from the player's array of hands to
	 * the terminal.
	 * 
	 * @param handNr index of the hand to be printed
	 */
	void getHand(int handNr) {
		System.out.println("Player's hand[" + handNr + "] " + hands[handNr].toString());
	}
	
	/**
	 * Returns the current bet of the player.
	 * @return current bet
	 */
	int getCurrBet(){
		return currBet;
	}
	
	/**
	 * Makes a bet for the player. The bet must be
	 * in conformity with the input arguments the
	 * specify the maximum and minimum bets.
	 * Sets the new credit for the player and returns
	 * an integer to signal success or insuccess.
	 * 
	 * @param bet bet to be made
	 * @param minBet input argument for minimum bet
	 * @param maxBet input argument for maximum bet
	 * @return 1 if bet is successful
	 * 		   0 if the bet is not in conformity with
	 * the project specifications
	 * 		   -1 if the bet makes the player's credit
	 * negative
	 */
	float Bet(int bet, int minBet, int maxBet){
		currBet = bet;
		if(bet < minBet || bet > maxBet){ 
			return 0;
		}
		return setMoney(getMoney() - bet);
	}
	
	/**
	 * Action hit, performed by the player. Makes the
	 * dealer give a card to the current hand of the
	 * player. Calls the dealer's operation "giveCard"
	 * with the parameters: this player and current hand
	 * index.
	 * 
	 * @param handNr index of the current hand
	 */
	void hit(int handNr){
		dealer.giveCard(this, handNr);
	}
	
	/**
	 * Action stand, performed by the player. The player
	 * does nothing and ends his turn. Passing to the next
	 * hand and/or player.
	 */
	void stand(){
		
	}
	
	/*
	void insure(){
		insurance = 1;
	}
	
	int checkInsurance(){
		return insurance;
	}*/
	
	/**
	 * Action split, performed by the player. Makes the
	 * player split its current hand into two separate
	 * hands.
	 * The conditions that need to be fulfilled
	 * for this action are verified in the game being
	 * played.
	 * 
	 * @param i index of the player's current hand
	 * @return returns -1 if number of hands is exceeded
	 * 			-2 if next split will exceed the number
	 * 			of hands and 0 otherwise
	 */
	int split(int i){
		
		nrHands++;
		
		if(nrHands == 4){
			System.out.println("Maximum number of hands reached!!");
			nrHands--;
			return -1;
		}
		
		hands[nrHands] = new Hand(hands[i].getCard(1), hands[i].getCard(1), true);
		hands[i].removeCard();
		hands[nrHands].removeCard();
		dealer.giveCard(this, i);
		dealer.giveCard(this, nrHands);
		if(nrHands == 3)
			return -2;
		return 0;
	}
	
	/*void surrender(){
		
	}*/
	
	/**
	 * Action double down, performed by the player. Makes
	 * the player double its bet. Checks if the doubled
	 * bet is in conformity with the project specifications.
	 * Returns 0 if insuccess, new bet if success.
	 * The conditions that need to be fulfilled
	 * for this action are verified in the game being
	 * played.
	 * 
	 * @param minBet input argument for minimum bet
	 * @param maxBet input argument for maximum bet
	 * @return 0 if insuccess
	 * 		   new bet if success
	 */
	int doubleDown(int minBet, int maxBet){
		if(2 * currBet > maxBet) {
			doubleDown = 0;
		}else{
			Bet(currBet, minBet, maxBet);
			currBet = 2 * currBet;
			doubleDown = 1;
			return currBet;
		}
		return checkDoubleDown();
	}
	
	/**
	 * Returns the value of the local variable
	 * doubleDown.
	 * @return the local variable's value
	 */
	int checkDoubleDown(){
		return doubleDown;
	}
	
	/**
	 * Action advice, performed by the player. Creates
	 * one instance of each strategy and gets the advice
	 * given by each one of them in a vector, passing
	 * the necessary flags to the decision of the strategy,
	 * i.e., if there is the possibility to double down, 
	 * insure, split or surrender. The first
	 * position of the vector refers to the basic strategy
	 * and the second position to the high-low strategy. 
	 * Prints in the terminal the advice from the different
	 * strategies, if not in simulation mode.
	 * 
	 * @param i index of the player's current hand
	 * @param simMode 1 if in simulation mode, 0 otherwise
	 * @param dd 1 if there is the possibility to double down,
	 * 		     0 otherwise
	 * @param in 1 if there is the possibility to insure,
	 * 			 0 otherwise
	 * @param sp 1 if there is the possibility to split,
	 * 			 0 otherwise
	 * @param su 1 if there is the possibility to surrender,
	 * 			 0 otherwise
	 * @return used in simulation mode to receive the player's
	 * 		   next move. In non-simulation mode there is only
	 * 		   the need to print this information to the terminal
	 */
	String[] advice(int i, int simMode, int dd, int in, int sp, int su){
		
		String[] ret = new String[2];
		
		bs = new Basic(dealer.hand, hands[i]);
		
		hls = new HiLo(dealer.hand, hands[i]);
		hls.runningCnt = dealer.getShoe().getRunningCnt();
		hls.nrDecksLeft = dealer.getShoe().getNrDecksLeft();
		
		
		if(simMode == 0){
			System.out.println("Basic:	" + bs.advice(dd, in, sp, su));
			System.out.println("Hi-Lo:	" + hls.advice(dd, in, sp, su));
		}else{
			ret[0] = bs.advice(dd, in, sp, su);
			ret[1] = hls.advice(dd, in, sp, su);
			return ret;
		}
		
		return null;
	}
}
