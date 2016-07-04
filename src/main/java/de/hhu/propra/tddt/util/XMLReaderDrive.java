package main.java.de.hhu.propra.tddt.util;

import main.java.de.hhu.propra.tddt.util.XMLReaderHandler;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * Created by Nadine on 04.07.16.
 */
public class XMLReaderDrive {

    public static void main(String[] args) throws IOException,SAXException{
        XMLReader readFile = XMLReaderFactory.createXMLReader();
        readFile.setContentHandler(new XMLReaderHandler());
        readFile.parse("ExerciseRoman.xml");
    }
}
