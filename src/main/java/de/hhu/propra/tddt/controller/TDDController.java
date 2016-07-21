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
            }
            case CODE: {
                einePhaseZurück();
                PhaseLabel.setText(CurrentPhase());
                testArea.setEditable(true);
                codeArea.setEditable(false);
                stopAndStartPlugins();
            }
            case REFACTOR: {
                einePhaseZurück();
                PhaseLabel.setText(CurrentPhase());
                testArea.setEditable(false);
                codeArea.setEditable(true);
                stopAndStartPlugins();
            }
            System.out.println("Back");
        }
    }

    @FXML
    public void handleCompileButton(ActionEvent actionEvent) {

        System.out.println("\n\nCompilation Nr: " + InformationCore.informationCore().getCompileManager().getCompilationNumber()+"\n");
        switch (InformationCore.informationCore().getCycleManager().getCurrentPhase()) {
            case TEST:
                compiliert = false;
                compilierenTest();
                CompileResultsPrüfenMitEinemFail();
                ResultsAusgeben();

                if(prüfenInTEST()){
                    InformationCore.informationCore().getCycleManager().nextPhase();
                }
                testPhaseCycle();
                PhaseLabel.setText(CurrentPhase());
                break;


            case CODE:

                //zum überprüfen ob compiliert
                compiliert = false;

                // Code Compilieren
                compilierenCode();
                CompileResultsPrüfenVonCode();


                // Test Compilieren
                compilierenTest();
                CompileResultsPrüfenVonTest();
                ResultsAusgeben();

                if(compiliert){
                    InformationCore.informationCore().getCycleManager().nextPhase();
                }
                prüfenInCODE();
                testPhaseCycle();
                PhaseLabel.setText(CurrentPhase());

                break;

            case REFACTOR:

                System.out.println("Compiling in REFACTOR");

                //zum überprüfen ob compiliert
                compiliert = false;

                // Code Compilieren
                compilierenCode();
                CompileResultsPrüfenVonCode();
                ResultsAusgeben();


                // Test Compilieren
                compilierenTest();
                CompileResultsPrüfenVonTest();
                if(compiliert){
                    InformationCore.informationCore().getCycleManager().nextPhase();
                }
                prüfenInRefactor();
                testPhaseCycle();
                PhaseLabel.setText(CurrentPhase());
                break;
        }


    }

    public boolean CompileResultsPrüfenMitEinemFail(){
        try {
            System.out.println("Test compiliert");
            if (InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("1")){
                compiliert = true;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Test gibt NULL aus");
        }
        return compiliert;
    }
    public void CompileResultsPrüfenVonCode(){
        try {
            if (InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(2).equals("0")) {
                compiliert = true;
                System.out.println("Code compiliert");
            } else {
                compiliert = false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Test gibt NULL aus");
        }
    }
    public void CompileResultsPrüfenVonTest(){
        try {
            if (InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("0")) {
                compiliert = true;
                System.out.println("Test compiliert");
            } else {
                compiliert = false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Test gibt NULL aus");
        }
    }



    public boolean prüfenInTEST(){
        if (InformationCore.informationCore().getCycleManager().getCurrentPhase() == CycleEnum.CODE) {
            stopAndStartPlugins();
            codeArea.setEditable(true);
            testArea.setEditable(false);
            return true;
        }
        return false;
    }
    public boolean prüfenInCODE(){
        if (InformationCore.informationCore().getCycleManager().getCurrentPhase() == CycleEnum.REFACTOR) {
            stopAndStartPlugins();
            codeArea.setEditable(true);
            testArea.setEditable(true);
            return true;
        }
        return false;
    }
    public boolean prüfenInRefactor(){
        if (InformationCore.informationCore().getCycleManager().getCurrentPhase() == CycleEnum.TEST) {
            stopAndStartPlugins();
            PhaseLabel.setText(CurrentPhase());
            codeArea.setEditable(false);
            testArea.setEditable(true);

            return true;
        }
        return false;
    }

    public void compilierenTest(){
        InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
    }
    public void compilierenCode(){
        InformationCore.informationCore().getCompileManager().compileCode(InformationCore.informationCore().getCodeManager().getText());
    }

    public void ResultsAusgeben(){
        System.out.println("CompilerResultList.get0");
        System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0));
        System.out.println();
        try{
            System.out.println("CompilerResultList.get1");
            System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(1));
        }catch (IndexOutOfBoundsException e){
            System.out.println("Noch nicht vorhanden");
        }
    }


    public void einePhaseZurück(){
        InformationCore.informationCore().getCycleManager().nextPhase();
        InformationCore.informationCore().getCycleManager().nextPhase();
    }

    public void stopAndStartPlugins(){
        PluginLoader.pluginLoader().stopAllPlugins();
        PluginLoader.pluginLoader().startAllPlugins();
    }

    public String CurrentPhase(){
        return InformationCore.informationCore().getCycleManager().getCurrentPhase().toString();
    }

    public void stopPluginCatchExeption(){
        try {
            PluginLoader.pluginLoader().stopAllPlugins();
        }catch (IllegalStateException e){
        }
    }

    public void testPhaseCycle(){
        System.out.println("++++++++++++++");
        System.out.println(CurrentPhase());
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        InformationCore.informationCore().setCodeManager(new TextManager(codeArea));
        InformationCore.informationCore().setTestManager(new TextManager(testArea));
        PluginLoader.pluginLoader().startAllPlugins();

        codeArea.setEditable(false);
        PhaseLabel.setText(CurrentPhase());

    }
}