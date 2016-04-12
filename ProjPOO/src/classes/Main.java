package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Player p1 = new Player(100);
		Dealer d1 = new Dealer();
		
		Card c1 = new Card("rei", "copas");
		Card c2 = new Card("5", "copas");
		Card c3 = new Card("6", "copas");
		Card c4 = new Card("7", "copas");
		Card c5 = new Card("8", "copas");
		Card c6 = new Card("6", "copas");
		Card c7 = new Card("7", "copas");
		Card c8 = new Card("8", "copas");
		Card c9 = new Card("6", "copas");
		Card c10 = new Card("7", "copas");
		Card c11 = new Card("8", "copas");
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		cards.add(c5);
		cards.add(c6);
		cards.add(c7);
		cards.add(c8);
		cards.add(c9);
		cards.add(c10);
		cards.add(c11);
		
		Shoe shoe = new Shoe(1, cards);
		
		shoe.Shuffle();
		
		p1.shoe = shoe;
		d1.shoe = shoe;
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String command = null;
	
		int dealVerify = 0;
		int betVerify = 0;
		int playVerify = 0;
		
		while(true){
			
			
			
			System.out.println("New game!!");
			command = scanner.nextLine();
			
			if(command.length() > 2){
				String betz = command.substring(2);
				int bet = Integer.parseInt(betz);
				if(p1.Bet(bet) == 1){
					betVerify = 1;
				}
			}else if(command.equals("b")){
				//fazer ultima aposta ou min
			}else if(command.equals("d")){
				if(betVerify == 1){
					dealVerify = 1;
					betVerify = 0;
					d1.Deal();
					p1.Deal();
				}else System.out.println("No bets yet!!");
			}else if(command.equals("$")){
				System.out.println("Current Money: " + p1.getMoney());
			}
			
			
			if(dealVerify == 1){
			
				whiletrue:
				
				while(true){
					
					dealVerify = 0;
					
					if(p1.hand.getTotalValue() > 21) break;
					
					System.out.println("Next move?");
					command = scanner.nextLine();
											
					//PLAYER
					switch(command) {
						case "h":
							p1.Hit();
							playVerify = 1;
							if(p1.hand.getTotalValue() == -1) break whiletrue;
							else break;
						case "s":
							p1.Stand();
							playVerify = 1;
							break whiletrue;
						case "g":  
							p1.getHand();break;
						case "$":
							System.out.println("Current Money: " + p1.getMoney());break;
					}
					
				}
				
				//DEALER
				if(playVerify == 1){
					playVerify = 0;
					
					if(p1.hand.getTotalValue() != -1){
							
						if(d1.hand.getTotalValue() < 17){
							System.out.println("Hit dealer");
							d1.Hit();
						}else{
							System.out.println("Dealer hand bigger than 16");
							d1.Stand();
						}
								
						if(d1.hand.getTotalValue() > p1.hand.getTotalValue()) System.out.println("Lost!!");
						else  System.out.println("Won!!");
						
					}else System.out.println("Lost!!");
				}
			}
		}
	}
}
