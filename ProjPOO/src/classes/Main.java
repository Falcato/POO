package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Player p1 = new Player(100);
		Card crei = new Card("rei", "copas");
		Card c5 = new Card("5", "copas");
		Card c6 = new Card("6", "copas");
		Card c7 = new Card("7", "copas");
		Card c8 = new Card("8", "copas");
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(crei);
		cards.add(c8);
		cards.add(c7);
		cards.add(c6);
		cards.add(c5);
		
		Shoe shoe = new Shoe(1, cards);
		
		shoe.Shuffle();
		
		p1.shoe = shoe;
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String command;
		
		int dealVerify = 0;
		
		while(true){
			
			System.out.println("Introduza uma acção");
			command = (scanner.next());
			
			switch(command) {
				case "d":
					dealVerify = 1;
					p1.Deal();
					break;
				case "h":
					if(dealVerify == 1){
						p1.Hit();
						break;
					}else{
						System.out.println("No deal yet!");
						break;
					}
				case "s":
					if(dealVerify == 1){
						p1.Stand();
						break;
					}else{
						System.out.println("No deal yet!");
						break;
					}
				case "g":  
					p1.getHand();break;
				case "q":  
					System.exit(1);break;
			}
		}
	}

}
