package classes;

import java.util.ArrayList;

public class Hand {
	
	private int totalValue;
	private int nrCards;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	
	public Hand(Card c1, Card c2, boolean visib){
		
		nrCards = 0;
		totalValue = 0;
		
		if(visib == false) c2.setTurned(false);
			
		addCard(c1);
		addCard(c2);
		
	}
	
	int getNrCards() {
		return nrCards;
	}



	@Override
	public String toString() {
		
		String handstr = "[";
		
		
		
		for (int i = 0 ; i < hand.size(); i++){
			
			handstr += hand.get(i).toString();
			if(i != hand.size() - 1) handstr += ", ";
		}
		if(hand.get(1).isTurned() == false){
			handstr += "]";
		}else	handstr += "] (" + totalValue + ")";
		
		return handstr;
	}

	int getTotalValue() {
		return totalValue;
	}

	private void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
		int aceCnt = 0;
		
		if(totalValue > 21){
			
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
	
	void addCard(Card c){
		
		int prevValue = getTotalValue();
		hand.add(c);
		setTotalValue(prevValue + c.getValue());
		nrCards++;		
	}
	
	void removeCard(){
		int prevValue = getTotalValue();
		setTotalValue(prevValue - hand.get(1).getValue());
		hand.remove(1);
		nrCards--;
	}

	void turnCard() {
		if(hand.get(1).isTurned() == false) hand.get(1).setTurned(true);		
	}
	
	int checkInsurance(){
		if(hand.get(0).getFigure().equals("A")) return 1;
		else return 0;
	}
	
	Card getCard(int index){
		return hand.get(index);
	}
}
