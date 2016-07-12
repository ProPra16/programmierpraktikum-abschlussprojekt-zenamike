package de.hhu.propra.tddt.util.xml.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by Nadine on 10.07.16.
 */

/**
 *  class: just reads all the information in an xml-file and saves
 *  them in a string. nothing more nothing less.
 */
public class XMLReader {

    private static String xmlToString;

    /**
     * Method: Constructor
     * @param path, templateOrNot
     * @throws IOException
     */

    public XMLReader(String path,boolean isTemplate) throws IOException, ParserConfigurationException, SAXException {
        if(isTemplate){
            this.xmlToString = readTemplateXMLFile(path);
        }
        else{
            this.xmlToString = readSomeXMLFile(path);
        }

        /**if(templateOrNot.equals("template"))
            this.xmlToString = readTemplateXMLFile(path);
        else{
            this.xmlToString = readSomeXMLFile(path);
        }*/
    }

    /**
     * Method: readSomeXMLFile
     * Task: method creates a reader for a given file and reads the content of said file
     * using a StringBuilder and BufferedReader, return the whole content including tags
     * Use: to read a xml file which structure is unknown or not compatible with the writer
     * structur
     * @param path
     * @return a string that contains all the information in the file
     * @throws IOException
     */
    private String readSomeXMLFile(String path) throws IOException {

        File xmlFile = new File(path);

        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();

        while(line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }

        xmlToString = stringBuilder.toString();

        return xmlToString;
    }

    /**
     * Method: readTemplateXMLFile
     * Task: method reads the content of a template xml file, which need to have the structure of
     * a xml file written by XML-Writer namely <settings></settings> <setting>CONTENT</setting>
     * Use: when you are sure you have a xml file that has the same structure as the a xml-Writer
     * written file.
     * @param pathName
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */

    private static String readTemplateXMLFile(String pathName)
            throws ParserConfigurationException, IOException, SAXException {

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        String contentOfFile = null;

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(pathName);

        Element rootElement = document.getDocumentElement();

        NodeList nodeList = rootElement.getElementsByTagName("setting");

        if(nodeList != null && nodeList.getLength() > 0){
            Element element1 = (Element) nodeList.item(0);
            contentOfFile = element1.getFirstChild().getNodeValue();
        }

        return contentOfFile;
    }

    public static String getXmlToString() {
        return xmlToString;
    }


    /**
     *
     * IGNORE: just for testing issues.
     */
    /**
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
     new XMLReader("src/Main/ExampleWriter.xml", false);
     System.out.println(getXmlToString());
     }*/

}
