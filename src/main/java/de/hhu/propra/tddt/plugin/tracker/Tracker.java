package de.hhu.propra.tddt.plugin.tracker;

import de.hhu.propra.tddt.plugin.Plugin;
import de.hhu.propra.tddt.plugin.PluginManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by zeljko On 04.07.2016
 */
public class Tracker extends Application implements Plugin {

    PluginManager pluginManager;
    Scene scene;
    TrackerController trackerController;


    //method from the interface : plugin
    @Override
    public void start() {
        //nothing needed to do on startup,
        //
    }


    //method from the Application
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(scene);
        trackerController.setDurationLabel();
        trackerController.setErrorsFromList();
        stage.show();
    }

    @Override
    public void stop() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Tracker.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
            trackerController = fxmlLoader.getController();
            main(new String[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPluginManager(PluginManager pluginManager) {
        if(pluginManager == null) throw new IllegalArgumentException();
        this.pluginManager = pluginManager;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
