package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.loader.SettingsLoader;
import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.io.IOException;

/**
 * Created by Nadine on 09.07.16
 */

/**
 *  Class: handles the scenes' buttons (namely back and startTDD button),
 *  handling the listener information and activating the plugins (Checkboxes)
 */

public class SettingsController {

    @FXML private CheckBox BabySteps;
    @FXML private CheckBox Tracking;
    @FXML private Button Back;
    @FXML private Button TDDCycle;


    @FXML
    public void handleBabyStepsBox(ActionEvent actionEvent) {
        System.out.println("BabySteps");
    }
    @FXML
    public void handleTrackingBox(ActionEvent actionEvent) {
        System.out.println("Tracking");
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        new StartLoader(SettingsLoader.getWindow());
        System.out.println("Back");
    }
    @FXML
    public void handleStartTDDButton(ActionEvent actionEvent) throws IOException {
        new TDDLoader(SettingsLoader.getWindow());
        System.out.println("Start");
    }
}