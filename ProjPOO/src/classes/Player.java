package classes;

public class Player {
	
	private int money;
	Hand hand;
	Shoe shoe;
	
	public Player(int money){
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public Hand getHand() {
		//implementar toString
		return hand;
	}
	
	public void Deal() {
		hand = new Hand(shoe.getCard(), shoe.getCard());
	}
	
	public void Hit(){
		hand.addCard(shoe.getCard());
	}
	
	public void Stand(){
		//nao faz nada
	}

}
