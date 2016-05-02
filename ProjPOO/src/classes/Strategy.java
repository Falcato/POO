package classes;

public abstract class Strategy {

	Hand dealerHand;
	Hand playerHand;
	
	public Strategy(Hand h1, Hand h2){
		dealerHand = h1;
		playerHand = h2;
	}
	
	abstract void advice();
}
