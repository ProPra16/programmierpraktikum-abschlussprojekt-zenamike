package de.hhu.propra.tddt.util.xml.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nadine on 10.07.16.
 */
public class XMLCatalogReader {

    private static Document dom;
    private static ArrayList<Exercise> ExerciseList;


    /**
     *  Method: parseXMLFile
     *  Task: takes a given xml-file and parses it using a DocumentBuilder.
     *  TODO: the path needs to be adjusted to our project
     */

    private static void parseXMLFile(String pathName){

        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ExerciseList = new ArrayList<>();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse(pathName);
            parseDocument();


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method: parseDocument
     * Task: get called after the parseXMLFile method and creates a list with all the exercises in the xml-File.
     * TODO: Maybe we can create the catalog in a more dynamic way, which can be added to using this reader and
     * TODO: the ExerciseList which is created in this class.
     */

    private static void parseDocument(){
        //get the root element

        Element docEle = dom.getDocumentElement();

        //get a nodelist of element

        NodeList nodeList = docEle.getElementsByTagName("exercise");

        if(nodeList != null && nodeList.getLength() != 0){
            for(int i = 0; i < nodeList.getLength();i++){

                //get the exercise elements
                Element element = (Element) nodeList.item(i);

                //get the exercise object
                Exercise exercise = getExercise(element);

                //add the exercise to the exercise list
                ExerciseList.add(exercise);
            }
        }

    }

    /**
     * Method: getExercise
     * Task: the method reads exactly one exercise from the exerciseList-file (needs to be adjusted!), and loads
     * all the necessary information (name,description,list of classes, list of tasks).
     * @param element
     * @return the current exercise in the exerciseList-file
     */

    private static Exercise getExercise(Element element){

        //for each <exercise> element get test or String values of
        //name, description, classes, tests

        String name = getTextValue(element, "name");
        String description = getTextValue(element, "description");

        ArrayList<String> classes = getListOfTextValues(element, "classes", "class");
        ArrayList<String> tests = getListOfTextValues(element, "tests","test");

        //Create a exercise with values read from the xml file

        Exercise currentExercise = new Exercise(name,description,classes,tests);

        return currentExercise;
    }

    /**
     * Method: getTextValue
     * Task: gets the content from one tag (name,description)
     * @param element
     * @param tagName
     * @return
     */

    private static String getTextValue(Element element, String tagName){
        String textValue = null;
        NodeList nodeList = element.getElementsByTagName(tagName);
        if(nodeList != null && nodeList.getLength() > 0){
            Element element1 = (Element) nodeList.item(0);
            textValue = element1.getFirstChild().getNodeValue();
        }

        return textValue;
    }

    /**
     * Method: getListOfTextValues
     * Task: equvalent method of getTextValue for tag contents of classes and tests.
     * @param element
     * @param tagName
     * @param subTag
     * @return
     */

    private static ArrayList<String> getListOfTextValues(Element element, String tagName,String subTag){
        ArrayList<String> currentList = new ArrayList<>();
        String textValue;
        NodeList nodeList = element.getElementsByTagName(tagName);
        if(nodeList != null && nodeList.getLength()>0){
            Element element1 = (Element) nodeList.item(0);
            textValue = getTextValue(element, subTag);
            currentList.add(textValue);
        }
        return currentList;
    }

    /**
     * Method: printList
     * Task: prints the information from the given xml file, which are saved in the ExerciseList, a list
     * consisting of Exercise-Objects containing the information.
     */

    public static void printList(){
        System.out.println("Anzahl der Aufgaben: '"+ ExerciseList.size()+ "'.");

        for(int i = 0; i < ExerciseList.size(); i++){
            System.out.println(ExerciseList.get(i).toString());
        }
    }

    /**
     * Method: getExerciseList
     * Task: returns the ExerciseList containing all the information from the xml-file.
     * @return the ExerciseList
     */
    public static ArrayList<Exercise> getExerciseList() {
        return ExerciseList;
    }

    /**
     *
     * IGNORE: just for testing issues.
     */
    /*public static void main(String[] args){
        parseXMLFile("src/Main/ExerciseRoman.xml");
        printList();

    }*/
}














