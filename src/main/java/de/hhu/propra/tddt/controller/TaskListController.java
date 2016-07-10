package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;
import de.hhu.propra.tddt.loader.TaskListLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


/**
 * Created by Nadine on 09.07.16
 */

/**
 *  Class: handles the scenes' buttons (namely back and startTDD button)
 */

public class TaskListController {

    @FXML private Button Back;
    @FXML private Button TDDCycle;

    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        new StartLoader(TaskListLoader.getWindow());
        System.out.println("Back");
    }
    @FXML
    public void handleStartTDDButton(ActionEvent actionEvent) throws IOException {
        new TDDLoader(TaskListLoader.getWindow());
        System.out.println("Start");
    }
}

