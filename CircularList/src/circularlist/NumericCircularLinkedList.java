package circularlist;

public class NumericCircularLinkedList<T extends Number> extends CircularLinkedList<T>{

	public NumericCircularLinkedList(){
		
	}
	
	public double average(){
		
		double average = 0;
		CLLNode<T> aux;
		
		for(aux = head; aux.prox != head; aux = aux.prox){
			average += aux.elem.doubleValue();
		}
		average += aux.elem.doubleValue();
		return average/nrElems;
	}
	
}
	