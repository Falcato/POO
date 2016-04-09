package lab1_75876;

import java.util.Random;

public class Main {
	
	public static void main(String[] args){
		
		if(args.length < 1) System.out.println("Input number must be greater than 1");
		
		int n = Integer.parseInt(args[0]);
		Lab2 labs2[] = new Lab2[n];
		
		int it = 1;
		int i, j;
		Random random = new Random();
		int rdm;
		
		while(true){
			
			boolean ver = true;
			
			for(i = 0; i < n; i++){
				Lab2 lab2 = new Lab2();
				labs2[i] = lab2;
				
				for(j = 0; j < 10; j++){
					rdm = random.nextInt(10);
					Lab1 lab1 = new Lab1(rdm, 0);
					if(labs2[i].associateLab1(lab1) == false){
						ver = false;
					}
				}
				if(ver == true){
					System.out.println("Found it: " + labs2[i] + " after " + it + " iterations");
					System.exit(3);
				}
			}
			it++;
			
		}
		
	}

}
