package main.java.de.hhu.propra.tddt.util.xml.reader
import java.util.ArrayList;

/**
 * Created by Nadine on 10.07.16.
 */

/**
 *  class: an object of this class saves all the information about one exercise
 *  in it (namely the name, the description, the list of classes and the list of tests).
 */

public class Exercise {

    String name;
    String description;

    ArrayList<String> classes;
    ArrayList<String> tests;

    public Exercise(String name, String description, ArrayList<String> classes, ArrayList<String> tests){

        this.name = name;
        this.description = description;
        this.classes = classes;
        this.tests = tests;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", classes=" + classes +
                ", tests=" + tests +
                '}';
    }
}
