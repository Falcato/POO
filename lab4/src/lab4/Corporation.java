package lab4;

public class Corporation extends StockOwner {
	
	private float value;
	
	
	//CONSTRUCTOR
	public Corporation(String name, float money, float value){
		
		super(name, money);
		this.value = value;
		
	}
	
	//CREATE SHARE
	public Share CreateShare(int value){
		
		return new Share(value, this, this);
	
	}
	
	
	//GET VALUE
	public float getValue(){
		
		return this.value;
	
	}
}
