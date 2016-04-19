package classes;

import java.util.ArrayList;
import java.util.Collections;

public class Shoe {

	int nrDecks;
	private int nrDecksLeft;
	private int nrShuffles;
	private ArrayList<Card> shoe = new ArrayList<Card>();
	private int nrCardsTaken;
	
	
	public Shoe(int nrDecks, ArrayList<Card> cardsinit) {
		
		this.nrDecks = nrDecks;
		this.nrDecksLeft = nrDecks;		
		this.nrCardsTaken = 0;
		
		
		//problema de misturar os decks enquanto que aqui estamos
		//a ordena-los todos
		for(int i = 0; i < nrDecks * cardsinit.size(); i++){
			shoe.add(cardsinit.get(i));
		}		
	}
	
	public int getNrDecksLeft() {
		return nrDecksLeft;
	}

	public int getNrShuffles() {
		return nrShuffles;
	}
	
	public int getNrCardsTaken() {
		return nrCardsTaken;
	}

	
	public void addCard(Card c){
		shoe.add(c);
	}
	
	public void Shuffle(){
		Collections.shuffle(shoe);
	}

	public Card getCard() {
		
		nrDecks = (nrCardsTaken) / 52;
		Card c = shoe.get(nrCardsTaken++);
		shoe.add(c);
		return c;
	}
	
}
