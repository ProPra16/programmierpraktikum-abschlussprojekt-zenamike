package de.hhu.propra.tddt.main;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Created by Nadine on 03.07.16.
 */

/**
 *  class: starts the application by instanziating a MainControlClass object
 */

public class Main extends Application{

    Stage window;

    public static void main(String[] args){
        /*
        InformationCore.informationCore().setSettingsManager(new SettingsManager());
        InformationCore.informationCore().setCycleManager(new CycleManager());
        InformationCore.informationCore().
        */
        launch(args);
    }



    @Override
    public void start(Stage window) throws Exception{
        new MainControlClass(window);
    }
}
