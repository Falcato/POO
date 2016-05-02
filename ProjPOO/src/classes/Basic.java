package classes;

public class Basic extends Strategy{

	public Basic(Hand h1, Hand h2){
		super(h1, h2);
	}
	
	int checkAces(){
		int aceCnt = 0;
		for(int i = 0; i < playerHand.getNrCards(); i++){
			if(playerHand.getCard(i).getFigure().equals("A")) aceCnt = 1;
		}
		return aceCnt;
	}
	
	int checkHandType(){
		if(playerHand.getCard(0).getValue() == playerHand.getCard(1).getValue()){
			return 3;
		}else if(checkAces() == 1){
			return 2;
		}else{
			return 1;
		}
	}
	
	@Override
	void advice() {
		
		if(checkHandType() == 3){
			
			//System.out.println("Pairs Hand!!");
			
			if(playerHand.getCard(0).getFigure().equals("A") || 
					playerHand.getCard(0).getFigure().equals("8"))
				System.out.println("Split");
			else if(playerHand.getCard(0).getValue() == 10)
				System.out.println("Stand");
			else if(playerHand.getCard(0).getValue() == 9){
				if(dealerHand.getCard(0).getValue() == 7
						|| dealerHand.getCard(0).getValue() == 10 
						|| dealerHand.getCard(0).getFigure().equals("A"))
					System.out.println("Stand");
				else System.out.println("Split");
			}else if(playerHand.getCard(0).getValue() == 7){
				if(dealerHand.getCard(0).getValue() == 8
						|| dealerHand.getCard(0).getValue() == 9
						|| dealerHand.getCard(0).getValue() == 10
						|| dealerHand.getCard(0).getFigure().equals("A"))
					System.out.println("Hit");
				else System.out.println("Split");
			}else if(playerHand.getCard(0).getValue() == 6){
				if(dealerHand.getCard(0).getValue() == 3
						|| dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					System.out.println("Split");
				else System.out.println("Hit");
			}else if(playerHand.getCard(0).getValue() == 5){
				if(dealerHand.getCard(0).getValue() == 10
						|| dealerHand.getCard(0).getFigure().equals("A"))
					System.out.println("Hit");
				else System.out.println("Double else Hit");
			}else if(playerHand.getCard(0).getValue() == 4)
				System.out.println("Hit");
			else if(playerHand.getCard(0).getValue() == 3 ||
					playerHand.getCard(0).getValue() == 2){
				if(dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6
						|| dealerHand.getCard(0).getValue() == 7)
					System.out.println("Split");
				else System.out.println("Hit");
			}
		}else if(checkHandType() == 2){
			
			//System.out.println("Soft Hand!!");
			
			if(playerHand.getTotalValue() > 18 && playerHand.getTotalValue() < 22)
				System.out.println("Stand");
			else if(playerHand.getTotalValue() == 18){
				if(dealerHand.getCard(0).getValue() == 2 
						|| dealerHand.getCard(0).getValue() == 7
						|| dealerHand.getCard(0).getValue() == 8)
					System.out.println("Stand");
				else if(dealerHand.getCard(0).getValue() == 9 
						|| dealerHand.getCard(0).getValue() == 10
						|| dealerHand.getCard(0).getFigure().equals("A"))
					System.out.println("Hit");
				else System.out.println("Double else Stand");
			}else if(playerHand.getTotalValue() == 17){
				if(dealerHand.getCard(0).getValue() == 3 
						|| dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					System.out.println("Double else Hit");
				else System.out.println("Hit");
			}else if(playerHand.getTotalValue() == 16 || playerHand.getTotalValue() == 15){
				if(dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					System.out.println("Double else Hit");
				else System.out.println("Hit");
			}else if(playerHand.getTotalValue() == 13 || playerHand.getTotalValue() == 14){
				if(dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					System.out.println("Double else Hit");
				else System.out.println("Hit");
			}
		}else if(checkHandType() == 1){
			
			//System.out.println("Hard Hand!!");
			
			if(playerHand.getTotalValue() > 16 && playerHand.getTotalValue() < 22)
				System.out.println("Stand");
			else if(playerHand.getTotalValue() == 16){
				if(dealerHand.getCard(0).getValue() == 7
						|| dealerHand.getCard(0).getValue() == 8)
					System.out.println("Hit");
				else if(dealerHand.getCard(0).getValue() == 9 
						|| dealerHand.getCard(0).getValue() == 10
						|| dealerHand.getCard(0).getFigure().equals("A"))
					System.out.println("Surrender else Hit");
				else System.out.println("Stand");
			}else if(playerHand.getTotalValue() == 15){
				if(dealerHand.getCard(0).getValue() == 7
						|| dealerHand.getCard(0).getValue() == 8
						|| dealerHand.getCard(0).getValue() == 9
						|| dealerHand.getCard(0).getFigure().equals("A"))
					System.out.println("Hit");
				else if(dealerHand.getCard(0).getValue() == 10)
					System.out.println("Surrender else Hit");
				else System.out.println("Stand");
			}else if(playerHand.getTotalValue() == 13 || playerHand.getTotalValue() == 14){
				if(dealerHand.getCard(0).getValue() == 2
						|| dealerHand.getCard(0).getValue() == 3
						|| dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					System.out.println("Stand");
				else System.out.println("Hit");
			}else if(playerHand.getTotalValue() == 12){
				if(dealerHand.getCard(0).getValue() == 4 
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					System.out.println("Stand");
				else System.out.println("Hit");
			}else if(playerHand.getTotalValue() == 11){
				if(dealerHand.getCard(0).getFigure().equals("A"))
					System.out.println("Hit");
				else System.out.println("Double else Hit");
			}else if(playerHand.getTotalValue() == 10){
				if(dealerHand.getCard(0).getFigure().equals("A")
						|| dealerHand.getCard(0).getValue() == 10)
					System.out.println("Hit");
				else System.out.println("Double else Hit");
			}else if(playerHand.getTotalValue() == 9){
				if(dealerHand.getCard(0).getValue() == 3
						|| dealerHand.getCard(0).getValue() == 4
						|| dealerHand.getCard(0).getValue() == 5
						|| dealerHand.getCard(0).getValue() == 6)
					System.out.println("Double else Hit");
				else System.out.println("Hit");
			}else System.out.println("Hit");
		}
	}
}
