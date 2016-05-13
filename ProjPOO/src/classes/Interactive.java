package classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * Implements one of the three possible game modes.
 * This is the actual blackjack game, where the player
 * is the user of the application and the moves are
 * based on the input commands of the user.
 */
public class Interactive extends Game{
	
	/**
	 * Constructor for the class Interactive.
	 * Receives the input arguments by the user.
	 * 
	 * @param args input arguments
	 */
	public Interactive(String[] args){
		this.args = args;
	}
	
	/**
	 * Checks if the input arguments received are in
	 * conformity with the project specifications.
	 * 
	 * @param args input arguments
	 * @return 0 if invalid arguments
	 * 		   1 otherwise
	 */
	public static int checkArgs(String[] args){
		
		if(args.length != 6){
			System.out.println("Invalid argument number!!");
			return 0;
		}
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
	
	@Override
	public void game() {
		
		if(checkArgs(args) == 0) System.exit(0);
		
		d1 = new Dealer();
		p1 = new Player(d1);
		ArrayList<Card> cards = new ArrayList<Card>();
				
		d1.addPlayer(p1);
		p1.setMoney(Integer.parseInt(args[3]));
		
		shoe = null;
		shoe = new Shoe(Integer.parseInt(args[4]), cards, args[0], Integer.parseInt(args[5]));
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
		
		//VARIAVEIS SIDE RULES
		int insurance = 0;
		int doubleDown = 0;
		int split = 0;
		int maxSplit = 0;
		int splitAces = 0;
		
		//VARIAVEIS ESTATISTICAS
		int cntPush = 0;
		int cntWin = 0;
		int cntLose = 0;
		int dealerBJ = 0;
		int playerBJ = 0;
		
		while(true){
			
			insurance = 0;
			doubleDown = 0;
			split = 0;
			splitAces = 0;
			maxSplit = 0;
			
			System.out.println("");
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
					d1.Deal(0);
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

							dealVerify = 0;
							
							if(d1.players[j].hands[i].getTotalValue() > 21) break;
							
							System.out.println("");
							command = scanner.nextLine();
													
							//PLAYER
							switch(command) {
								case "h":
									System.out.println("Player hits");
									
									//d1.players[j].hit(i);
									
									d1.giveCard(d1.players[j], i);
									d1.players[j].getHand(i);
									playVerify = 1;
									if(doubleDown > 0 || splitAces > 0) break whiletrue;
									else break;
								case "s":
									playVerify = 1;
									System.out.println("Player stands");
									d1.players[j].getHand(i);
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
									if(playVerify == 0 && split == 0){
										System.out.println("Player is surrendering...");
										
										//d1.players[j].surrender();
										
										float ret = (float)bet / (float)2;
										d1.giveMoney(d1.players[j], ret);
										cntLose++;
										break whiletrue;
									}else System.out.println("Cannot surrender...");
									break;
								case "2":
									if(playVerify == 0 && (d1.players[j].hands[i].getTotalValue() == 9 
									|| d1.players[j].hands[i].getTotalValue() == 10
									|| d1.players[j].hands[i].getTotalValue() == 11)
									&& doubleDown == 0){
										
										
										doubleDown = d1.players[j].doubleDown(minBet, maxBet);
										if(doubleDown > 0){
											System.out.println("Player is doubling...");
											bet = 2 * bet;
										}else 
											System.out.println("Cannot double...");
										
										
										/*d1.players[j].Bet(bet, minBet, maxBet);
										bet = 2 * bet;
										doubleDown = 1;*/
										break;
									}else System.out.println("Cannot double...");
									break;
								case "p":
									if(d1.players[j].hands[i].getCard(0).getValue() == d1.players[j].hands[i].getCard(1).getValue()
											|| d1.players[j].hands[i].getCard(0).getFigure().equals(d1.players[j].hands[i].getCard(1).getFigure())){
										if(d1.players[j].Bet(bet, minBet, maxBet) != 1) System.out.println("Not enough balance to split...");
										
										maxSplit = d1.players[j].split(i);
										if(maxSplit == -1)
											break;
										if(d1.players[j].hands[i].getCard(0).getFigure().equals("A"))
											splitAces = 1;
										split = 1;
										System.out.println("Player is splitting...");
										d1.players[j].getHand(i);
										break;
									}else System.out.println("Cannot split...");
									break;
								case "ad":
									d1.players[j].advice(i, 0, 
											playVerify == 0 && (d1.players[j].hands[i].getTotalValue() == 9 
											|| d1.players[j].hands[i].getTotalValue() == 10
											|| d1.players[j].hands[i].getTotalValue() == 11)
											&& doubleDown == 0 ? 1 : 0,
											playVerify == 0 && d1.hand.checkInsurance() == 1 
											&& insurance == 0 ? 1 : 0,
											(d1.players[j].hands[i].getCard(0).getValue() == d1.players[j].hands[i].getCard(1).getValue()
											|| d1.players[j].hands[i].getCard(0).getFigure().equals(d1.players[j].hands[i].getCard(1).getFigure()))
											&& maxSplit == 0 ? 1 : 0,		
											playVerify == 0 && split == 0 ? 1 : 0);
									break;
								default:
									System.out.println(command + ": Illegal command!!");
							}
						}
					}
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
										d1.getHand();
									}else{
										System.out.println("Dealer stands");
										d1.hand.turnCard();
										d1.getHand();
										break;
									}
								}
										
								if(d1.hand.getTotalValue() > d1.players[j].hands[i].getTotalValue() && d1.hand.getTotalValue() < 22){
									
									cntLose++;
									if(d1.hand.getTotalValue() == 21 && d1.hand.getNrCards() == 2){
										System.out.println("Blackjack!!");
										dealerBJ++;
										if(insurance == 1){
											System.out.println("Insurance Money!!");
											d1.giveMoney(d1.players[j], 2 * bet);
										}
									}
									System.out.println("Player[" + j + "]'s hand[" + i + "] lost and his current balance is " + d1.players[j].getMoney());
								}
								else if(d1.hand.getTotalValue() == d1.players[j].hands[i].getTotalValue()){
									if(d1.hand.getTotalValue() == 21){
										if(d1.hand.getNrCards() == 2){
											System.out.println("Blackjack!!");
											dealerBJ++;
											if(insurance == 1){
												System.out.println("Insurance Money!!");
												d1.giveMoney(d1.players[j], 2 * bet);
											}
										}
										if(d1.players[j].hands[i].getNrCards() == 2 && split == 0){
											System.out.println("Blackjack!!");
											playerBJ++;
										}
									}
									if(d1.players[j].hands[i].getNrCards() == 2 
											&& d1.hand.getNrCards() > 2 
											&& d1.players[j].hands[i].getTotalValue() == 21 
											&& split == 0){
										
										cntWin++;
										float ret = (float) (2.5 * bet);
										d1.giveMoney(d1.players[j], ret);
										System.out.println("Player[" + j + "]'s hand[" + i + "] won and his current balance is " + d1.players[j].getMoney());
									}else if(d1.players[j].hands[i].getNrCards() == 2 
											&& d1.hand.getNrCards() > 2 
											&& d1.players[j].hands[i].getTotalValue() == 21){
										
										cntWin++;
										d1.giveMoney(d1.players[j], 2 * bet);
										System.out.println("Player[" + j + "]'s hand[" + i + "] won and his current balance is " + d1.players[j].getMoney());
									}else if(d1.players[j].hands[i].getNrCards() > 2 && d1.hand.getNrCards() == 2 && d1.players[j].hands[i].getTotalValue() == 21){
										
										d1.getHand();
										System.out.println("Player[" + j + "]'s hand[" + i + "] lost and his current balance is " + d1.players[j].getMoney());
										cntLose++;
									}else{
										
										cntPush++;
										d1.giveMoney(d1.players[j], bet);
										System.out.println("Player[" + j + "]'s hand[" + i + "] push and his current balance is " + d1.players[j].getMoney());
									}
								}else if(d1.hand.getTotalValue() < 22){
									
									cntWin++;
									if(d1.players[j].hands[i].getTotalValue() == 21 
											&& d1.players[j].hands[i].getNrCards() == 2 
											&& split == 0){
										playerBJ++;
										System.out.println("Blackjack!!");
										float ret = (float) (2.5 * bet);
										d1.giveMoney(d1.players[j], ret);
									}else d1.giveMoney(d1.players[j], 2 * bet);
									System.out.println("Player[" + j + "]'s hand[" + i + "] won and his current balance is " + d1.players[j].getMoney());
								}
								else if(d1.hand.getTotalValue() > 21){
									
									cntWin++;
									if(d1.players[j].hands[i].getTotalValue() == 21 
											&& d1.players[j].hands[i].getNrCards() == 2
											&& split == 0){
										playerBJ++;
										System.out.println("Blackjack!!");
										float ret = (float) (2.5 * bet);
										d1.giveMoney(d1.players[j], ret);
									}else d1.giveMoney(d1.players[j], 2 * bet);
									System.out.println("Player[" + j + "]'s hand[" + i + "] won and his current balance is " + d1.players[j].getMoney());
								}
							}else{
								d1.hand.turnCard();
								d1.getHand();
								
								cntLose++;
								if(d1.hand.getTotalValue() == 21 && d1.hand.getNrCards() == 2){
									dealerBJ++;
									System.out.println("Blackjack!!");
									if(insurance == 1){
										System.out.println("Insurance Money!!");
										d1.giveMoney(d1.players[j], 2 * bet);
									}
								}
								System.out.println("Player[" + j + "]'s hand[" + i + "] lost and his current balance is " + d1.players[j].getMoney());
							}
						}
					}
				}
			}
		}	
	}
}
