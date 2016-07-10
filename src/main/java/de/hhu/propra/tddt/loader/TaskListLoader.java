package de.hhu.propra.tddt.loader;

import de.hhu.propra.tddt.controller.TaskListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Nadine on 09.07.16
 */

/**
 *  Class: load the fxml file information and set the stage to the
 *  current scene (namely taskList)
 */

public class TaskListLoader {


    protected Scene taskList;
    protected static Stage window;
    protected TaskListController controller;

    /**
     * Method: StartController
     * Task: sets this classes scene to the Main.java scene
     * @param window
     */
    public TaskListLoader(Stage window) throws IOException {
        this.window = window;
        loadFXMLInformation();
        window.setScene(taskList);
        window.show();
    }

    /**
     * Method: loadFXMLInformation
     * Task: loads the needed FXML information from a given file and builds a
     * scene based on these.
     * @throws IOException
     */
    public void loadFXMLInformation() throws IOException{
        URL fxml = getClass().getResource("/fxml/TaskController.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxml);

        taskList = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
    }

    public Scene scene(){
        return taskList;
    }

    public static Stage getWindow(){ return window; }

    public TaskListController controller() throws IOException{
        loadFXMLInformation();
        return controller;
    }



}