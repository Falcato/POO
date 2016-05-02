package classes;

public class Dealer {
	
	Hand hand;
	private Shoe shoe;
	Player players[] = new Player[6];
	int CntPlayers = 0;
	
	
	void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	public Shoe getShoe() {
		return shoe;
	}
	
	public Dealer(){
	}
	
	void addPlayer(Player p1){
		players[CntPlayers] = p1;
		CntPlayers ++;
	}
	
	public void getHand() {
		System.out.println("Dealer's hand " + hand.toString());
	}
	
	void Deal() {
		
		hand = new Hand(shoe.getCard(), shoe.getCard(), false);
		getHand();
		
		for(int i = 0; i < CntPlayers; i++){
			players[i].hands[0] = new Hand(shoe.getCard(), shoe.getCard(), true);
			players[i].getHand(0);
			players[i].nrHands = 0;
		}
	}
	
	void getCard(){
		hand.addCard(shoe.getCard());
		hand.turnCard();
		getHand();
	}
	
	void giveCard(Player p, int handNr){
		Card c = shoe.getCard();
		if(c.turned == false) c.turned = true;
		p.hands[handNr].addCard(c);
		p.getHand(handNr);
	}
	
	void giveMoney(Player p, float money){
		p.setMoney(p.getMoney() + money);
	}
	
}
