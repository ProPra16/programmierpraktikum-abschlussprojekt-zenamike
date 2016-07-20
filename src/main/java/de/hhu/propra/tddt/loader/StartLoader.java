package de.hhu.propra.tddt.loader;

/**
 * Created by MichaelLiske on 09.07.16 at 19:36.
 */

import de.hhu.propra.tddt.controller.StartController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 *  Class: load the fxml file information and set the stage to the
 *  current scene (namely starting)
 */
public class StartLoader {


    protected Scene start;
    protected static Stage window;
    protected StartController controller;

    /**
     * Method: StartController
     * Task: sets this classes scene to the Main.java scene
     * @param window
     */
    public StartLoader(Stage window) throws IOException {
        this.window = window;
        loadFXMLInformation();
        window.setScene(start);
        window.show();
    }

    /**
     * Method: loadFXMLInformation
     * Task: loads the needed FXML information from a given file and builds a
     * scene based on these.
     * @throws IOException
     */
    public void loadFXMLInformation() throws IOException{
        URL fxml = getClass().getResource("/fxml/StartController.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxml);

        start = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
    }

    public Scene scene(){
        return start;
    }

    public static Stage getWindow(){ return window; }

    public StartController controller() throws IOException{
        loadFXMLInformation();
        return controller;
    }



}
