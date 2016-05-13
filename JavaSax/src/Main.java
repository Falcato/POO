import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Main extends DefaultHandler{

	public static void main(String[] args) {
	
		SAXParserFactory fact = SAXParserFactory.newInstance();
		
		try{
			SAXParser saxParser = fact.newSAXParser();
			DefaultHandler handler = new Main();
			saxParser.parse(new File(args[0]), handler);
		}catch(IOException e){
			System.err.println("IO error");
		}catch(SAXException e){
			System.err.println("Parser error");
		}catch(ParserConfigurationException e){
			System.err.println("Parser configuration error");
		}

	}

}
