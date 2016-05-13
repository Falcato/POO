package classes;

import java.util.ArrayList;
/**
 * 
 * Implements a hand in blackjack.
 * Hands contain cards and the dealer and each player
 * have a hand. Each hand has a value based on the cards
 * that are associated to it.
 */
public class Hand {
	
	private int totalValue;
	private int nrCards;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	
	/**
	 * Creates a new Hand, receives two cards and if the
	 * second card should be turned up or down (if it's the
	 * dealer's first hand).
	 * 
	 * @param c1 1st card to be added
	 * @param c2 2nd card to be added
	 * @param visib true if both cards are turned up
	 * 				false if the 2nd should be turned down
	 */
	public Hand(Card c1, Card c2, boolean visib){
		
		nrCards = 0;
		totalValue = 0;
		
		if(visib == false)
			c2.setTurned(false);
			
		addCard(c1);
		addCard(c2);
		
	}
	
	/**
	 * Return the number of cards in the hand.
	 * @return number of cards
	 */
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

	/**
	 * Returns the value of the hand as it is.
	 * @return total value
	 */
	int getTotalValue() {
		return totalValue;
	}

	/**
	 * Sets the total value of the hand, havin in
	 * account the possibility of the ace value being
	 * 1 or 11.
	 * 
	 * @param totalValue new value of the hand
	 */
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
			if(aceCnt != 0)
				this.totalValue = totalValue - 10;
		}
	}
	
	/**
	 * Adds a card to the hand, updates the
	 * total value of the hand and increments
	 * the number of cards in the hand.
	 * 
	 * @param c Card to be added to the hand
	 */
	void addCard(Card c){
		
		int prevValue = getTotalValue();
		hand.add(c);
		setTotalValue(prevValue + c.getValue());
		nrCards++;		
	}
	
	/**
	 * Removes the 2nd card of the hand, updates
	 * the total value of the hand and the 
	 * number of cards.
	 * This method is only used when a split is
	 * detected.
	 */
	void removeCard(){
		int prevValue = getTotalValue();
		setTotalValue(prevValue - hand.get(1).getValue());
		hand.remove(1);
		nrCards--;
	}

	/**
	 * Turns up the 2nd card of the hand.
	 * This method is used if a card is turned down
	 * due to it being assigned to the dealer's 2nd
	 * card and it's not supposed to be turned down
	 * anymore.
	 */
	void turnCard() {
		if(hand.get(1).isTurned() == false)
			hand.get(1).setTurned(true);		
	}
	
	/**
	 * Checks if an insurance is possible to be made.
	 * @return 1 if is possible to insure
	 * 		   0 otherwise
	 */
	int checkInsurance(){
		if(hand.get(0).getFigure().equals("A")) return 1;
		else return 0;
	}
	
	/**
	 * Gets the card of a certain position in the hand.
	 * @param index position of the card to be returned
	 * @return the card in the chosen position
	 */
	Card getCard(int index){
		return hand.get(index);
	}
}
