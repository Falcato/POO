package lab2_75876;

public class Main {

	public static void main(String[] args) {
		
		IterableArray<String> ia = new IterableArray<String>(4);
		try {
			ia.add("Hello");
			ia.add("Iterable");
			ia.add("Array");
			ia.add("!");
		} catch (IAException e) {
			System.err.println("Array too short " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println(ia);
		
		IAIterator<String> iia = new IAIterator<String>(ia);
		
		while(iia.hasNext()){
			System.out.println(iia.next());
		}
		System.out.println(iia.iarray.array[iia.iarray.size - 1]);
		
		iia = (IAIterator<String>) ia.iterator();
		
		while(iia.hasNext()){
			iia.next();
			iia.remove();
		}
		iia.next();
		iia.remove();
		
		System.out.println(ia);
	}

}
