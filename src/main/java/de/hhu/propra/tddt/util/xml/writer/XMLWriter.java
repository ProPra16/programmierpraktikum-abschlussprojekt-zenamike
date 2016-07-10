package main.java.de.hhu.propra.tddt.util.xml.writer

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by Nadine on 10.07.16.
 */

/**
 *  class: this class simply takes a content string and a namePath string and creates/overwrites
 *  an xml-file with the information. All files have a root Settings and one element <setting></setting>.
 *  Content can be found in between those tags.
 */

public class XMLWriter {

    public XMLWriter(String content, String namePath) throws TransformerException, ParserConfigurationException {
        writeSomeXMLFile(content, namePath);
    }

    /**
     * method: writeSomeXMLFile
     * task: takes a content string ans a string with the filename/path and creates a xml file, all files
     * have a root element <settings></settings> and one tag <setting></setting>, the content can be found in
     * in between the setting tags.
     *
     * @param ContentForXMLFile
     * @param fileNameAndPlace
     * @throws ParserConfigurationException
     * @throws TransformerException
     */

    private static void writeSomeXMLFile(String ContentForXMLFile, String fileNameAndPlace)
            throws ParserConfigurationException,
            TransformerException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement("settings");
        document.appendChild(rootElement);

        Element setting = document.createElement("setting");
        setting.appendChild(document.createTextNode(ContentForXMLFile));
        rootElement.appendChild(setting);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileNameAndPlace)); //save in File
        //StreamResult result = new StreamResult(System.out);                 //stream on console (for testing)

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.transform(source, result);

        System.out.println("File saved.");

    }

    /**
     * method: main
     * task: just for testing issues.
     *
     * @param args
     * @throws TransformerException
     * @throws ParserConfigurationException
     */

    /**
     *
     * IGNORE: just for testing issues.
     */
    /**
     public static void main(String[] args) throws TransformerException, ParserConfigurationException {

     String content = "FOR THE LOLZ";
     String namePath = "src/Main/ExampleWriter.xml";
     new XMLWriter(content, namePath);
     }*/
}
