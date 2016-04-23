package lab8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Counter {

	public static void main(String[] args) throws IOException {
		
		int i;
		Reader in = new FileReader("test.in");
		CountCharReader cin = new CountCharReader(in, 'a');
		while((i = cin.read()) != -1);
		
		System.out.println(cin.charCnt);
		
		cin.close();
		
		Writer out = new FileWriter("test.in", true);
		CountCharWriter cout = new CountCharWriter(out, 'a');
		cout.write("ola o meu nome e tomas");
		//cout.write('a');
		//cout.write('a');
		
		System.out.println(cout.charCnt);
	
		cout.close();
	}
}
