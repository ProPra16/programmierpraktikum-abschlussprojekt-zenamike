package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;
import de.hhu.propra.tddt.loader.TaskListLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static de.hhu.propra.tddt.controller.TaskListController.classCode;
import static de.hhu.propra.tddt.controller.TaskListController.testCode;

/**
 * Created by MichaelLiske on 09.07.16
 */

/**
 *  Class: handles the scenes' buttons (namely setting, tasklist and
 *  starting button)
 */

public class StartController{
    @FXML private Button TaskList;
    @FXML private Button TDDCycle;


    @FXML
    public void handleTaskButton(ActionEvent actionEvent) throws IOException {
        new TaskListLoader(StartLoader.getWindow());
        System.out.println("Settings+Tasks");
    }
    @FXML
    public void handleStartButton(ActionEvent actionEvent) throws IOException {
        new TDDLoader(StartLoader.getWindow());
        TDDLoader.controller.setCode(classCode);
        TDDLoader.controller.setTest(testCode);

        System.out.println("Start");
    }
}