package lab2_75876;

import java.util.Arrays;
import java.util.Iterator;

public class IterableArray<T> implements Iterable<T>{
	Object[] array;
	int size;
	
	public IterableArray(int size){
		array = new Object[size];
		this.size = size;
		
		for(int i = 0; i < size; i++)
			array[i] = null;
	}
	
	void add(T t) throws IAException{
		int i;
		
		if(array[0] == null){
			array[0] = t;
		}else{
			for(i = 0; array[i] != null ; i++);
			if(i < size)
				array[i] = t;
			else throw new IAException();
		}
	}

	@Override
	public String toString() {
		return "IterableArray [array=" + Arrays.toString(array) + "]";
	}

	@Override
	public Iterator<T> iterator() {
		return new IAIterator<T>(this);
	}
	
}
