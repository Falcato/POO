package lab_6;

public class Triangulo extends Forma{
	
	int base;
	int altura;
	float m;
	float besq;
	float bdto;
	
	public Triangulo(int pos_x, int pos_y, int base, int altura){
		super(pos_x, pos_y);
		this.base = base;
		this.altura = altura;
		
		m = (float) altura / ((float)base /(float) 2);
		bdto = pos_y + (float) (m * pos_x);
		besq = pos_y - (float) (m * pos_x);
		
	}

	
	@Override
	public int[] interseccao(int y) {
		
		
		int pos[];
		
		//TOPO
		if(y == pos_y){
			pos = new int[1];
			pos[0] = pos_x;
			return pos;
			
		//CORPO
		}else if(y > pos_y && y < pos_y + altura - 1){
			
			pos = new int[2];
			pos[0] = (int) ((y - besq)/(m));
			pos[1] = (int) ((y - bdto)/(-m));
			return pos;
			
		//BASE
		}else if(y == pos_y + altura -1){
			
			pos = new int[base];
			
			for(int i = 0; i < base; i++){
				pos[i] = pos_x - base / 2 + i;
			}
			
			return pos;
		}
		
		return new int[0];
	}
	
	

}
