package classes;

public class Dealer {
	
	Hand hand;
	Shoe shoe;
	
	public Dealer(){
	
	}

	public void getHand() {
		System.out.println("dealer " + hand.toString());
	}
	
	public void Deal() {
		hand = new Hand(shoe.getCard(), shoe.getCard());
		getHand();
	}
	
	public void Hit(){
		hand.addCard(shoe.getCard());
		getHand();
	}
	
	public void Stand(){
		//nao faz nada
		getHand();
	}

}
