package de.hhu.propra.tddt.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Nadine on 03.07.16.
 */
public class StartController extends CustomController{

    private MainController mainController;

    @FXML protected void handleSettingsButton(ActionEvent eventSetting){ System.out.println("SettingsButton pressed!");}

    @FXML protected void handleTaskButton(ActionEvent eventTask){
        System.out.println("TaskButton pressed!");
    }

    @FXML protected void handleStartButton(ActionEvent eventStart){
        System.out.println("StartButton pressed!");
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
