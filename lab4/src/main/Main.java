package main;

import lab4.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StockMarket sm = new StockMarket(); 
		
		StockOwner joao = new StockOwner("João", 5000);
		StockOwner pedro = new StockOwner("Pedro", 10000);

		Corporation galp = new Corporation("Galp", 3000, 20);
		
		Share galpshare = galp.CreateShare(500);
		
		sm.PutinMarket(galpshare);
		
		sm.buy(pedro, galp, 500);
		sm.buy(joao, galp, 100);
		
		System.out.println("joao "+joao.money);
		System.out.println("pedro "+pedro.money);	
		
	}

}
