package lab_6;

public abstract class Forma {

	int pos_x;
	int pos_y;
	
	public Forma(int pos_x, int pos_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
	
	public abstract int[] interseccao(int y);

}
