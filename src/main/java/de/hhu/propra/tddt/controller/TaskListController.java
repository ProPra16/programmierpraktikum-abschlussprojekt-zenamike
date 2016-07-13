package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;
import de.hhu.propra.tddt.loader.TaskListLoader;
import de.hhu.propra.tddt.util.manager.CatalogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Nadine on 09.07.16
 */

/**
 *  Class: handles the scenes' buttons (namely back and startTDD button)
 */

public class TaskListController {

    @FXML public Button Go;
    @FXML private Button Back;
    @FXML private Button TDDCycle;
    @FXML private ChoiceBox<String> Tasks;
    @FXML private TextArea Discription;
    @FXML private TextField pathField;
    @FXML String path =  "";
    static String classCode;
    static String testCode;
    CatalogManager manager;

    public static String getClassCode() {
        return classCode;
    }

    public static String getTestCode() {
        return testCode;
    }

    /**
     * TODO: Handle the Task and TextArea and
     * TODO: make a Listener between them
     * TODO: give the matching Code and Test to TDDController
     * @param
     * @throws IOException
     */

    @FXML
    public String getPath(){
        path = pathField.getText();
        return path;
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        new StartLoader(TaskListLoader.getWindow());
        System.out.println("Back");
    }
    @FXML
    public void handleStartTDDButton(ActionEvent actionEvent) throws IOException {
        new TDDLoader(TaskListLoader.getWindow());
        classCode = manager.getCurrentChosenOne().getClasses();
        testCode = manager.getCurrentChosenOne().getTests();
        System.out.println(classCode);
        System.out.println(testCode);


            /*TDDController controller = new TDDController();
            controller.setCode(classCode);
            controller.setTest(testCode);
*/
        System.out.println("Start");
    }
    @FXML
    public void handleGoButton(ActionEvent actionEvent) throws IOException{
        path = getPath();
        System.out.println(path);
        manager = new CatalogManager(path);
        ArrayList<String> names = manager.getExerciseNames();

        for(int i = 0; i < names.size(); i++){
            String current = names.get(i);
            Tasks.getItems().add(current);
        }

        Tasks.getSelectionModel().selectedItemProperty().addListener((observer,oldValue,newValue) ->{
            String description = manager.compareChoiceWithCatalog(newValue);
            Discription.setText(description);
        });
    }
}

