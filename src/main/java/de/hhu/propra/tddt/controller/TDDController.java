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
        System.out.println("Compilation Nr: " + InformationCore.informationCore().getCompileManager().getCompilationNumber());

        if(phase.equals(CycleEnum.TEST))
        {
            System.out.println("Compiling in TEST");
            InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
            System.out.println("\n!!! Code Text !!!  \n "+InformationCore.informationCore().getCodeManager().getText());
            System.out.println("\n!!! Test Text !!!  \n "+InformationCore.informationCore().getTestManager().getText());
            String results = InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0);
            System.out.println(results+"   results");

            // Nächste Phase setzen oder Fehler ausgeben
            if(results.equals("[1]")){
                // Settings stoppen + Starten
                PluginLoader.pluginLoader().stopAllPlugins();
                PluginLoader.pluginLoader().startAllPlugins();
                System.out.println(phase);

            }
            else{
                for (int i = 0; !InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i).isEmpty(); i++) {
                    System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i));
                }
            }


            // Phase auf Nächste setzen
            phase = InformationCore.informationCore().getCycleManager().getCurrentPhase();
            System.out.println(phase);
            if(phase.equals(CycleEnum.CODE)) {
                PhaseLabel.setText("CODE");
                codeArea.setEditable(true);
                testArea.setEditable(false);
            }
            return;
        }


        if(phase.equals(CycleEnum.CODE))
        {
            System.out.println("Compiling in CODE");

            //zum überprüfen ob Code und! Test compilieren
            boolean compiliert = false;

            // Code Compilieren und Fails ausgeben
            InformationCore.informationCore().getCompileManager().compileCode(InformationCore.informationCore().getCodeManager().getText());
            for (int i = 0; !InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i).isEmpty(); i++) {
                System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i));
            }
            if(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(2).isEmpty()){
                compiliert = true;
            }
            else {
                compiliert = false;
            }

            // Test Compilieren und Fails ausgeben
            InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
            for (int i = 0; !InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i).isEmpty(); i++) {
                System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i));
            }
            if( InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("0"))
            {
                compiliert = true;
            }
            else {
                compiliert = false;
            }

            // Nächste Phase setzen
            if( compiliert == true) {
                // Settings stoppen + starten
                PluginLoader.pluginLoader().stopAllPlugins();
                PluginLoader.pluginLoader().startAllPlugins();
            }

            // Phase auf Nächste setzen
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
            System.out.println("Compiling in REFACTOR");

            //zum überprüfen ob Code und! Test compilieren
            boolean compiliert = false;


            // Code Compilieren und Fails ausgeben
            InformationCore.informationCore().getCompileManager().compileCode(InformationCore.informationCore().getCodeManager().getText());
            for (int i = 0; !InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i).isEmpty(); i++) {
                System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i));
            }
            if( InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(2).equals("")){
                compiliert = true;
            }
            else {
                compiliert = false;
            }



            // Test Compilieren und Fails ausgeben
            InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
            for (int i = 0; !InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i).isEmpty(); i++) {
                System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i));
            }
            if( InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("0")) {
                compiliert = true;
            }
            else {
                compiliert = false;
            }


            // Nächste Phase setzen
            if( compiliert == true) {
                // Settings stoppen + starten
                PluginLoader.pluginLoader().stopAllPlugins();
                PluginLoader.pluginLoader().startAllPlugins();
            }
            if(phase.equals(CycleEnum.TEST)) {
                PhaseLabel.setText("TEST");
                testArea.setEditable(true);
                codeArea.setEditable(false);
            }
            return;

        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        InformationCore.informationCore().setCodeManager(new TextManager(codeArea));
        InformationCore.informationCore().setTestManager(new TextManager(testArea));
        PluginLoader.pluginLoader().startAllPlugins();

        codeArea.setEditable(false);
        PhaseLabel.setText("TEST");

    }
}

