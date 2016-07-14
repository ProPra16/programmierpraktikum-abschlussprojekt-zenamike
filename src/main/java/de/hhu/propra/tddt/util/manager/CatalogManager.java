package de.hhu.propra.tddt.util.manager;

/**
 * Created by Nadine on 13.07.16.
 */

import de.hhu.propra.tddt.util.xml.reader.Exercise;
import de.hhu.propra.tddt.util.xml.reader.XMLCatalogReader;

import java.util.ArrayList;

/**
 * class: takes results of XMLCatalogReader and uses the results
 */
public class CatalogManager {


    static XMLCatalogReader xmlInformation;
    static ArrayList<Exercise> exerciseCatalog;
    static ArrayList<String> exerciseNames;
    static Exercise currentChosenOne;

    public CatalogManager(String path){
        useXMLReader(path);
        readNames();
    }

    private void useXMLReader(String path){

        //System.out.println("CheckPoint CatalogManager");

        xmlInformation = new XMLCatalogReader(path);
        exerciseCatalog = xmlInformation.getExerciseList();
        exerciseNames = new ArrayList<>();

    }

    private ArrayList<String> readNames(){
        for(int i = 0; i < exerciseCatalog.size(); i++){
            String current = exerciseCatalog.get(i).getName();
            exerciseNames.add(current);
        }
        return exerciseNames;
    }

    // Methode nimmt string entgegen und gleicht alle exercises ab

    public ArrayList<String> getExerciseNames() {
        return exerciseNames;
    }

    public String compareChoiceWithCatalog(String choice){
        for(int i = 0; i < exerciseCatalog.size(); i++){
            if(choice.equals(exerciseCatalog.get(i).getName())){
                this.currentChosenOne = exerciseCatalog.get(i);
                return exerciseCatalog.get(i).getDescription();
            }
        }
        return "";
    }

    public static Exercise getCurrentChosenOne() {
        return currentChosenOne;
    }


    /**public static void main(String[] args) {
        CatalogManager manager = new CatalogManager("src/Main/ExerciseRoman.xml");
        ArrayList<String> names = manager.getExerciseNames();


        for(int i = 0; i < names.size(); i++){
            String current = names.get(i);
            System.out.println(current);
        }

        System.out.println(manager.compareChoiceWithCatalog("FucksGivenCounter"));

    }*/
}
