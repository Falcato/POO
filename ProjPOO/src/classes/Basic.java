package classes;

/**
 * Implements the basic strategy of a blackjack game there is no
 * card counting involved, the only variables are the player and
 * dealer's hand. Based on those variables it returns the best
 * next move to be made.
 * 
 */


public class Basic extends Strategy{
	
	/**
	 * Creates a basic strategy receives two hands, the dealer's and player's.
	 *   
	 * @param h1 Dealer's Hand
	 * @param h2 Player's Hand
	 */
	public Basic(Hand h1, Hand h2){
		super(h1, h2);
	}
	
	/**
	 * Verifies if the player's hand contains aces.
	 * 
	 * @return 1 if there are aces in the player's hand
	 * 		   0 otherwise.
	 */
	int checkAces(){
		int aceCnt = 0;
		for(int i = 0; i < playerHand.getNrCards(); i++){
			if(playerHand.getCard(i).getFigure().equals("A")) aceCnt = 1;
		}
		return aceCnt;
	}
	
	/**
	 * Verifies the type of hand of the player.
	 * Returns the type of the hand.
	 * 
	 * @return 1 if the player's hand is a Hard Hand
	 * 		   2 if it's a Soft Hand
	 * 		   3 if it's a Pairs Hand.
	 */
	int checkHandType(){
		if(playerHand.getCard(0).getValue() == playerHand.getCard(1).getValue() && playerHand.getNrCards() == 2){
			return 3;
		}else if(playerHand.getCard(0).getFigure().equals(playerHand.getCard(1).getFigure()) && playerHand.getNrCards() == 2){
			return 3;
		}else if(checkAces() == 1 && playerHand.getTotalValue() > 12 && playerHand.getTotalValue() < 22){
			return 2;
		}else{
			return 1;
		}
	}
	
	@Override
	String advice(int dd, int in, int sp, int su) {
		
		if(checkHandType() == 3){
			
			//System.out.println("Pairs Hand!!");
			
			if(playerHand.getCard(0).getFigure().equals("A") || 
					playerHand.getCard(0).getFigure().equals("8"))
				return sp == 1 ? "split" : altStrategy(playerHand);
			else if(playerHand.getCard(0).getValue() == 10)
				return "stand";
			else if(playerHand.getCard(0).getValue() == 9){
				if(dealerHand.getCard(0).getValue() == 7
						|| dealerHand.getCard(0).getValue() == 10 
						|| dealerHand.getCard(0).getFigure().equals("A"))
					return "stand";
				else return sp == 1 ? "split" : altStrategy(playerHand);
			}else if(playerHand.getCard(0).getValue() == 7){
				if(dealerHand.getCard(0).getValue() == 8
						|| dealerHand.getCard(0).getValue() == 9
						|| dealerHand.getCard(0).getValue() == 10
						|| dealerHand.getCard(0).getFigure().equals("A"))
					return "hit";
				else return sp == 1 ? "split" : altStrategy(playerHand);
			}else if(playerHand.getCard(0).getValue() == 6){
				if(dealerHand.getCard(0).getValue() == 3
						|| dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					return sp == 1 ? "split" : altStrategy(playerHand);
				else return "hit";
			}else if(playerHand.getCard(0).getValue() == 5){
				if(dealerHand.getCard(0).getValue() == 10
						|| dealerHand.getCard(0).getFigure().equals("A"))
					return "hit";
				else return dd == 1 ? "double" : "hit";
			}else if(playerHand.getCard(0).getValue() == 4)
				return "hit";
			else if(playerHand.getCard(0).getValue() == 3 ||
					playerHand.getCard(0).getValue() == 2){
				if(dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6
						|| dealerHand.getCard(0).getValue() == 7)
					return sp == 1 ? "split" : altStrategy(playerHand);
				else return "hit";
			}
		}else if(checkHandType() == 2){
			
			//System.out.println("Soft Hand!!");
			
			if(playerHand.getTotalValue() > 18 && playerHand.getTotalValue() < 22)
				return "stand";
			else if(playerHand.getTotalValue() == 18){
				if(dealerHand.getCard(0).getValue() == 2 
						|| dealerHand.getCard(0).getValue() == 7
						|| dealerHand.getCard(0).getValue() == 8)
					return "stand";
				else if(dealerHand.getCard(0).getValue() == 9 
						|| dealerHand.getCard(0).getValue() == 10
						|| dealerHand.getCard(0).getFigure().equals("A"))
					return "hit";
				else return dd == 1 ? "double" : "stand";
			}else if(playerHand.getTotalValue() == 17){
				if(dealerHand.getCard(0).getValue() == 3 
						|| dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					return dd == 1 ? "double" : "hit";
				else return "hit";
			}else if(playerHand.getTotalValue() == 16 || playerHand.getTotalValue() == 15){
				if(dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					return dd == 1 ? "double" : "hit";
				else return "hit";
			}else if(playerHand.getTotalValue() == 13 || playerHand.getTotalValue() == 14){
				if(dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					return dd == 1 ? "double" : "hit";
				else return "hit";
			}
		}else if(checkHandType() == 1){
			
			//System.out.println("Hard Hand!!");
			
			if(playerHand.getTotalValue() > 16 && playerHand.getTotalValue() < 22)
				return "stand";
			else if(playerHand.getTotalValue() == 16){
				if(dealerHand.getCard(0).getValue() == 7
						|| dealerHand.getCard(0).getValue() == 8)
					return "hit";
				else if(dealerHand.getCard(0).getValue() == 9 
						|| dealerHand.getCard(0).getValue() == 10
						|| dealerHand.getCard(0).getFigure().equals("A"))
					return su == 1 ? "surrender" : "hit";
				else return "stand";
			}else if(playerHand.getTotalValue() == 15){
				if(dealerHand.getCard(0).getValue() == 7
						|| dealerHand.getCard(0).getValue() == 8
						|| dealerHand.getCard(0).getValue() == 9
						|| dealerHand.getCard(0).getFigure().equals("A"))
					return "hit";
				else if(dealerHand.getCard(0).getValue() == 10)
					return su == 1 ? "surrender" : "hit";
				else return "stand";
			}else if(playerHand.getTotalValue() == 13 || playerHand.getTotalValue() == 14){
				if(dealerHand.getCard(0).getValue() == 2
						|| dealerHand.getCard(0).getValue() == 3
						|| dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					return "stand";
				else return "hit";
			}else if(playerHand.getTotalValue() == 12){
				if(dealerHand.getCard(0).getValue() == 4 
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					return "stand";
				else return "hit";
			}else if(playerHand.getTotalValue() == 11){
				if(dealerHand.getCard(0).getFigure().equals("A"))
					return "hit";
				else return dd == 1 ? "double" : "hit";
			}else if(playerHand.getTotalValue() == 10){
				if(dealerHand.getCard(0).getFigure().equals("A")
						|| dealerHand.getCard(0).getValue() == 10)
					return "hit";
				else return dd == 1 ? "double" : "hit";
			}else if(playerHand.getTotalValue() == 9){
				if(dealerHand.getCard(0).getValue() == 3
						|| dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					return dd == 1 ? "double" : "hit";
				else return "hit";
			}else return "hit";
		}
		return null;
	}
}
