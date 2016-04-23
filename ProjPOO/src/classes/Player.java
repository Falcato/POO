package classes;

public class Player{
	
	private int money;
	Hand hand;
	
	public Player(){
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
	
	public int Bet(int bet, int minBet, int maxBet){
		if(bet < minBet || bet > maxBet) return 0;
		return setMoney(getMoney() - bet);
	}
}
