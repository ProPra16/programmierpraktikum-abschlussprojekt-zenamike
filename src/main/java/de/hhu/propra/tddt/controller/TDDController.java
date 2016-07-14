package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.contentmanager.TextManager;
import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.informationcore.InformationCore;
import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.plugin.PluginLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
public class TDDController implements Initializable {

    private String code = TaskListController.classCode;
    private String test = TaskListController.testCode;

    @FXML private Button Back;
    @FXML private Button Compile;
    @FXML public TextArea codeArea;
    @FXML public TextArea testArea;


    public  void setCode(String codeInput) {
        code = codeInput;
        //System.out.println(code + " Setter für Code");
        codeArea.setText(code);
    }

    public  void setTest(String testInput) {
        test = testInput;
        //System.out.println(test + " Setter für Tests ");
        testArea.setText(test);
    }



    private CycleEnum phase = CycleEnum.TEST;
    @FXML private Label PhaseLabel;


    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {

        if(phase.equals(CycleEnum.TEST))
        {
            new StartLoader(StartLoader.getWindow());
            PluginLoader.pluginLoader().stopAllPlugins();
        }
        if(phase.equals(CycleEnum.CODE))
        {
            phase = CycleEnum.TEST;
            PhaseLabel.setText("TEST");
            testArea.setEditable(true);
            codeArea.setEditable(false);
        }
        if(phase.equals(CycleEnum.REFACTOR))
        {
            phase = CycleEnum.CODE;
            PhaseLabel.setText("CODE");
            testArea.setEditable(false);
            codeArea.setEditable(true);
        }
        System.out.println("Back");
    }


    @FXML
    public void handleCompileButton(ActionEvent actionEvent) {
        System.out.println("Compile");

        if(phase.equals(CycleEnum.TEST))
        {

            InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
            System.out.println("ERROR in TEST: "+InformationCore.informationCore().getCompileManager().getCompileResultList());
            phase = InformationCore.informationCore().getCycleManager().getCurrentPhase();
            System.out.println(InformationCore.informationCore().getCodeManager().getText()  + " blablabla");
            if(phase.equals(CycleEnum.CODE)) {
                PhaseLabel.setText("CODE");
                codeArea.setEditable(true);
                testArea.setEditable(false);
            }
            return;
        }
        if(phase.equals(CycleEnum.CODE))
        {
            InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
            InformationCore.informationCore().getCompileManager().compileCode(InformationCore.informationCore().getCodeManager().getText());
            System.out.println("ERROR in CODE: " + InformationCore.informationCore().getCompileManager().getCompileResultList());

            phase = InformationCore.informationCore().getCycleManager().getCurrentPhase();
            if(phase.equals(CycleEnum.REFACTOR)) {
                PhaseLabel.setText("REFACTOR");
                testArea.setEditable(true);
                codeArea.setEditable(true);
            }
            return;
        }
        if(phase.equals(CycleEnum.REFACTOR))
        {

            InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
            InformationCore.informationCore().getCompileManager().compileCode(InformationCore.informationCore().getCodeManager().getText());
            System.out.println("ERROR in REFACTOR: " + InformationCore.informationCore().getCompileManager().getCompileResultList());

            phase = InformationCore.informationCore().getCycleManager().getCurrentPhase();
            if(phase.equals(CycleEnum.TEST)) {
                PhaseLabel.setText("TEST");
                testArea.setEditable(true);
                codeArea.setEditable(true);
            }
            return;

        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InformationCore.informationCore().setCodeManager(new TextManager(codeArea));
        InformationCore.informationCore().setTestManager(new TextManager(testArea));
        codeArea.setEditable(false);
        PhaseLabel.setText("TEST");

    }
}

