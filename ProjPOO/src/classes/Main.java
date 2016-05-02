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
		
		int debugMode = 0;
		String[] splitedcmd = null;
		
		if(args[0].equals("-d")) debugMode = 1;
		
		if(debugMode == 0 && checkArgs(args) == 0) System.exit(0);
		
		Dealer d1 = new Dealer();
		Player p1 = new Player(d1);
		ArrayList<Card> cards = new ArrayList<Card>();
				
		d1.addPlayer(p1);
		p1.setMoney(Integer.parseInt(args[3]));
		
		Shoe shoe = null;
		
		//DEBUG MODE
		if(debugMode == 1){
			
			System.out.println("Entering Debug Mode");
			debugMode = 1;
		
			/***********************************LER CARTAS DO FICHEIRO***************************/
			
			BufferedReader br = null;
			String line = null;
			try {
				br = new BufferedReader(new FileReader(args[4]));
			} catch (FileNotFoundException e2) {
				System.out.println("Error opening file.");
				e2.printStackTrace();
			}
			try {
				line = br.readLine();
			} catch (IOException e1) {
				System.out.println("Error reading file.");
				e1.printStackTrace();
			} finally {
			    try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing file.");
					e.printStackTrace();
				}
			}
			
			String[] splitedcard = line.split("\\s+");
			
			/*************************************************************************************/
			
			for (int i=0; i < splitedcard.length; i++){
				Card card = new Card(splitedcard[i]);
				cards.add(card);
	        }
			
			shoe = new Shoe(1, cards, args[0], 0);

			/***********************************LER CMDS DO FICHEIRO*****************************/
			
			try {
				br = new BufferedReader(new FileReader(args[5]));
			} catch (FileNotFoundException e2) {
				System.out.println("Error opening file.");
				e2.printStackTrace();
			}
			try {
				line = br.readLine();
			} catch (IOException e1) {
				System.out.println("Error reading file.");
				e1.printStackTrace();
			} finally {
			    try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing file.");
					e.printStackTrace();
				}
			}
			
			splitedcmd = line.split("\\s+");
			
			/*******************************************************************************/
			
		}
		
		if(debugMode == 0){
			shoe = new Shoe(Integer.parseInt(args[4]), cards, args[0], Integer.parseInt(args[5]));
			shoe.Shuffle();
			System.out.println("Shuffling Shoe...");
		}
				
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
		
		int insurance = 0;
		int doubleDown = 0;
		
		//VARIAVEIS ESTATISTICAS
		int cntPush = 0;
		int cntWin = 0;
		int cntLose = 0;
		int dealerBJ = 0;
		int playerBJ = 0;
		
		//VARIAVEL DEBUG
		int cmd = 0;
		
		while(true){
			
			insurance = 0;
			doubleDown = 0;
			
			System.out.println("");
			if (debugMode == 1){
				if(cmd < splitedcmd.length){
					if(splitedcmd[cmd].equals("b") && cmd < splitedcmd.length - 1){
						if(Character.isDigit(splitedcmd[cmd+1].charAt(0))){
							command = splitedcmd[cmd] + " " + splitedcmd[cmd+1];
							System.out.println("-cmd " + command);
							cmd++;
						}else{
							command = splitedcmd[cmd];
							System.out.println("-cmd " + command);
						}
					}else{
						command = splitedcmd[cmd];
						System.out.println("-cmd " + command);
					}
				}else{
					System.out.println("No more commands... Exiting...");
					System.exit(0);
				}
		    }else command = scanner.nextLine();
			
						
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
					System.out.println("Player is betting " + bet);
				}else System.out.println("Invalid Bet!!");
			}else if(command.equals("b")){
				if(bet == 0) bet = minBet;
				if(p1.Bet(bet, minBet, maxBet) == 1){
					betVerify = 1;
					System.out.println("Player is betting " + bet);
				}else System.out.println("Invalid Bet!!");
			}else if(command.equals("d")){
				if(betVerify == 1){
					dealVerify = 1;
					betVerify = 0;
					d1.Deal();
				}else System.out.println("No bets yet!!");
			}else if(command.equals("$")){
				System.out.println("Current Balance: " + p1.getMoney());
			}else if(command.equals("q")){
				System.out.println("Quitting...");
				System.exit(0);
			}else if(command.equals("ad")){
				if(shoe.getAceFive() > 1) System.out.println("Ace Five: bet " + bet * 2);
				else System.out.println("Ace Five:	bet " + minBet);
			}else System.out.println(command + ": Illegal command!");
			
			
			if(dealVerify == 1){
				
				for(int j = 0; j < d1.CntPlayers; j++){
					
					for(int i = 0; i < d1.players[j].nrHands + 1; i++){
				
						if(d1.players[j].nrHands > 0){
							System.out.println("");
							System.out.println("Playing hand number " + i + "...");
							System.out.println("");
							d1.players[j].getHand(i);
						}
						
						whiletrue:
						
						while(true){
							
							cmd++;
							dealVerify = 0;
							
							if(d1.players[j].hands[i].getTotalValue() > 21) break;
							
							System.out.println("");
							if (debugMode == 1){
								if(cmd < splitedcmd.length){
									command = splitedcmd[cmd];
									if(!(splitedcmd[cmd].equals("d"))) System.out.println("-cmd " + command);
								}else{
									System.out.println("No more commands... Exiting...");
									System.exit(0);
								}
						    }else command = scanner.nextLine();
													
							//PLAYER
							switch(command) {
								case "h":
									System.out.println("Player hits");
									
									//d1.players[j].hit(i);
									
									d1.giveCard(d1.players[j], i);
									playVerify = 1;
									if(doubleDown == 1) break whiletrue;
									else break;
								case "s":
									playVerify = 1;
									System.out.println("Player stands");
									
									//d1.players[j].stand();
									
									break whiletrue;
								case "$":
									System.out.println("Current Balance: " + d1.players[j].getMoney());break;
								case "q":
									System.out.println("Quitting...");System.exit(0);
								case "st":
									System.out.println("BJ P/D    " + playerBJ + "/" + dealerBJ);
									System.out.println("Win       " + cntWin);
									System.out.println("Lose      " + cntLose);
									System.out.println("Push      " + cntPush);
									System.out.println("Balance   " + d1.players[j].getMoney() + "(" +
											(float)d1.players[j].getMoney() / (float)Integer.parseInt(args[3]) + "%)");
									break;
								case "i":
									if(playVerify == 0 && d1.hand.checkInsurance() == 1){
										System.out.println("Player is insuring...");
										
										//d1.players[j].insure();
										
										d1.players[j].Bet(bet, minBet, maxBet);
										insurance = 1;
										break;
									}else System.out.println("Cannot insure...");
									break;
									
								case "u":
									if(playVerify == 0){
										System.out.println("Player is surrendering...");
										
										//d1.players[j].surrender();
										
										float ret = (float)bet / (float)2;
										d1.giveMoney(d1.players[j], ret);
										cntLose++;
										break whiletrue;
									}else System.out.println("Cannot surrender...");
									break;
								case "2":
									if(playVerify == 0 && (d1.players[j].hands[i].getTotalValue() == 9 || d1.players[j].hands[i].getTotalValue() == 10 || d1.players[j].hands[i].getTotalValue() == 11)){
										System.out.println("Player is doubling...");
										
										//d1.players[j].doubleDown(minBet, maxBet);
										
										d1.players[j].Bet(bet, minBet, maxBet);
										bet = 2 * bet;
										doubleDown = 1;
										break;
									}else System.out.println("Cannot double...");
									break;
								case "p":
									if(d1.players[j].hands[i].getCard(0).getValue() == d1.players[j].hands[i].getCard(1).getValue()){
										System.out.println("Player is splitting...");
										if(d1.players[j].Bet(bet, minBet, maxBet) != 1) System.out.println("Not enough balance to split...");
										d1.players[j].split(i);
										break;
									}else System.out.println("Cannot split...");
									break;
								case "ad":
									d1.players[j].advice(i);
									break;
								default:
									System.out.println(command + ": Illegal command!!");
							}
						}
					}
					cmd++;
				}
				
				//DEALER
				if(playVerify == 1){
					playVerify = 0;
					
					for(int j = 0; j < d1.CntPlayers; j++){
						
						for(int i = 0; i < d1.players[j].nrHands + 1; i++){
													
							if(d1.players[j].hands[i].getTotalValue() < 22){
								
								while(true){
									
									if(d1.hand.getTotalValue() < 17){
										System.out.println("Dealer hits");
										d1.getCard();
									}else{
										System.out.println("Dealer stands");
										d1.hand.turnCard();
										d1.getHand();
										break;
									}
								}
										
								if(d1.hand.getTotalValue() > d1.players[j].hands[i].getTotalValue() && d1.hand.getTotalValue() < 22){
									System.out.println("Player[" + i + "] Lost!!");
									cntLose++;
									if(d1.hand.getTotalValue() == 21 && d1.hand.getNrCards() == 2){
										System.out.println("Blackjack!!");
										dealerBJ++;
										if(insurance == 1){
											System.out.println("Insurance Money!!");
											d1.giveMoney(d1.players[j], bet);
										}
									}
								}
								else if(d1.hand.getTotalValue() == d1.players[j].hands[i].getTotalValue()){
									if(d1.hand.getTotalValue() == 21){
										if(d1.hand.getNrCards() == 2){
											System.out.println("Blackjack!!");
											dealerBJ++;
											if(insurance == 1){
												System.out.println("Insurance Money!!");
												d1.giveMoney(d1.players[j], bet);
											}
										}
										if(d1.players[j].hands[i].getNrCards() == 2){
											System.out.println("Blackjack!!");
											playerBJ++;
										}
									}
									if(d1.players[j].hands[i].getNrCards() == 2 && d1.hand.getNrCards() > 2 && d1.players[j].hands[i].getTotalValue() == 21){
										System.out.println("Player[" + i + "] Won!!");
										cntWin++;
										float ret = (float) (2.5 * bet);
										d1.giveMoney(d1.players[j], ret);
									}else if(d1.players[j].hands[i].getNrCards() > 2 && d1.hand.getNrCards() == 2 && d1.players[j].hands[i].getTotalValue() == 21){
										d1.getHand();
										System.out.println("Player[" + i + "] Lost!!");
										cntLose++;
									}else{
										System.out.println("Player[" + i + "] Push!!");
										cntPush++;
										d1.giveMoney(d1.players[j], bet);
									}
								}else if(d1.hand.getTotalValue() < 22){
									System.out.println("Player[" + i + "] Won!!");
									cntWin++;
									if(d1.players[j].hands[i].getTotalValue() == 21 && d1.players[j].hands[i].getNrCards() == 2){
										playerBJ++;
										System.out.println("Blackjack!!");
										float ret = (float) (2.5 * bet);
										d1.giveMoney(d1.players[j], ret);
									}else d1.giveMoney(d1.players[j], 2 * bet);
								}
								else if(d1.hand.getTotalValue() > 21){
									System.out.println("Player[" + i + "] Won!!");
									cntWin++;
									if(d1.players[j].hands[i].getTotalValue() == 21 && d1.players[j].hands[i].getNrCards() == 2){
										playerBJ++;
										System.out.println("Blackjack!!");
										float ret = (float) (2.5 * bet);
										d1.giveMoney(d1.players[j], ret);
									}else d1.giveMoney(d1.players[j], 2 * bet);
								}
							}else{
								d1.getHand();
								System.out.println("Player[" + i + "] Lost!!");
								cntLose++;
								if(d1.hand.getTotalValue() == 21 && d1.hand.getNrCards() == 2){
									dealerBJ++;
									System.out.println("Blackjack!!");
									if(insurance == 1){
										System.out.println("Insurance Money!!");
										d1.giveMoney(d1.players[j], bet);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

