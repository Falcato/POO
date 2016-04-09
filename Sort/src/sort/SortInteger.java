package sort;

public abstract class SortInteger {

	private int[] values;
	int compareCnt;
	int swapCnt;
	
	public SortInteger(int[] val){
		values = val;
		compareCnt = 0;
		swapCnt = 0;
	}
	
	protected final void swap(int i, int j){
		swapCnt++;
		int tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}
	
	protected final int compare(int i, int j){
		compareCnt++;
		if(values[i] == values[j]) return 0;
		else return(values[i] < values[j] ? -1 : 1);
	}
	
	protected abstract void sort();
	
	protected final int getDataLenght(){
		return values.length;
	}
	
}
