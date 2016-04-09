package lab1_75876;

public class Lab1 {

	int x;
	int y;
	
	@Override
	public String toString() {
		return "Lab1[x=" + x + ", y=" + y + "]";
	}
	
	public Lab1(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
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
		Lab1 other = (Lab1) obj;
		if (x != other.x) {
			return false;
		}
		return true;
	}
	
}
