package de.hhu.propra.tddt.controllerloader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Nadine on 03.07.16.
 */
public class StartControllerLoader extends ControllerLoader{

    private static Scene startMenue;
    private static StartMenueController startMenueController;
    private static MainController mainController;

    public StartControllerLoader(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public CustomController controller() throws IOException{
        loadController();
        return startMenueController;
    }

    @Override
    public Scene scene(){
        return startMenue;
    }

    private void loadController() throws IOException{
        URL fxml = getClass().getResource("/rsc/MainController.fmxl");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxml);

        startMenue = new Scene(fxmlLoader.load());                      //?
        startMenueController = fxmlLoader.getController();
        startMenueController.setMainController(mainController);
    }



}
