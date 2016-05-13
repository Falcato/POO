package classes;

import java.util.ArrayList;

/**
 * 
 * The simulation class implements one of three
 * possible game modes. In this mode, the moves
 * are made solely based on the advices given by
 * the strategy that is input in the beggining of
 * the application. It has no human interaction 
 * whatsoever and reproduces the statistics after
 * a predermined number of shuffles.
 */
public class Simulation extends Game{

	/**
	 * Constructor for the Simulation class.
	 * Receives the input arguments by the user.
	 * 
	 * @param args input arguments
	 */
	public Simulation(String[] args){
		this.args = args;
	}
	
	/**
	 * Receives the input arguments and verifies if their
	 * values are in conformity with the project specifications.
	 * 
	 * @param args input arguments
	 * @return 0 if invalid arguments
	 * 		   1 otherwise
	 */
	public static int checkArgs(String[] args){
		
		if(args.length != 8)
			return 0;
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
	
	/**
	 * Receives the strategy to be used through the game
	 * and verifies if it's one of the supported strategies.
	 * 
	 * @param strat strategy in input arguments
	 * @return 1 if basic strategy
	 * 			2 if basic strategy with ace-five
	 * 			3 if high-low strategy
	 * 			4 if high-low strategy with ace-five
	 * 			0 if not supported strategy
	 */
	int checkStrat(String strat){
		switch(strat){
			case "BS" : return 1;
			case "BS-AF" : return 2;
			case "HL" : return 3;
			case "HL-AF" : return 4;
			default : return 0;
		}
	}
	
	@Override
	void game() {
		
		if(checkArgs(args) == 0) System.exit(0);
		
		d1 = new Dealer();
		p1 = new Player(d1);
		ArrayList<Card> cards = new ArrayList<Card>();
				
		d1.addPlayer(p1);
		p1.setMoney(Integer.parseInt(args[3]));
		
		shoe = null;
		shoe = new Shoe(Integer.parseInt(args[4]), cards, args[0], Integer.parseInt(args[5]));
		shoe.Shuffle();
		d1.setShoe(shoe);
	
		int strat = checkStrat(args[7]);
		if(strat == 0)
			System.exit(0);
		
		//VARIAVEL SO DO SIM MODE
		int lastRound = 0;
		String command;
		
		//VARIAVEL DE CONTROLO
		int playVerify = 0;
		
		//VARIAVEIS APOSTA
		int bet = 0;
		int minBet = Integer.parseInt(args[1]);
		int maxBet = Integer.parseInt(args[2]);
		
		int insurance = 0;
		int doubleDown = 0;
		int split = 0;
		int maxSplit = 0;
		
		//VARIAVEIS ESTATISTICAS
		int cntPush = 0;
		int cntWin = 0;
		int cntLose = 0;
		int dealerBJ = 0;
		int playerBJ = 0;
		
		whilestats:
		
		while(shoe.getShufflesDone() < Integer.parseInt(args[6])){
			
			insurance = 0;
			doubleDown = 0;
			split = 0;
			maxSplit = 0;
			//System.out.println(bet);
					
			if(strat == 1 || strat == 3){
				if(lastRound == 1){
					bet -= minBet;
					if(bet < minBet)
						bet = minBet;
					if(bet > maxBet)
						bet = maxBet;
					if(p1.Bet(bet, minBet, maxBet) != 1){
						System.out.println("b " + bet + ": Illegal command!!");
						break whilestats;
					}
				}else if(lastRound == 2){
					if(bet < minBet)
						bet = minBet;
					if(bet > maxBet)
						bet = maxBet;
					if(p1.Bet(bet, minBet, maxBet) != 1){
						System.out.println("b " + bet + ": Illegal command!!");
						break whilestats;
					}
				}else if(lastRound == 3){
					bet += minBet;
					if(bet < minBet)
						bet = minBet;
					if(bet > maxBet)
						bet = maxBet;
					if(p1.Bet(bet, minBet, maxBet) != 1){
						System.out.println("b " + bet + ": Illegal command!!");
						break whilestats;
					}
				}else if(lastRound == 0){
					bet = minBet;
					if(p1.Bet(bet, minBet, maxBet) != 1){
						System.out.println("b " + bet + ": Illegal command!!");
						break whilestats;
					}
				}
			}else if(strat == 2 || strat == 4){
				if(shoe.getAceFive() > 1){
					bet = 2 * bet;
					if(bet > maxBet)
						bet = maxBet;
					if(bet < minBet)
						bet = minBet;
					if(p1.Bet(bet, minBet, maxBet) != 1){
						System.out.println("b " + bet + ": Illegal command!!");
						break whilestats;
					}
				}else{
					if(bet > maxBet)
						bet = maxBet;
					if(bet < minBet)
						bet = minBet;
					if(p1.Bet(bet, minBet, maxBet) != 1){
						System.out.println("b " + bet + ": Illegal command!!");
						break whilestats;
					}
				}
			}
			
			d1.Deal(1);
			
			for(int j = 0; j < d1.CntPlayers; j++){
				
				for(int i = 0; i < d1.players[j].nrHands + 1; i++){
					
					whiletrue:
					
					while(true){
						
						if(d1.players[j].hands[i].getTotalValue() > 21) break;
												
						
						//PLAYER
						if(strat == 1 || strat == 2)
							command = d1.players[j].advice(i, 1, 
									playVerify == 0 && (d1.players[j].hands[i].getTotalValue() == 9 
									|| d1.players[j].hands[i].getTotalValue() == 10
									|| d1.players[j].hands[i].getTotalValue() == 11)
									&& doubleDown == 0 
											&& d1.players[j].getMoney() > bet ? 1 : 0,
									playVerify == 0 && d1.hand.checkInsurance() == 1
									&& insurance == 0 ? 1 : 0,
									(d1.players[j].hands[i].getCard(0).getValue() == d1.players[j].hands[i].getCard(1).getValue()
									|| d1.players[j].hands[i].getCard(0).getFigure().equals(d1.players[j].hands[i].getCard(1).getFigure()))
									&& maxSplit == 0
											&& d1.players[j].getMoney() > bet ? 1 : 0,		
									playVerify == 0 && split == 0 ? 1 : 0)[0];
						else
							command = d1.players[j].advice(i, 1, 
									playVerify == 0 && (d1.players[j].hands[i].getTotalValue() == 9 
									|| d1.players[j].hands[i].getTotalValue() == 10
									|| d1.players[j].hands[i].getTotalValue() == 11)
									&& doubleDown == 0
									&& d1.players[j].getMoney() > bet ? 1 : 0,
									playVerify == 0 && d1.hand.checkInsurance() == 1 
									&& insurance == 0? 1 : 0,
									(d1.players[j].hands[i].getCard(0).getValue() == d1.players[j].hands[i].getCard(1).getValue()
									|| d1.players[j].hands[i].getCard(0).getFigure().equals(d1.players[j].hands[i].getCard(1).getFigure()))
									&& maxSplit == 0 
									&& d1.players[j].getMoney() > bet ? 1 : 0,				
									playVerify == 0 && split == 0 ? 1 : 0)[1];
						
						
						switch(command) {
											
							case "hit":
								d1.giveCard(d1.players[j], i);
								playVerify = 1;
								if(doubleDown == 1) break whiletrue;
								else break;
							case "stand":
								playVerify = 1;							
								break whiletrue;
							case "insure":
									d1.players[j].Bet(bet, minBet, maxBet);
									insurance = 1;
									break;
							case "surrender":
									float ret = (float)bet / (float)2;
									d1.giveMoney(d1.players[j], ret);
									cntLose++;
									lastRound = 1;
									break whiletrue;
							case "double":
									if(d1.players[j].Bet(bet, minBet, maxBet) != 1){
										System.out.println("b " + bet + ": Illegal command!!");
										break whilestats;
									}
									bet = 2 * bet;
									doubleDown = 1;
									break;
							case "split":
									if(d1.players[j].Bet(bet, minBet, maxBet) != 1)
										break;
									maxSplit = d1.players[j].split(i);
									split = 1;
									break;
							default : System.out.println(command);
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
									d1.getCard();
								}else{
									d1.hand.turnCard();
									break;
								}
							}
									
							if(d1.hand.getTotalValue() > d1.players[j].hands[i].getTotalValue() && d1.hand.getTotalValue() < 22){
								
								cntLose++;
								if(d1.hand.getTotalValue() == 21 && d1.hand.getNrCards() == 2){
									dealerBJ++;
									if(insurance == 1){
										d1.giveMoney(d1.players[j], 2 * bet);
									}
								}
							}
							else if(d1.hand.getTotalValue() == d1.players[j].hands[i].getTotalValue()){
								if(d1.hand.getTotalValue() == 21){
									if(d1.hand.getNrCards() == 2){
										dealerBJ++;
										if(insurance == 1){
											d1.giveMoney(d1.players[j], 2 * bet);
										}
									}
									if(d1.players[j].hands[i].getNrCards() == 2 && split == 0){
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
								}else if(d1.players[j].hands[i].getNrCards() == 2 
										&& d1.hand.getNrCards() > 2 
										&& d1.players[j].hands[i].getTotalValue() == 21){
									
									cntWin++;
									d1.giveMoney(d1.players[j], 2 * bet);
								}else if(d1.players[j].hands[i].getNrCards() > 2 && d1.hand.getNrCards() == 2 && d1.players[j].hands[i].getTotalValue() == 21){
									
									//d1.getHand();
									cntLose++;
								}else{
									
									cntPush++;
									d1.giveMoney(d1.players[j], bet);
								}
							}else if(d1.hand.getTotalValue() < 22){
								
								cntWin++;
								if(d1.players[j].hands[i].getTotalValue() == 21 
										&& d1.players[j].hands[i].getNrCards() == 2 
										&& split == 0){
									playerBJ++;
									float ret = (float) (2.5 * bet);
									d1.giveMoney(d1.players[j], ret);
								}else d1.giveMoney(d1.players[j], 2 * bet);
							}
							else if(d1.hand.getTotalValue() > 21){
								
								cntWin++;
								if(d1.players[j].hands[i].getTotalValue() == 21 
										&& d1.players[j].hands[i].getNrCards() == 2
										&& split == 0){
									playerBJ++;
									float ret = (float) (2.5 * bet);
									d1.giveMoney(d1.players[j], ret);
								}else d1.giveMoney(d1.players[j], 2 * bet);
							}
						}else{
							d1.hand.turnCard();
							
							cntLose++;
							if(d1.hand.getTotalValue() == 21 && d1.hand.getNrCards() == 2){
								dealerBJ++;
								if(insurance == 1){
									d1.giveMoney(d1.players[j], 2 * bet);
								}
							}
						}
					}
				}
			}
		}
		System.out.println("BJ P/D    " + playerBJ + "/" + dealerBJ);
		System.out.println("Win       " + cntWin);
		System.out.println("Lose      " + cntLose);
		System.out.println("Push      " + cntPush);
		System.out.println("Balance   " + p1.getMoney() + "(" +
				(float)p1.getMoney() / (float)Integer.parseInt(args[3]) + "%)");
	}
}
