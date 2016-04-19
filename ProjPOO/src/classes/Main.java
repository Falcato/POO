package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Dealer d1 = new Dealer();
		Player p1 = new Player();
				
		d1.addPlayer(p1);
		p1.setMoney(100);
		
		/***********************************LER DO FICHEIRO***************************************/
		
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			line = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String[] splited = line.split("\\s+");
		
		/****************************************************************************************/
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for (int i=0; i<splited.length; i++)
        {
			Card card = new Card(splited[i]);
			cards.add(card);
        }
						
		Shoe shoe = new Shoe(1, cards);
		
		shoe.Shuffle();
		
		System.out.println("Shuffling Shoe...");
		
		d1.setShoe(shoe);
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String command = null;
	
		int dealVerify = 0;
		int betVerify = 0;
		int playVerify = 0;
		int bet = 0;
		
		while(true){
			
			
			
			System.out.println("New game!!");
			command = scanner.nextLine();
			
			if(command.length() > 2){
				String betz = command.substring(2);
				bet = Integer.parseInt(betz);
				if(p1.Bet(bet) == 1){
					betVerify = 1;
				}else System.out.println("Invalid Bet!!");
			}else if(command.equals("b")){
				if(p1.Bet(bet) == 1){
					betVerify = 1;
				}else System.out.println("Invalid Bet!!");
			}else if(command.equals("d")){
				if(betVerify == 1){
					dealVerify = 1;
					betVerify = 0;
					d1.Deal();
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
							d1.giveCard(p1);
							playVerify = 1;
							if(p1.hand.getTotalValue() == -1) break whiletrue;
							else break;
						case "s":
							playVerify = 1;
							break whiletrue;
						case "g":  
							p1.getHand();
							break;
						case "$":
							System.out.println("Current Money: " + p1.getMoney());break;
					}
					
				}
				
				//DEALER
				if(playVerify == 1){
					playVerify = 0;
					
					if(p1.hand.getTotalValue() < 22){
							
						if(d1.hand.getTotalValue() < 17){
							System.out.println("Hit dealer");
							d1.getCard();
						}else{
							System.out.println("Dealer hand bigger than 16");
							break;
						}
								
						if(d1.hand.getTotalValue() > p1.hand.getTotalValue() && d1.hand.getTotalValue() < 22) System.out.println("Lost!!");
						else if(d1.hand.getTotalValue() < 22)  System.out.println("Won!!");
						else if(d1.hand.getTotalValue() > 21)  System.out.println("Won!!");
						
					}else System.out.println("Lost!!");
				}
			}
		}
	}
}
