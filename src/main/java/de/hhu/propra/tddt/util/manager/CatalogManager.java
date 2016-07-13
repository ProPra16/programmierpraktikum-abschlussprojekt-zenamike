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



    public static ArrayList<String> useXMLReader(String path){

        XMLCatalogReader xmlInformation = new XMLCatalogReader(path);
        ArrayList<Exercise> exerciseCatalog = xmlInformation.getExerciseList();
        ArrayList<String> exerciseNames = new ArrayList<>();

        for(int i = 0; i < exerciseCatalog.size(); i++){
            String current = exerciseCatalog.get(i).getName();
            exerciseNames.add(current);
        }
        return exerciseNames;
    }


    /**
     public static void main(String[] args) {
     ArrayList<String> bla = useXMLReader("src/Main/ExerciseRoman.xml");

     for(int i = 0; i < bla.size(); i++){
     String current = bla.get(i);
     System.out.println(current);
     }

     }*/
}
