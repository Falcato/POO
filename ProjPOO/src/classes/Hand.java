package classes;

import java.util.ArrayList;

public class Hand {
	
	private int totalValue;
	ArrayList<Card> hand = new ArrayList<Card>();
	
	
	public Hand(Card c1, Card c2){
		
		totalValue = 0;
		addCard(c1);
		addCard(c2);
		
	}
	
	public int getTotalValue() {
		return totalValue;
	}


	private void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	
	public void addCard(Card c){
		
		int prevValue = getTotalValue();
		hand.add(c);
		setTotalValue(prevValue + c.getValue());
		
	}
}
