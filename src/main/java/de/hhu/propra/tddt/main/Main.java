package de.hhu.propra.tddt.main;

import de.hhu.propra.tddt.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Nadine on 03.07.16.
 */
public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception{
        new MainController(window);
    }
}