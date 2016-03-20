package lab4;

public class Share {
	
	protected int quantity;
	StockOwner so;
	Corporation c;
	
	//CONSTRUCTOR
	public Share(int qtd, StockOwner so, Corporation c){
		
		this.quantity = qtd;
		this.so = so;
		this.c = c;
		
	}

}
