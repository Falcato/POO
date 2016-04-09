package sort;

public class BubbleSort extends SortInteger{

	public BubbleSort(int[] val) {
		super(val);
	}

	@Override
	protected final void sort() {
		
		 int j;
	     boolean flag = true;   // set flag to true to begin first pass

	     while ( flag )
	     {
	            flag= false;    //set flag to false awaiting a possible swap
	            for( j=0;  j < getDataLenght() - 1;  j++ )
	            {
	                   if ( compare(j, j+1) == 1  )   // change to > for ascending sort
	                   {
	                          swap(j, j+1);
	                          flag = true;              //shows a swap occurred  
	                  } 
	            } 
	      } 
		
	}

}
