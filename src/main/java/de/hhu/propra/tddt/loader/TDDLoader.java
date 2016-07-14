package de.hhu.propra.tddt.loader;

import de.hhu.propra.tddt.controller.TDDController;

import de.hhu.propra.tddt.controller.TaskListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by MichaelLiske on 09.07.16
 */

/**
 * class: load the fxml file information and set the stage to the
 *  current scene (namely tDD)
 */

public class TDDLoader {


    protected Scene tDD;
    protected static Stage window;
    static public TDDController controller;

    /**
     * Method: StartController
     * Task: sets this classes scene to the Main.java scene
     * @param window
     */
    public TDDLoader(Stage window) throws IOException {
        this.window = window;
        loadFXMLInformation();
        window.setScene(tDD);
        window.show();
    }

    /**
     * Method: loadFXMLInformation
     * Task: loads the needed FXML information from a given file and builds a
     * scene based on these.
     * @throws IOException
     */
    public void loadFXMLInformation() throws IOException{
        URL fxml = getClass().getResource("/fxml/TDDController.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxml);

        tDD = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();

    }

    public Scene scene(){
        return tDD;
    }

    public static Stage getWindow(){ return window; }

    public TDDController controller() throws IOException{
        loadFXMLInformation();
        //System.out.println("Controller erstellt.");
        controller.setCode(TaskListController.getClassCode());
        return controller;
    }



}