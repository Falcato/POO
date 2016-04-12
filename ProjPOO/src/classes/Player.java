package classes;

public class Player extends Dealer{
	
	private int money;
	Hand hand;
	Shoe shoe;
	
	public Player(int money){
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public int setMoney(int money) {
		
		if(money < 0){
			System.out.println("Not enough money!!");
			System.out.println("Place a smaller bet!!");
			return -1;
		}
		this.money = money;
		return 1;
	}

	public void getHand() {
		System.out.println("player " + hand.toString());
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
		getHand();
	}
	
	public int Bet(int bet){
		return setMoney(getMoney() - bet);
	}
}
