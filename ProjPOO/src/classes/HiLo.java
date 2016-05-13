package classes;
/**
 * 
 * Implements the high-low strategy in a blackjack game.
 * It involves card counting and based on that count and
 * both the player and dealer's hand returns the best
 * move to be made.
 */
public class HiLo extends Strategy{

	int runningCnt;
	int nrDecksLeft;
	float trueCnt;
	
	/**
	 * Creates a high-low strategy receives two hands, the dealer's and player's,
	 * and sets the local variables to zero.
	 *   
	 * @param h1 Dealer's Hand
	 * @param h2 Player's Hand
	 */
	public HiLo(Hand h1, Hand h2) {
		super(h1, h2);
		runningCnt = 0;
		nrDecksLeft = 0;
		trueCnt = 0;
	}

	
	
	@Override
	String advice(int dd, int in, int sp, int su) {
		
		Strategy bs = new Basic(dealerHand, playerHand);
		trueCnt = (float)runningCnt / (float)nrDecksLeft;
		
		if(trueCnt > 2 && in == 1) return "insure";
		if(playerHand.getTotalValue() == 16){
			if(dealerHand.getCard(0).getValue() == 10){
				if(trueCnt > -1) return "stand";
				else return "hit";
			}else if(dealerHand.getCard(0).getValue() == 9){
				if(trueCnt > 4) return "stand";
				else return "hit";
			}else return bs.advice(dd, in, sp, su);
		}else if(playerHand.getTotalValue() == 15){
			if(dealerHand.getCard(0).getValue() == 10){
				if(trueCnt > -1 && trueCnt < 4 && su == 1) return "surrender";
				else if(trueCnt > 3) return "stand";
				else return "hit";
			}else if(dealerHand.getCard(0).getValue() == 9){
				if(trueCnt > 1 && su == 1) return "surrender";
				else return bs.advice(dd, in, sp, su);
			}else if(dealerHand.getCard(0).getFigure().equals("A")){
				if(trueCnt > 0 && su == 1) return "surrender";
				else return bs.advice(dd, in, sp, su);
			}else return bs.advice(dd, in, sp, su);
		}else if(playerHand.getTotalValue() == 14){
			if(dealerHand.getCard(0).getValue() == 10){
				if(trueCnt > 2 && su == 1) return "surrender";
				else return bs.advice(dd, in, sp, su);
			}else return bs.advice(dd, in, sp, su);
		}else if(playerHand.getTotalValue() == 13){
			if(dealerHand.getCard(0).getValue() == 3){
				if(trueCnt > -3) return "stand";
				else return "hit";
			}else if(dealerHand.getCard(0).getValue() == 2){
				if(trueCnt > -2) return "stand";
				else return "hit";
			}else return bs.advice(dd, in, sp, su);
		}else if(playerHand.getTotalValue() == 12){
			if(dealerHand.getCard(0).getValue() == 6){
				if(trueCnt > -2) return "stand";
				else return "hit";
			}else if(dealerHand.getCard(0).getValue() == 5){
				if(trueCnt > -3) return "stand";
				else return "hit";
			}else if(dealerHand.getCard(0).getValue() == 4){
				if(trueCnt > -1) return "stand";
				else return "hit";
			}else if(dealerHand.getCard(0).getValue() == 3){
				if(trueCnt > 1) return "stand";
				else return "hit";
			}else if(dealerHand.getCard(0).getValue() == 2){
				if(trueCnt > 2) return "stand";
				else return "hit";
			}else return bs.advice(dd, in, sp, su);
		}else if(playerHand.getTotalValue() == 11){
			if(dealerHand.getCard(0).getFigure().equals("A")){
				if(trueCnt > 0 && dd == 1) return "double";
				else return "hit";
			}else return bs.advice(dd, in, sp, su);
		}else if(playerHand.getTotalValue() == 10){
			if(dealerHand.getCard(0).getValue() == 10){
				if(trueCnt > 3 && dd == 1) return "double";
				else return "hit";
			}else if(dealerHand.getCard(0).getFigure().equals("A")){
				if(trueCnt > 3 && dd == 1) return "double";
				else return "hit";
			}else return bs.advice(dd, in, sp, su);
		}else if(playerHand.getTotalValue() == 9){
			if(dealerHand.getCard(0).getValue() == 7){
				if(trueCnt > 2 && dd == 1) return "double";
				else return "hit";
			}else if(dealerHand.getCard(0).getValue() == 2){
				if(trueCnt > 0 && dd == 1) return "double";
				else return "hit";
			}else return bs.advice(dd, in, sp, su);
		}else if(playerHand.getCard(0).getValue() == 10 && playerHand.getCard(1).getValue() == 10){
			if(dealerHand.getCard(0).getValue() == 6){
				if(trueCnt > 3 && sp == 1) return "split";
				else return "stand";
			}else if(dealerHand.getCard(0).getValue() == 5){
				if(trueCnt > 4 && sp == 1) return "split";
				else return "stand";
			}else return bs.advice(dd, in, sp, su);
		}else return bs.advice(dd, in, sp, su);
	}
}
