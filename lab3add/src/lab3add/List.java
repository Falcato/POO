package lab3add;

public class List {

	
	public class Node{
		int number;
		Node next;
		Node previous;
	}

	Node head;
	Node aux;
	
	//CONSTRUCTOR NO ARGS
	public List(){
		head = null;		
	}
	
	//CONSTRUCTOR 1 ARG
	public List(int nr){
		head = new Node();
		head.number = nr;
		head.next = null;
		head.previous = null;
	}
	
	//INSERT
	public void Insert(int nr){
		
		//SE A LISTA ESTIVER VAZIA
		if(head == null){
			head = new Node();
			head.number = nr;
			head.next = null;
			head.previous = null;
		}else{
						
			//CHEGAR AO FINAL DA LISTA
			for(aux = head; aux.next != null ; aux = aux.next);
			
			//CRIAR ELEMENTO
			Node curr = new Node();
			curr.number = nr;
			curr.next = null;
			curr.previous = aux;
			
			//INSERIR ELEMENTO
			aux.next = curr;
			
		}	
	}
	
	public int Remove(int nr){
		
		int removed = 0;
		
		for(aux = head; aux.next != null ; aux = aux.next){
			
			//SE FOR NUMERO A REMOVER
			if(aux.number == nr){
				
				aux.previous.next = aux.next;
				aux.next.previous = aux.previous;
				aux = null;
				removed ++;
			}
		}
		
		return removed;
	}
	
	public int Length(){
		
		int length = 0;
		
		//PERCORRE A LISTA
		for(aux = head; aux != null; aux = aux.next){
			length++;
		}
		
		return length;
	}
	
	@Override
	public String toString() {
		
		String lista = "{";
		
		for(aux = head; aux != null; aux = aux.next){
			lista = lista + aux.number;
			if(aux.next != null) lista = lista + ",";
		}
		
		lista = lista + "}";
		return lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aux == null) ? 0 : aux.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		List other = (List) obj;
		if (aux == null) {
			if (other.aux != null) {
				return false;
			}
		} else if (!aux.equals(other.aux)) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List list = new List(1);
		list.Insert(2);
		list.Insert(3);
		
		//System.out.println(list.Length());
		System.out.println("lista 1 " + list.toString());
		
		
		List list2 = new List();
		list2.Insert(1);
		list2.Insert(2);
		list2.Insert(3);
		
		//System.out.println(list.Length());
		System.out.println("lista 2 " + list2.toString());
		
		if(list == list2){
			System.out.println("true ==");
		}else System.out.println("false ==");
		
		
		if(list.equals(list2)){
			System.out.println("true equals");
		}else System.out.println("false equals");
		
		
		list=null;
		System.gc();
	}
}
