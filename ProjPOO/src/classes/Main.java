package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static int checkArgs(String[] args){
		
		if(!(args[0].equals("-i")) && !(args[0].equals("-s")) && !(args[0].equals("-d"))){
			System.out.println("Invalid first argument!!");
			return 0;
		}
		if(Integer.parseInt(args[1]) < 1){
			System.out.println("Invalid minimum bet!!");
			return 0;
		}
		if(Integer.parseInt(args[2]) > 20 * Integer.parseInt(args[1]) || Integer.parseInt(args[2]) < 10 * Integer.parseInt(args[1])){
			System.out.println("Invalid max bet!!");
			return 0;
		}
		if(Integer.parseInt(args[3]) < 50 * Integer.parseInt(args[1])){
			System.out.println("Invalid initial balance!!");
			return 0;
		}
		if(Integer.parseInt(args[4]) < 4 || Integer.parseInt(args[4]) > 8){
			System.out.println("Invalid shoe size!!");
			return 0;
		}
		if(Integer.parseInt(args[5]) < 10 || Integer.parseInt(args[5]) > 100){
			System.out.println("Invalid shuffle limit!!");
			return 0;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		
		if(checkArgs(args) == 0) System.exit(0);
		
		Dealer d1 = new Dealer();
		Player p1 = new Player();
		ArrayList<Card> cards = new ArrayList<Card>();
				
		d1.addPlayer(p1);
		p1.setMoney(Integer.parseInt(args[3]));
		
		//DEBUG MODE
		if(args[0].equals("-d")){
			
			System.out.println("Entering Debug Mode");
		
			/***********************************LER DO FICHEIRO***************************************/
			
			BufferedReader br = null;
			String line = null;
			try {
				//MUDAR O NUMERO DO ARGUMENTO
				br = new BufferedReader(new FileReader(args[6]));
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
			
			for (int i=0; i<splited.length; i++){
				Card card = new Card(splited[i]);
				cards.add(card);
	        
			}
			//Shoe shoe = new Shoe(Integer.parseInt(args[4]), cards, args[0], Integer.parseInt(args[5]));
			//FALTA LER OS COMANDOS DO CMD-FILE E EXECUTA-LOS
		}
		
		Shoe shoe = new Shoe(Integer.parseInt(args[4]), cards, args[0], Integer.parseInt(args[5]));
		
		//DEBUG MODE
		//Shoe shoe = new Shoe(1, cards, args[0], Integer.parseInt(args[5]));
		
		shoe.Shuffle();
		
		System.out.println("Shuffling Shoe...");
		
		d1.setShoe(shoe);
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String command = null;
	
		//VARIAVEIS CONTROLO
		int dealVerify = 0;
		int betVerify = 0;
		int playVerify = 0;
		
		//VARIAVEIS APOSTA
		int bet = 0;
		int minBet = Integer.parseInt(args[1]);
		int maxBet = Integer.parseInt(args[2]);
		
		//VARIAVEIS ESTATISTICAS
		int cntPush = 0;
		int cntWin = 0;
		int cntLose = 0;
		int dealerBJ = 0;
		int playerBJ = 0;
		
		while(true){
			
			command = scanner.nextLine();
			
			if(command.equals("st")){
				System.out.println("BJ P/D    " + playerBJ + "/" + dealerBJ);
				System.out.println("Win       " + cntWin);
				System.out.println("Lose      " + cntLose);
				System.out.println("Push      " + cntPush);
				System.out.println("Balance   " + p1.getMoney() + "(" +
						(float)p1.getMoney() / (float)Integer.parseInt(args[3]) + "%)");
			}
			else if(command.length() > 2){
				String betz = command.substring(2);
				bet = Integer.parseInt(betz);
				if(p1.Bet(bet, minBet, maxBet) == 1){
					betVerify = 1;
				}else System.out.println("Invalid Bet!!");
			}else if(command.equals("b")){
				if(bet == 0) bet = minBet;
				if(p1.Bet(bet, minBet, maxBet) == 1){
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
			}else if(command.equals("q")){
				System.out.println("Quitting...");
				System.exit(0);
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
						case "q":
							System.out.println("Quitting...");System.exit(0);
						case "st":
							System.out.println("BJ P/D    " + playerBJ + "/" + dealerBJ);
							System.out.println("Win       " + cntWin);
							System.out.println("Lose      " + cntLose);
							System.out.println("Push      " + cntPush);
							System.out.println("Balance   " + p1.getMoney() + "(" +
									(float)p1.getMoney() / (float)Integer.parseInt(args[3]) + "%)");
					}
					
				}
				
				//DEALER
				if(playVerify == 1){
					playVerify = 0;
					
					if(p1.hand.getTotalValue() < 22){
						
						while(true){
							
							if(d1.hand.getTotalValue() < 16){
								System.out.println("Hit dealer");
								d1.getCard();
							}else{
								System.out.println("Stand dealer");
								d1.hand.turnCard();
								d1.getHand();
								break;
							}
						}
								
						if(d1.hand.getTotalValue() > p1.hand.getTotalValue() && d1.hand.getTotalValue() < 22){
							System.out.println("Lost!!");
							cntLose++;
							if(d1.hand.getTotalValue() == 21) dealerBJ++;
						}
						else if(d1.hand.getTotalValue() == p1.hand.getTotalValue()){
							if(d1.hand.getTotalValue() == 21){
								dealerBJ++;
								playerBJ++;
							}
							if(d1.hand.getNrCards() > p1.hand.getNrCards() && p1.hand.getNrCards() == 2){
								System.out.println("Won!!");
								cntWin++;
								d1.giveMoney(p1, 2 * bet);
							}else{
								System.out.println("Push!!");
								cntPush++;
								d1.giveMoney(p1, bet);
							}
						}else if(d1.hand.getTotalValue() < 22){
							System.out.println("Won!!");
							cntWin++;
							d1.giveMoney(p1, 2 * bet);
							if(p1.hand.getTotalValue() == 21) playerBJ++;
						}
						else if(d1.hand.getTotalValue() > 21){
							System.out.println("Won!!");
							cntWin++;
							d1.giveMoney(p1, 2 * bet);
							if(p1.hand.getTotalValue() == 21) playerBJ++;
						}
						
					}else{
						d1.hand.turnCard();
						d1.getHand();
						System.out.println("Lost!!");
						cntLose++;
						if(d1.hand.getTotalValue() == 21) dealerBJ++;
					}
				}
			}
		}
	}
}
