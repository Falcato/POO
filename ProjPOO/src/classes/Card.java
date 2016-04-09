package classes;

public class Card {
	
	private int value;
	private String naipe;
	private String figure;
	boolean turned;
	
	public Card(String figure, String naipe){
		this.figure = figure;
		this.naipe = naipe;
		turned = false;
		
		//Implementar value da carta
		
	}

	public int getValue() {
		return value;
	}

	public String getNaipe() {
		return naipe;
	}

	public String getFigure() {
		return figure;
	}
	
}
