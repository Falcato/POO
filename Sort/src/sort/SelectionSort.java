package sort;

public class SelectionSort extends SortInteger{

	public SelectionSort(int[] val) {
		super(val);
	}

	@Override
	protected final void sort() {
		
        for (int i = 0; i < getDataLenght() - 1; i++) {
        	int indiceMenor = i;

            // compara com os outros valores do vetor
            for (int j = i + 1; j < getDataLenght(); j++){
                if (compare(j, i) == -1){      //vetor[j] < menor
                    indiceMenor = j;
                }
            }

            // troca os valores menor e maior
            //vetor[indiceMenor] = vetor[i];
            swap(indiceMenor, i);
        }
	}

}
