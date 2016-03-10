package main;

import lab3.Complex;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//PARTE 1
		Complex um = new Complex(2,3);
		Complex dois = new Complex(1,1);
		
		System.out.println("um " + um);
		System.out.println("dois " + dois);
		
		System.out.println("add "+ um.add(dois));
		System.out.println("sub "+ um.subtract(dois));
		System.out.println("mul "+ um.multiply(dois));
		
		
		//PARTE 2
		Complex tres = new Complex(3,4);
		Complex quatro = new Complex(3,4);
		
		if(tres == quatro){
			System.out.println("true ==");
		}else {
			System.out.println("false ==");
		}
		
		if(tres.equals(quatro)){
			System.out.println("true equals");
		}else {
			System.out.println("false equals");
		}
		
	}

}
