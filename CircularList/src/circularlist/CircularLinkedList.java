package circularlist;

public class CircularLinkedList<T> {
	
	CLLNode<T> head;
	double nrElems = 0;
	
	public void add(T t){
		
		CLLNode<T> auxhead = null;
		
		if(head != null){
			auxhead = head;
			head = new CLLNode<T>(t);
			CLLNode<T> aux;					
			for(aux = auxhead; aux.prox != auxhead; aux = aux.prox); 
			
			aux.prox = head;
			head.prox = auxhead;
			
		}else{
			head = new CLLNode<T>(t);
			head.prox = head;
		}
		
		nrElems ++;
		
	}
	
	public void remove(T t){
		
		CLLNode<T> aux;
		
		if(head.elem.equals(t)){
		
			for(aux = head; aux.prox != head; aux = aux.prox);
			aux.prox = head.prox;
			head = head.prox;
			nrElems --;
			
		}else{
			
			for(aux = head; aux.prox != head; aux = aux.prox){
				if(t.equals(aux.prox.elem)){
					aux.prox = aux.prox.prox;
					nrElems --;
				}
			}
		}
	}
	
	public CircularLinkedList(){
		head = null;
	}
	
}
