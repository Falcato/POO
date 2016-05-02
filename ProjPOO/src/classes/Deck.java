package classes;

import java.util.ArrayList;

public class Deck {

	private int nrCardsLeft;
	ArrayList<Card> cards = new ArrayList<Card>();
	
	public Deck(){
		
		nrCardsLeft = 52;
			
	}

	public int getNrCardsLeft() {
		return nrCardsLeft;
	}
	
	public void addCard(Card c){
		cards.add(c);
	}
	
	public void Shuffle(){
		//implementar shuffle
	}

	public Card getCard() {
		return cards.get(nrCardsLeft--);
	}
	
	
}
