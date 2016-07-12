package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.cycle.Cycle;
import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.loader.StartLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
    @FXML private TextArea codeArea;
    @FXML private TextArea testArea;
    private String code;
    private String test;

    @FXML private CycleEnum phase = CycleEnum.TEST;
    Cycle cycle = new Cycle();
    @FXML private Label PhaseLabel;
    @FXML private Label Time;
    @FXML private TextArea Test;
    @FXML private TextArea Code;

    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {

        if(phase.equals(CycleEnum.TEST))
        {
            new StartLoader(StartLoader.getWindow());

        }
        if(phase.equals(CycleEnum.CODE))
        {


        }
        if(phase.equals(CycleEnum.REFACTOR))
        {


        }
        System.out.println("Back");
    }
    @FXML
    public void handleCompileButton(ActionEvent actionEvent) {
        System.out.println("Compile");

        if(phase.equals(CycleEnum.TEST))
        {
            test = testArea.getText();
            phase = cycle.testingPhase(test,phase);

            if(phase.equals(CycleEnum.CODE)) PhaseLabel.setText("CODE");
        }
        if(phase.equals(CycleEnum.CODE))
        {
            test = testArea.getText();
            code = codeArea.getText();
            phase = cycle.codingPhase(code,test,phase);
            if(phase.equals(CycleEnum.REFACTOR)) PhaseLabel.setText("REFACTOR");

        }
        if(phase.equals(CycleEnum.REFACTOR))
        {
            test = testArea.getText();
            code = codeArea.getText();
            phase = cycle.refactoringPhase(code,test,phase);

        }

    }

    public TextArea getCodeArea(){
        return codeArea;
    }

    public TextArea getTestArea() { return testArea; }
}

