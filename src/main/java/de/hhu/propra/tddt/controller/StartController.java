package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.loader.SettingsLoader;
import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;
import de.hhu.propra.tddt.loader.TaskListLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by MichaelLiske on 09.07.16
 */

/**
 *  Class: handles the scenes' buttons (namely setting, tasklist and
 *  start button)
 */

public class StartController{
    @FXML private Button Setting;
    @FXML private Button TaskList;
    @FXML private Button TDDCycle;


    @FXML
    public void handleSettingsButton(ActionEvent actionEvent) throws IOException {
        new SettingsLoader(StartLoader.getWindow());
        System.out.println("Settings");
    }
    @FXML
    public void handleTaskButton(ActionEvent actionEvent) throws IOException {
        new TaskListLoader(StartLoader.getWindow());
        System.out.println("Task");
    }
    @FXML
    public void handleStartButton(ActionEvent actionEvent) throws IOException {
        new TDDLoader(StartLoader.getWindow());
        System.out.println("Start");
    }
}