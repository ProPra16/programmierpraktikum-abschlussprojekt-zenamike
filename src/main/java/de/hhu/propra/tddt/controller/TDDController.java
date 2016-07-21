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
                stopAndStartPlugins();
                compiliert = false;
                compilierenTest();
                CompileResultsPrüfenMitEinemFail();
                ResultsAusgeben();
                if(prüfenInTEST()){
                    InformationCore.informationCore().getCycleManager().nextPhase();
                }
                testPhaseCycle();
                PhaseLabel.setText(CurrentPhase());
                setAreaIfBabySteps();
                break;


            case CODE:
                stopAndStartPlugins();
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
                setAreaIfBabySteps();
                break;

            case REFACTOR:
                stopAndStartPlugins();
                System.out.println("Compiling in REFACTOR");

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
                prüfenInRefactor();
                testPhaseCycle();
                PhaseLabel.setText(CurrentPhase());
                setAreaIfBabySteps();
                break;
        }


    }

    private boolean CompileResultsPrüfenMitEinemFail(){
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
    private void CompileResultsPrüfenVonCode(){
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
    private void CompileResultsPrüfenVonTest(){
        try {
            if (InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("0")) {
                compiliert = true;
                System.out.println("Test compiliert");
            }
            if(!InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("0")) {
                compiliert = false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Test gibt NULL aus");
        }
    }


    //Sets the TextAreas editable
    private boolean prüfenInTEST(){
        if (InformationCore.informationCore().getCycleManager().getCurrentPhase() == CycleEnum.CODE) {
            codeArea.setEditable(true);
            testArea.setEditable(false);
            return true;
        }
        return false;
    }
    private boolean prüfenInCODE(){
        if (InformationCore.informationCore().getCycleManager().getCurrentPhase() == CycleEnum.REFACTOR) {
            codeArea.setEditable(true);
            testArea.setEditable(true);
            return true;
        }
        return false;
    }
    private boolean prüfenInRefactor(){
        if (InformationCore.informationCore().getCycleManager().getCurrentPhase() == CycleEnum.TEST) {
            codeArea.setEditable(false);
            testArea.setEditable(true);

            return true;
        }
        return false;
    }

    //Compiles
    private void compilierenTest(){
        InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
    }
    private void compilierenCode(){
        InformationCore.informationCore().getCompileManager().compileCode(InformationCore.informationCore().getCodeManager().getText());
    }

    /**
     * gives you the Compilation Result
     */
    private void ResultsAusgeben(){
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

    /**
     * switches the Phase one back
     */
    private void einePhaseZurück(){
        InformationCore.informationCore().getCycleManager().lastPhase();
    }

    /**
     * stop the Plugins and start them again
     */
    private void stopAndStartPlugins(){
        PluginLoader.pluginLoader().stopAllPlugins();
        PluginLoader.pluginLoader().startAllPlugins();
    }

    /**
     * gives you the currentPhase
     * @return as String
     */
    private String CurrentPhase(){
        return InformationCore.informationCore().getCycleManager().getCurrentPhase().toString();
    }

    /**
     * chatch the exeption @ if Phase=TEST + Back button pressed
     */
    private void stopPluginCatchExeption(){
        try {
            PluginLoader.pluginLoader().stopAllPlugins();
        }catch (IllegalStateException e){
            System.out.println("Plugins schon gestoppt");
        }
    }

    /**
     * Prints the CurrentPhase
     */
    private void testPhaseCycle(){
        System.out.println("++++++++++++++");
        System.out.println(CurrentPhase());
    }


    /**
     * resets the text for Babysteps
     */
    private void setAreaIfBabySteps(){
        testArea.setText(InformationCore.informationCore().getTestManager().getText());
        codeArea.setText(InformationCore.informationCore().getTestManager().getText());
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