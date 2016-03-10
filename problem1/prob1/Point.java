package prob1;

import java.util.HashSet;

public class Point {

	int x, y;
	
	//Constructors
	
	public Point(){
		//x=0; y=0;
		//this(0,0);
	}
	
	public Point(int x){
		//this.x=x;
		this(x,0);
	}	
	
	
	public Point(int xx, int yy){
		x=xx;
		y=yy;
	}
	
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("Vais morrer... "+this);
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Point p00_1 = new Point(); //Point(0); Point(0,0);
		Point p00_2 = new Point();
		Point p23 = new Point(2,3);
		
		System.out.println("Ponto 00 (1): "+p00_1);
		System.out.println("Ponto 00 (2): "+p00_2);
		System.out.println("Ponto 23: "+p23);
		
		System.out.println(p00_1==p00_2);
		System.out.println(p00_1==p23);
		System.out.println(p00_1.equals(p00_2));
		System.out.println(p00_1.equals(p23));
		
		System.out.println(p00_1.hashCode());
		System.out.println(p00_2.hashCode());
		System.out.println(p23.hashCode());
		
		HashSet<Point> hsp = new HashSet<Point>();
		hsp.add(p00_1);
		hsp.add(p00_2);
		hsp.add(p23);
		System.out.println(hsp.size());
		System.out.println(hsp);
		
		p00_1=null;
		p00_2=null;
		p23=null;
		
		System.gc();
		
	}


}
