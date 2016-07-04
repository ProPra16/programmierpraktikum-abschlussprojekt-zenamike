package de.hhu.propra.tddt.util.xml.reader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Nadine On 23.06.2016
 */
public class XMLReaderHandler extends DefaultHandler {


    public void startDocument() throws SAXException {
        System.out.println("Starting the parsing process.");
    }

    public void endDocument() throws SAXException {
        System.out.println("Ending the parsing process.");
    }

    public void characters(char[] chars, int begin, int length) throws SAXException {
        for (int i = begin; i < (begin + length); i++) {
            System.out.print(chars[i]);
        }
    }

    public void startElement(String s, String s1, String s2, Attributes attributes) throws SAXException {
        System.out.print("<" + s2 + ">");

    }

    public void endElement(String s, String s1, String s2) throws SAXException {
        System.out.println("</" + s2 + ">");
    }

}
