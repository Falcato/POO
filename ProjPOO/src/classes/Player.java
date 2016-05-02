package classes;

public class Player{
	
	Dealer dealer;
	private float money;
	int currBet = 0;
	//int insurance = 0;
	int doubleDown = 0;
	int nrHands;
	Hand hands[] = new Hand[6];
	Strategy bs;
	HiLo hls;
	
	public Player(Dealer d){
		dealer = d;
		nrHands = 0;
	}

	float getMoney() {
		return money;
	}

	float setMoney(float money) {
		
		if(money < 0){
			System.out.println("Not enough money!!");
			System.out.println("Place a smaller bet!!");
			return -1;
		}
		this.money = money;
		return 1;
	}

	void getHand(int handNr) {
		System.out.println("Player's hand[" + handNr + "] " + hands[handNr].toString());
	}
	
	int getCurrBet(){
		return currBet;
	}
	
	float Bet(int bet, int minBet, int maxBet){
		currBet = bet;
		if(bet < minBet || bet > maxBet) return 0;
		return setMoney(getMoney() - bet);
	}
	
	void hit(int handNr){
		dealer.giveCard(this, handNr);
	}
	
	void stand(){
		
	}
	
	/*
	void insure(){
		insurance = 1;
	}
	
	int checkInsurance(){
		return insurance;
	}*/
	
	void split(int i){
		
		nrHands++;
		hands[nrHands] = new Hand(hands[i].getCard(1), hands[i].getCard(1), true);
		hands[i].removeCard();
		hands[nrHands].removeCard();
		dealer.giveCard(this, i);
		dealer.giveCard(this, nrHands);
				
	}
	
	void surrender(){
		
	}
	
	void doubleDown(int minBet, int maxBet){
		Bet(currBet, minBet, maxBet);
		currBet = 2 * currBet;
		doubleDown = 1;
	}
	
	int checkDoubleDown(){
		return doubleDown;
	}
	
	void advice(int i){
		bs = new Basic(dealer.hand, hands[i]);
		System.out.print("Basic:	");
		bs.advice();
		
		hls = new HiLo(dealer.hand, hands[i]);
		hls.runningCnt = dealer.getShoe().getRunningCnt();
		hls.nrDecksLeft = dealer.getShoe().getNrDecksLeft();
		System.out.print("Hi-Lo:	");
		hls.advice();
	}
}
