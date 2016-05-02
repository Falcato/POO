package classes;

public class HiLo extends Strategy{

	int runningCnt;
	int nrDecksLeft;
	int trueCnt;
	
	public HiLo(Hand h1, Hand h2) {
		super(h1, h2);
		runningCnt = 0;
		nrDecksLeft = 0;
		trueCnt = 0;
	}

	
	
	@Override
	void advice() {
		
		Strategy bs = new Basic(dealerHand, playerHand);
		trueCnt = runningCnt / nrDecksLeft;
		
		if(trueCnt > 2) System.out.println("Insure");
		if(playerHand.getTotalValue() == 16){
			if(dealerHand.getCard(0).getValue() == 10){
				if(trueCnt > -1) System.out.println("Stand");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getValue() == 9){
				if(trueCnt > 4) System.out.println("Stand");
				else System.out.println("Hit");
			}else bs.advice();
		}else if(playerHand.getTotalValue() == 15){
			if(dealerHand.getCard(0).getValue() == 10){
				if(trueCnt > -1 && trueCnt < 4) System.out.println("Surrender");
				else if(trueCnt > 3) System.out.println("Stand");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getValue() == 9){
				if(trueCnt > 1) System.out.println("Surrender");
				else bs.advice();
			}else if(dealerHand.getCard(0).getFigure().equals("A")){
				if(trueCnt > 0) System.out.println("Surrender");
				else bs.advice();
			}else bs.advice();
		}else if(playerHand.getTotalValue() == 14){
			if(dealerHand.getCard(0).getValue() == 10){
				if(trueCnt > 2) System.out.println("Surrender");
				else bs.advice();
			}else bs.advice();
		}else if(playerHand.getTotalValue() == 13){
			if(dealerHand.getCard(0).getValue() == 3){
				if(trueCnt > -3) System.out.println("Stand");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getValue() == 2){
				if(trueCnt > -2) System.out.println("Stand");
				else System.out.println("Hit");
			}else bs.advice();
		}else if(playerHand.getTotalValue() == 12){
			if(dealerHand.getCard(0).getValue() == 6){
				if(trueCnt > -2) System.out.println("Stand");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getValue() == 5){
				if(trueCnt > -3) System.out.println("Stand");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getValue() == 4){
				if(trueCnt > -1) System.out.println("Stand");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getValue() == 3){
				if(trueCnt > 1) System.out.println("Stand");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getValue() == 2){
				if(trueCnt > 2) System.out.println("Stand");
				else System.out.println("Hit");
			}else bs.advice();
		}else if(playerHand.getTotalValue() == 11){
			if(dealerHand.getCard(0).getFigure().equals("A")){
				if(trueCnt > 0) System.out.println("Double");
				else System.out.println("Hit");
			}else bs.advice();
		}else if(playerHand.getTotalValue() == 10){
			if(dealerHand.getCard(0).getValue() == 10){
				if(trueCnt > 3) System.out.println("Double");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getFigure().equals("A")){
				if(trueCnt > 3) System.out.println("Double");
				else System.out.println("Hit");
			}else bs.advice();
		}else if(playerHand.getTotalValue() == 9){
			if(dealerHand.getCard(0).getValue() == 7){
				if(trueCnt > 2) System.out.println("Double");
				else System.out.println("Hit");
			}else if(dealerHand.getCard(0).getValue() == 2){
				if(trueCnt > 0) System.out.println("Double");
				else System.out.println("Hit");
			}else bs.advice();
		}else if(playerHand.getCard(0).getValue() == 10 && playerHand.getCard(1).getValue() == 10){
			if(dealerHand.getCard(0).getValue() == 6){
				if(trueCnt > 3) System.out.println("Split");
				else System.out.println("Stand");
			}else if(dealerHand.getCard(0).getValue() == 5){
				if(trueCnt > 4) System.out.println("Split");
				else System.out.println("Stand");
			}else bs.advice();
		}else bs.advice();
	}
}
