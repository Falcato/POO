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

	public void getHand() {
		System.out.println(hand.toString());
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
