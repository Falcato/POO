package circularlist;

public class Main {

	public static void main(String[] args) {
		
		NumericCircularLinkedList<Integer> list = new NumericCircularLinkedList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);

		System.out.println(list.average());
				
		list.remove(7);
		list.remove(2);
		list.add(6);
		
		System.out.println(list.average());
		
	}

}
