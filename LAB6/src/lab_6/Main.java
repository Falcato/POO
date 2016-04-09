package lab_6;

public class Main {

	public static void main(String[] args) {
		
		//Forma rect1 = new Rectangulo(1, 1, 4, 4);
		//Forma rect2 = new Rectangulo(3, 3, 4, 4);
		Forma tri1 = new Triangulo(8, 1, 10, 10);
		
		Imagem img = new Imagem(40, 40);
		
		//img.adicionarForma(rect1);
		//img.adicionarForma(rect2);
		img.adicionarForma(tri1);
		
		System.out.println(img);
		
	}

}
