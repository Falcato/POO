package lab2_75876;

import java.util.Iterator;

public class IAIterator<T> implements Iterator<T> {

	IterableArray<T> iarray;
	int index;
	int check;
	
	public IAIterator(IterableArray<T> array){
		iarray = array;
		index = 0;
		check = 0;
	}
	
	@Override
	public boolean hasNext() {
		if(index < iarray.size - 1){
			if(iarray.array[index + 1] != null) 
				return true;
			else
				return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next() {
		check = 1;
		if(index < iarray.size - 1){
			return (T) iarray.array[index++];
		}
		return null;
	}
	
	public void remove() throws IllegalStateException{
		if(iarray.array[0] == null || check == 0) throw new IllegalStateException();
		else{
			for(int i = 1; i < iarray.size; i++){
				iarray.array[i - 1] = iarray.array[i];
				index = 0;
			}
			iarray.array[iarray.size - 1] = null;
		}
		check = 0;
	}

}
