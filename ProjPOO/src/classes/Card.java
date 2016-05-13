package classes;
/**
 *
 * Implements a card in a blackjack game. It is the base
 * of the whole game. Creates and identifies different cards.
 */
public class Card {
	
	private int value;
	private String naipe;
	private String figure;
	boolean turned;
	
	/**
	 * Constructor for class Card, receives a card
	 * and examines it, indentifying it.
	 * 
	 * @param card card to be identified
	 */
	public Card(String card){
		
		if(card.substring(0, 2).equals("10")){
			this.figure = card.substring(0,2);
			this.naipe = String.valueOf(card.charAt(2));
			turned = true;
			setValue(figure);
		}else{
			this.figure = String.valueOf(card.charAt(0));
			this.naipe = String.valueOf(card.charAt(1));
			turned = true;
			setValue(figure);
		}
	}

	/**
	 * Receives the figure of the card and identifies it, setting the
	 * figure attribute accordingly.
	 * 
	 * @param figure2 the figure of the card, e.g, "2", "3", "A", etc.
	 */
	void setValue(String figure2) {
		
		switch(figure2) {
			case "1":  this.value = 1;break;
			case "2":  this.value = 2;break;
			case "3":  this.value = 3;break;
			case "4":  this.value = 4;break;
			case "5":  this.value = 5;break;
			case "6":  this.value = 6;break;
			case "7":  this.value = 7;break;
			case "8":  this.value = 8;break;
			case "9":  this.value = 9;break;
			case "A":  this.value = 11;break;
			default:  this.value = 10;
		}
		
	}

	@Override
	public String toString() {
		if(turned == false) return "X";
		return "" + figure + "" + naipe + "";
	}

	/**
	 * Returns the value of the card.
	 * @return the value of the card
	 */
	int getValue() {
		return value;
	}

	/**
	 * Returns the naipe of the card.
	 * @return the naipe of the card
	 */
	String getNaipe() {
		return naipe;
	}

	/**
	 * Returns the figure of the card.
	 * @return the figure of the card
	 */
	String getFigure() {
		return figure;
	}

	/**
	 * Returns the indication if the card is turned
	 * or not.
	 * @return true if turned up
	 * 			false otherwise.
	 */
	boolean isTurned() {
		return turned;
	}

	/**
	 * Turns the card up or down.
	 * @param turned true if turned up
	 * 				 false otherwise.
	 */
	void setTurned(boolean turned) {
		this.turned = turned;
	}
	
}
