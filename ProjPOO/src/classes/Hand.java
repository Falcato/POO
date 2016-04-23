package classes;

import java.util.ArrayList;

public class Hand {
	
	private int totalValue;
	private int nrCards;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	
	public Hand(Card c1, Card c2, boolean visib){
		
		nrCards = 2;
		totalValue = 0;
		
		if(visib == false) c2.setTurned(false);
			
		addCard(c1);
		addCard(c2);
		
	}
	
	public int getNrCards() {
		return nrCards;
	}



	@Override
	public String toString() {
		
		String handstr = "Hand [totalValue=" + totalValue + ", hand=";
		
		if(hand.get(1).isTurned() == false){
			handstr = "Hand [";
		}
		
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
		int aceCnt = 0;
		
		if(totalValue == 21){
			System.out.println("BlackJack!!");
		}else if(totalValue > 21){
			
			for(int i = 0; i < hand.size(); i++){
				if(hand.get(i).getValue() == 11){
					hand.get(i).setValue("1");
					aceCnt = 1;
					break;
				}
			}
			if(aceCnt == 0)	System.out.println("Bust!!");
			else this.totalValue = totalValue - 10;
		}
	}
	
	public void addCard(Card c){
		
		int prevValue = getTotalValue();
		hand.add(c);
		setTotalValue(prevValue + c.getValue());
		nrCards++;
		
	}

	public void turnCard() {
		if(hand.get(1).isTurned() == false) hand.get(1).setTurned(true);		
	}
}
