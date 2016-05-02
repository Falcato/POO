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
	private int runningCnt;
	
	private int aceFive;
	
	public Shoe(int nrDecks, ArrayList<Card> cardsinit, String mode, int shuffles) {
		
		this.nrDecks = nrDecks;
		this.nrDecksLeft = nrDecks;		
		this.nrCardsTaken = 0;
		this.nrCards = 0;
		this.shuffles = shuffles;
		runningCnt = 0;
		aceFive = 0;
		
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
	
	int getNrDecksLeft() {
		return nrDecksLeft;
	}

	int getNrCardsTaken() {
		return nrCardsTaken;
	}

	int getNrCards() {
		return nrCards;
	}
	
	int getRunningCnt(){
		return runningCnt;
	}
	
	int getAceFive(){
		return aceFive;
	}
	
	void addCard(Card c){
		shoe.add(c);
	}
	
	void Shuffle(){
		Collections.shuffle(shoe);
	}
	
	int runningCntValue(Card c){
		if(c.getValue() < 7) return 1;
		else if(c.getValue() < 10) return 0;
		else return -1;
	}
	
	int aceFiveValue(Card c){
		if(c.getValue() == 5) return 1;
		else if(c.getValue() == 11) return -1;
		else return 0;
	}
	
	Card getCard() {
		
		nrDecks = (nrCardsTaken) / 52;
		nrDecksLeft -= nrDecks;
		Card c = shoe.get(nrCardsTaken++);
		if(c.getValue() == 1) c.setValue("A");
		
		runningCnt += runningCntValue(c);
		aceFive += aceFiveValue(c);
		
		shoe.add(c);
		if(nrCardsTaken == nrCards * shuffles / 100){
			aceFive = 0;
			runningCnt = 0;
			Shuffle();
			System.out.println("Shuffling Shoe...");
		}
		return c;
	}
}
