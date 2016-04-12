package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;

public class Shoe {

	int nrDecks;
	private int nrDecksLeft;
	private int nrShuffles;
	private ArrayList<Card> shoe = new ArrayList<Card>();
	private int nrCardsLeft;
	
	
	public Shoe(int nrDecks, ArrayList<Card> cardsinit) {
		
		this.nrDecks = nrDecks;
		this.nrDecksLeft = nrDecks;		
		this.nrCardsLeft = cardsinit.size();
		
		
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
	
	public int getNrCardsLeft() {
		return nrCardsLeft;
	}

	
	public void addCard(Card c){
		shoe.add(c);
	}
	
	public void Shuffle(){
		Collections.shuffle(shoe);
	}

	public Card getCard() {
		
		if(nrCardsLeft == 0){
			//IMPLEMENTAR THROW
			throw new EmptyStackException();
		}
		
		nrDecks = (nrCardsLeft - 1) / 52;
		return shoe.get(--nrCardsLeft);
	}
	
}
