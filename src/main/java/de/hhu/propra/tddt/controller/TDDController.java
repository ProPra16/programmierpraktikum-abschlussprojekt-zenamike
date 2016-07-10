package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by MichaelLiske on 09.07.16
 */

/**
 *  Class: handles the scenes' buttons (namely back and compile button)
 */

/** TODO: reading the text areas, setting areas to immutable one at a time,
 *  TODO: adding the clock(label), adding the phase(label)
 *  TODO: adding code and test code, changing the compile and back button
 *  TODO: depending on phase
 */
public class TDDController {

    @FXML private Button Back;
    @FXML private Button Compile;

    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        new StartLoader(TDDLoader.getWindow());
        System.out.println("Back");
    }
    @FXML
    public void handleCompileButton(ActionEvent actionEvent) {
        System.out.println("Compile");
    }
}

