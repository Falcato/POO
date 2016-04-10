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
		setValue(figure);
		
	}

	public void setValue(String figure) {
		
		switch(figure) {
			case "2":  this.value = 2;break;
			case "3":  this.value = 3;break;
			case "4":  this.value = 4;break;
			case "5":  this.value = 5;break;
			case "6":  this.value = 6;break;
			case "7":  this.value = 7;break;
			case "8":  this.value = 8;break;
			case "9":  this.value = 9;break;
			case "ace":  this.value = 11;break;
			default:  this.value = 10;
		}
		
	}

	@Override
	public String toString() {
		return "" + figure + " de " + naipe + "";
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
