package lab8;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CountCharReader extends FilterReader{

	char c;
	int charCnt = 0;
	
	protected CountCharReader(Reader arg0, char c) {
		super(arg0);
		this.c = c;
	}
	
	@Override
	public int read() throws IOException{
		int i;
		if((i = super.read()) == c) charCnt ++;
		return i;
	}
}
