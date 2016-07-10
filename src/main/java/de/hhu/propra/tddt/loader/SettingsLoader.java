package de.hhu.propra.tddt.loader;

import de.hhu.propra.tddt.controller.SettingsController;

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
 *  current scene (namely settings)
 */

public class SettingsLoader {


    protected Scene settings;
    protected static Stage window;
    protected SettingsController controller;

    /**
     * Method: StartController
     * Task: sets this classes scene to the Main.java scene
     * @param window
     */
    public SettingsLoader(Stage window) throws IOException {
        this.window = window;
        loadFXMLInformation();
        window.setScene(settings);
        window.show();
    }

    /**
     * Method: loadFXMLInformation
     * Task: loads the needed FXML information from a given file and builds a
     * scene based on these.
     * @throws IOException
     */
    public void loadFXMLInformation() throws IOException{
        URL fxml = getClass().getResource("/fxml/SettingsController.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxml);

        settings = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
    }

    public Scene scene(){
        return settings;
    }

    public static Stage getWindow(){ return window; }

    public SettingsController controller() throws IOException{
        loadFXMLInformation();
        return controller;
    }



}