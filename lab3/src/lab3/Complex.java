package lab3;

public class Complex {
	
	float real, imaginary;
	
	
	//CONSTRUCTOR
	public Complex(float r, float i){
		real = r;
		imaginary = i;
	}
	
	
	//ADD
	public Complex add (Complex x){
		
		float a = this.real + x.real;
		float b = this.imaginary + x.imaginary;
		
		return new Complex(a, b);
	}
	
	
	//SUBTRACT
	public Complex subtract (Complex x){
		
		float a = this.real - x.real;
		float b = this.imaginary - x.imaginary;
		
		return new Complex(a, b);
	}
	
	
	//MULTIPLY
	public Complex multiply (Complex x){
		
		float a = this.real * x.real - this.imaginary * x.imaginary;
		float b = this.imaginary * x.real + this.real * x.imaginary;
		
		return new Complex(a, b);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(imaginary);
		result = prime * result + Float.floatToIntBits(real);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complex other = (Complex) obj;
		if (Float.floatToIntBits(imaginary) != Float.floatToIntBits(other.imaginary))
			return false;
		if (Float.floatToIntBits(real) != Float.floatToIntBits(other.real))
			return false;
		return true;
	}


	//TO STRING
	@Override
	public String toString() {
		return "Complex " + real + " + " + imaginary + "i";
	}
	
}
