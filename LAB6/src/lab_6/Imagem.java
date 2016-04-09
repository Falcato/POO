package lab_6;

import java.util.ArrayList;
import java.util.Arrays;

public class Imagem {

	int altura;
	int largura;
	ArrayList<Forma> formas = new ArrayList<Forma>(); 
	
	public Imagem(int altura, int largura){
		this.altura = altura;
		this.largura = largura;
	}
	
	public void adicionarForma(Forma f){
		formas.add(f);	
	}
	
	public String linha(int y){
		
		char[] res = new char[largura];
		Arrays.fill(res, ' ');
		
		for(Forma f: formas){
			
			int[] inters = f.interseccao(y);
			
			for(int j: inters){
				res[j] = '*';
			}
		}
			
		return new String(res);
	}

	@Override
	public String toString() {
	
		String res = new String();
		
		for(int i = 0; i < altura; i++){
			
			res += linha(i) + '\n';
			
		}
		
		return res;
		
	}
	
	
}
