<<<<<<< HEAD:src/main/java/de/hhu/propra/tddt/util/XMLReaderDrive.java
package main.java.de.hhu.propra.tddt.util;
=======
package de.hhu.propra.tddt.util.xml.reader;
>>>>>>> 84d46728da103ea132d4f46f15d84303f82c6970:src/main/java/de/hhu/propra/tddt/util/xml/reader/XMLReaderDrive.java

import main.java.de.hhu.propra.tddt.util.XMLReaderHandler;
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
