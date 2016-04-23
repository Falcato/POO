package lab8;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CountCharWriter extends FilterWriter{
	
	char car;
	int charCnt = 0;
	
	protected CountCharWriter(Writer arg0, char c) {
		super(arg0);
		this.car = c;
	}
	
	@Override
	public void write(int c) throws IOException{
		if(c == car) charCnt++;
		super.write(c);
	}
	
	public void write(String str, int off, int len) throws IOException{
		
	}
	
}
