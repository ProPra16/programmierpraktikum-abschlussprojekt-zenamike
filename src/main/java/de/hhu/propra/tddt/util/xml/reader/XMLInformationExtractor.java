package de.hhu.propra.tddt.util.xml.reader;

import java.util.ArrayList;

/**
 * Created by Nadine on 07.07.16.
 */
public class XMLInformationExtractor {

    private String name;
    private String description;

    private ArrayList<String> classList;
    private ArrayList<String> testList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<String> classList) {
        this.classList = classList;
    }

    public ArrayList<String> getTestList() {
        return testList;
    }

    public void setTestList(ArrayList<String> testList) {
        this.testList = testList;
    }
}
