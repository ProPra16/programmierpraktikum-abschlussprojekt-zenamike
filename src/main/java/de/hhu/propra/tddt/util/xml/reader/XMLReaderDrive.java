package de.hhu.propra.tddt.util.xml.reader;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;


/**
 * Created by Nadine on 04.07.16.
 */
public class XMLReaderDrive {

    public static void main(String[] args) throws IOException, SAXException {
        XMLReader readFile = XMLReaderFactory.createXMLReader();
        readFile.setContentHandler(new XMLReaderHandler());
        readFile.parse("ExerciseRoman.xml");
    }
}
