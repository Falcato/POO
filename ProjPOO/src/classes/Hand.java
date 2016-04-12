package classes;

import java.util.ArrayList;

public class Hand {
	
	private int totalValue;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	
	public Hand(Card c1, Card c2){
		
		totalValue = 0;
		addCard(c1);
		addCard(c2);
		
	}
	
	@Override
	public String toString() {
		
		String handstr = "Hand [totalValue=" + totalValue + ", hand=";
		
		for (int i = 0 ; i < hand.size(); i++){
			
			handstr += hand.get(i).toString();
			if(i != hand.size() - 1) handstr += ", ";
		}
		handstr += "]";
		
		return handstr;
	}

	public int getTotalValue() {
		return totalValue;
	}

	private void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
		if(totalValue == 21){
			System.out.println("BlackJack!!");
		}else if(totalValue > 21){
			System.out.println("Bust!!");
			this.totalValue = -1;
		}
	}
	
	public void addCard(Card c){
		
		int prevValue = getTotalValue();
		hand.add(c);
		setTotalValue(prevValue + c.getValue());
		
	}
}
