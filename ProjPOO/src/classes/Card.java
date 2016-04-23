package classes;

public class Card {
	
	private int value;
	private String naipe;
	private String figure;
	boolean turned;
	
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

	public void setValue(String figure2) {
		
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

	public int getValue() {
		return value;
	}

	public String getNaipe() {
		return naipe;
	}

	public String getFigure() {
		return figure;
	}

	public boolean isTurned() {
		return turned;
	}

	public void setTurned(boolean turned) {
		this.turned = turned;
	}
	
}
