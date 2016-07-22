package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.compiler.CompilerManager;
import de.hhu.propra.tddt.contentmanager.TextManager;
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
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Created by MichaelLiske on 09.07.16
 */

/**
 * Class: handles the scenes' buttons (namely back and compile button)
 */

public class TDDController implements Initializable {

    private String code = TaskListController.classCode;
    private String test = TaskListController.testCode;

    @FXML
    private Button Back;
    @FXML
    private Button Compile;
    @FXML
    public TextArea codeArea;
    @FXML
    public TextArea testArea;
    boolean compiliert;

    public void setCode(String codeInput) {
        code = codeInput;
        codeArea.setText(code);
    }

    public void setTest(String testInput) {
        test = testInput;
        testArea.setText(test);
    }

    @FXML
    private Label PhaseLabel;

    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        switch (InformationCore.informationCore().getCycleManager().getCurrentPhase()) {
            case TEST: {
                new StartLoader(StartLoader.getWindow());
                stopPluginCatchExeption();
                break;
            }
            case CODE: {
                einePhaseZurueck();
                PhaseLabel.setText(CurrentPhase());
                uiUpdateForTest();
                break;
            }
            case REFACTOR: {
                einePhaseZurueck();
                PhaseLabel.setText(CurrentPhase());
                uiUpdateForCode();
                break;
            }
        }
    }

    @FXML
    public void handleCompileButton(ActionEvent actionEvent) {

        try {

            switch (InformationCore.informationCore().getCycleManager().getCurrentPhase()) {
                case TEST:
                    compiliert = false;
                    compilierenTest();
                    CompileResultsPruefenMitEinemFail();

                    if (!compiliert) {
                        uiUpdateForCode();
                        InformationCore.informationCore().getCycleManager().nextPhase();
                    }
                    PhaseLabel.setText(CurrentPhase());
                    break;

                case CODE:

                    // zum ueberpruefen ob compiliert
                    compiliert = false;

                    // Test Compilieren
                    compilierenTest();
                    CompileResultsPruefenVonTest();

                    if (compiliert) {
                        uiUpdateForRefactor();
                        InformationCore.informationCore().getCycleManager().nextPhase();
                    }
                    PhaseLabel.setText(CurrentPhase());
                    break;

                case REFACTOR:

                    // zum ueberpruefen ob compiliert
                    compiliert = false;

                    // Code Compilieren
                    compilierenCode();
                    CompileResultsPruefenVonTest();

                    if (compiliert) {
                        uiUpdateForTest();
                        InformationCore.informationCore().getCycleManager().nextPhase();
                    }
                    PhaseLabel.setText(CurrentPhase());
                    break;
            }

        }catch (IndexOutOfBoundsException e){}
    }
    public void CompileResultsPruefenMitEinemFail() {
        CompilerManager compileManager = InformationCore.informationCore().getCompileManager();
        LinkedList<LinkedList<String>> theList = compileManager.getCompileResultList();
        LinkedList<String> elementList = theList.get(0);
        if (elementList.get(0).isEmpty() || elementList.get(2).equals("1")) {
            compiliert = false;
        } else {
            compiliert = true;
        }
    }

    public void CompileResultsPruefenVonTest() {
        LinkedList<LinkedList<String>> compileResultList = InformationCore.informationCore().getCompileManager()
                .getCompileResultList();
        if (compileResultList.get(0).get(2)
                .equals("0")) {
            compiliert = true;
        } else {
            compiliert = false;
        }
    }

    public void uiUpdateForCode() {
        updateInformationForTextManager();
        stopAndStartPlugins();
        codeArea.setEditable(true);
        testArea.setEditable(false);
    }

    public void uiUpdateForRefactor() {
        updateInformationForTextManager();
        stopAndStartPlugins();
        codeArea.setEditable(true);
        testArea.setEditable(true);
    }

    public void uiUpdateForTest() {
        updateInformationForTextManager();
        stopAndStartPlugins();
        PhaseLabel.setText(CurrentPhase());
        codeArea.setEditable(false);
        testArea.setEditable(true);
    }

    public void compilierenTest() {
        String testCode = InformationCore.informationCore().getTestManager().getText();
        String code = InformationCore.informationCore().getCodeManager().getText();
        InformationCore.informationCore().getCompileManager().compileTest(code, testCode);
    }

    public void compilierenCode() {
        compilierenTest();
    }

    public void einePhaseZurueck() {
        InformationCore.informationCore().getCycleManager().nextPhase();
        InformationCore.informationCore().getCycleManager().nextPhase();
    }

    public void stopAndStartPlugins() {
        try {
            PluginLoader.pluginLoader().stopAllPlugins();
        } catch (Exception e) {
            //NO EXCEPTION FOR YOU
        }
        PluginLoader.pluginLoader().startAllPlugins();
    }

    public void updateInformationForTextManager() {
        InformationCore.informationCore().getTestManager().updatePhaseSave(testArea.getText());
        System.out.println("UPDATE INFORMATION FOR TEXTMANAGER");
        System.out.println(testArea.getText());

        InformationCore.informationCore().getCodeManager().updatePhaseSave(codeArea.getText());
        System.out.println(codeArea.getText());
    }

    public String CurrentPhase() {
        return InformationCore.informationCore().getCycleManager().getCurrentPhase().toString();
    }

    public void stopPluginCatchExeption() {
        try {
            PluginLoader.pluginLoader().stopAllPlugins();
        } catch (IllegalStateException e) {
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        InformationCore.informationCore().setCodeManager(new TextManager(codeArea));
        InformationCore.informationCore().setTestManager(new TextManager(testArea));

        try {
            uiUpdateForTest();
        } catch (Exception e) {

        }

        PhaseLabel.setText(CurrentPhase());

    }
}