package sort;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		if(args.length < 1){
			System.out.println("Too few arguments");
			System.exit(0);
		}
		
		int n = Integer.parseInt(args[0]);
		
		int[] valuesbubble = new int[n];
		int[] valuesselect = new int[n];
		Random rdm = new Random();
		
		for(int i = 0; i < n; i++){
			valuesbubble[i] = rdm.nextInt(10*n);
			valuesselect[i] = valuesbubble[i];
		}
		
		BubbleSort bsort = new BubbleSort(valuesbubble);
		SelectionSort ssort = new SelectionSort(valuesselect);
		
		System.out.print("Before ordering bubble: ");
		for(int i = 0; i < n; i++){
			System.out.print(valuesbubble[i] + " ");
		}
		System.out.println();
		
		bsort.sort();
		
		
		System.out.print("After ordering bubble: ");
		for(int i = 0; i < n; i++){
			System.out.print(valuesbubble[i] + " ");
		}
		System.out.println();
		System.out.println("BSwaps: " + bsort.swapCnt + " BComparisons: " + bsort.compareCnt);
		
		
		System.out.print("Before ordering select: ");
		for(int i = 0; i < n; i++){
			System.out.print(valuesselect[i] + " ");
		}
		System.out.println();
		
		ssort.sort();
		
		
		System.out.print("After ordering select: ");
		for(int i = 0; i < n; i++){
			System.out.print(valuesselect[i] + " ");
		}
		System.out.println();
		System.out.println("SSwaps: " + ssort.swapCnt + " SComparisons: " + ssort.compareCnt);
		
	}

}
