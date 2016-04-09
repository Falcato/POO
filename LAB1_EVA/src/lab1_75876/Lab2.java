package lab1_75876;

import java.util.Arrays;

public class Lab2 {

	private Lab1 labs1[] = new Lab1[10];
	private int i = 0;
	
	
	@Override
	public String toString() {
		return "Lab2[labs1=" + Arrays.toString(labs1) + "]";
	}

	
	public boolean associateLab1(Lab1 lab1){
		
		if(i < 10){
			boolean eq = false;
			for(int j = 0; j < i; j++){
				if(labs1[j].equals(lab1)) eq = true;
			}
			
			if(eq == false){
				labs1[i] = lab1;
				i++;
				return true;
			}
			else return false;
		}else{
			System.out.println("Trying to associate more than 10 objects!");
			System.exit(2);
		}
		return false;
			
	}
	
}
