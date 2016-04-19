package classes;

public class Dealer {
	
	Hand hand;
	private Shoe shoe;
	Player players[] = new Player[6];
	int CntPlayers = 0;
	
	
	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	public Dealer(){
	}
	
	public void addPlayer(Player p1){
		players[CntPlayers] = p1;
		CntPlayers ++;
	}
	
	public void getHand() {
		System.out.println("dealer " + hand.toString());
	}
	
	void Deal() {
		hand = new Hand(shoe.getCard(), shoe.getCard());
		getHand();
		
		for(int i = 0; i < CntPlayers; i++){
			players[i].hand = new Hand(shoe.getCard(), shoe.getCard());
			players[i].getHand();
		}
	}
	
	void getCard(){
		hand.addCard(shoe.getCard());
		getHand();
	}
	
	void giveCard(Player p){
		p.hand.addCard(shoe.getCard());
		p.getHand();
	}
	
}
