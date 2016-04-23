package circularlist;

class CLLNode<T> {
	
	T elem;
	CLLNode<T> prox;

	public CLLNode(CLLNode<T> prox, T elem){
		this.elem = elem;
		this.prox = prox;
	}
	public CLLNode(T elem){
		this.elem = elem;
	}


}
