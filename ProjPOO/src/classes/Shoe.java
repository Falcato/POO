package classes;

import java.util.ArrayList;
import java.util.Collections;

public class Shoe {

	int nrDecks;
	private int nrDecksLeft;
	private int shuffles;
	private ArrayList<Card> shoe = new ArrayList<Card>();
	private int nrCardsTaken;
	private int nrCards;
	
	
	public Shoe(int nrDecks, ArrayList<Card> cardsinit, String mode, int shuffles) {
		
		this.nrDecks = nrDecks;
		this.nrDecksLeft = nrDecks;		
		this.nrCardsTaken = 0;
		this.nrCards = 0;
		this.shuffles = shuffles;
		
		if(mode.equals("-d")){
			for(int i = 0; i < nrDecks * cardsinit.size(); i++){
				shoe.add(cardsinit.get(i));
				nrCards++;
			}
		}else if(mode.equals("-i")){
			
			System.out.println("Entering Interactive Mode");
			
			for(int i = 2; i < 11; i++){
				for(int j = 0; j < nrDecks; j++){
					Card cards = new Card(i + "S");
					shoe.add(cards);
					Card cardd = new Card(i + "D");
					shoe.add(cardd);
					Card cardc = new Card(i + "C");
					shoe.add(cardc);
					Card cardh = new Card(i + "H");
					shoe.add(cardh);
					nrCards += 4;
				}
			}
			for(int i = 0; i < nrDecks; i++){
				Card cards = new Card("J" + "S");
				shoe.add(cards);
				Card cardd = new Card("J" + "D");
				shoe.add(cardd);
				Card cardc = new Card("J" + "C");
				shoe.add(cardc);
				Card cardh = new Card("J" + "H");
				shoe.add(cardh);
				nrCards += 4;
			}
			for(int i = 0; i < nrDecks; i++){
				Card cards = new Card("Q" + "S");
				shoe.add(cards);
				Card cardd = new Card("Q" + "D");
				shoe.add(cardd);
				Card cardc = new Card("Q" + "C");
				shoe.add(cardc);
				Card cardh = new Card("Q" + "H");
				shoe.add(cardh);
				nrCards += 4;
			}
			for(int i = 0; i < nrDecks; i++){
				Card cards = new Card("K" + "S");
				shoe.add(cards);
				Card cardd = new Card("K" + "D");
				shoe.add(cardd);
				Card cardc = new Card("K" + "C");
				shoe.add(cardc);
				Card cardh = new Card("K" + "H");
				shoe.add(cardh);
				nrCards += 4;
			}
			for(int i = 0; i < nrDecks; i++){
				Card cards = new Card("A" + "S");
				shoe.add(cards);
				Card cardd = new Card("A" + "D");
				shoe.add(cardd);
				Card cardc = new Card("A" + "C");
				shoe.add(cardc);
				Card cardh = new Card("A" + "H");
				shoe.add(cardh);
				nrCards += 4;
			}
		}
	}
	
	public int getNrDecksLeft() {
		return nrDecksLeft;
	}

	public int getNrCardsTaken() {
		return nrCardsTaken;
	}

	public int getNrCards() {
		return nrCards;
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
		if(c.getValue() == 1) c.setValue("A");
		
		if(nrCardsTaken == nrCards * shuffles / 100){
			Shuffle();
			System.out.println("Shuffling Shoe...");
		}
		
		return c;
		
	}
	
}
