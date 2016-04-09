package lab_6;

public class Rectangulo extends Forma{

	int altura;
	int largura;
	
	public Rectangulo(int pos_x, int pos_y, int largura, int altura) {
		
		super(pos_x, pos_y);
		this.altura = altura;
		this.largura = largura;
	}

	@Override
	public int[] interseccao(int y) {
		
		int pos[];
		//BASES
		if(y == pos_y || y == pos_y + altura -1){
			pos = new int[largura];
			
			for(int i = 0; i < largura; i++){
				pos[i] = pos_x+i;
			}
			
			return pos;
			
		//CORPO
		}else if(y < pos_y + altura -1 && y > pos_y){
			
			pos = new int[2];
			pos[0] = pos_x;
			pos[1] = pos_x + largura - 1;
			return pos;
				
		}
		
		return new int[0];
	}

}
