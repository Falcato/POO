package lab4;

import java.util.LinkedList;

public class StockOwner {
	
	protected String name;
	public float money;
	public LinkedList<Share> portfolio;
	
	//CONSTRUCTOR
	public StockOwner(String name, float money){
		
		this.name = name;
		this.money = money;
		portfolio = new LinkedList<Share>();
		
	}
	
	//ADD SHARE
	void add(Share share){
		
		portfolio.add(share);
		
	}
	
	//REMOVE SHARE
	void remove(Share share){
		
		portfolio.remove(share);
		
	}
	
	//ADD MONEY
	void credit(float qtd){
		
		this.money = this.money + qtd;
		
	}
	
	//REMOVE MONEY
	void debit(float qtd){
		
		this.money = this.money - qtd;
		
	}

}
