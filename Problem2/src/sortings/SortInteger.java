package sortings;

public abstract class SortInteger {

	/*Fields*/
	private int[] values;
	private final SortMetrics metrics = new SortMetrics();
	
	protected final int compare(int i, int j){
		metrics.compareCnt++;
		int i1 = values[i], i2 = values[j];
		if(i1==i2) return 0;
		else return (i1 < i2 ? -1 : 1);
	}
	
	protected final void swap(int i, int j){
		metrics.swapCnt++;
		int tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}
	
	protected final int getDataLenght(){
		return values.length;
	}
	
	public SortInteger(int[] v){
		values = v;
	}
	
	protected abstract void doSort();
	
	public void sort(){
		metrics.init();
		doSort();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
