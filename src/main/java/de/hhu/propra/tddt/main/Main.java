package de.hhu.propra.tddt.main;

import de.hhu.propra.tddt.compiler.CompilerManager;
import de.hhu.propra.tddt.cycle.CycleManager;
import de.hhu.propra.tddt.informationcore.InformationCore;
import de.hhu.propra.tddt.settings.SettingsManager;
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

        InformationCore.informationCore().setSettingsManager(new SettingsManager());
        InformationCore.informationCore().setCycleManager(new CycleManager());
        InformationCore.informationCore().setCompileManager(new CompilerManager());

        launch(args);
    }



    @Override
    public void start(Stage window) throws Exception{
        window.setOnCloseRequest(e-> System.exit(0));
        new MainControlClass(window);
    }
}
