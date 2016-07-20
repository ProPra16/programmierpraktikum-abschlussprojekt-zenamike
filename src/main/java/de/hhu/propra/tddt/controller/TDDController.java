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

import static de.hhu.propra.tddt.cycle.CycleEnum.TEST;

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
    public String results;
    boolean compiliert;

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



    private CycleEnum phase = TEST;
    @FXML private Label PhaseLabel;


    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {

        if(phase.equals(TEST))
        {
            new StartLoader(StartLoader.getWindow());
            PluginLoader.pluginLoader().stopAllPlugins();
            PluginLoader.pluginLoader().startAllPlugins();
        }
        if(phase.equals(CycleEnum.CODE))
        {
            phase = TEST;
            PhaseLabel.setText("TEST");
            testArea.setEditable(true);
            codeArea.setEditable(false);
            PluginLoader.pluginLoader().stopAllPlugins();
            PluginLoader.pluginLoader().startAllPlugins();
        }
        if(phase.equals(CycleEnum.REFACTOR))
        {
            phase = CycleEnum.CODE;
            PhaseLabel.setText("CODE");
            testArea.setEditable(false);
            codeArea.setEditable(true);
            PluginLoader.pluginLoader().stopAllPlugins();
            PluginLoader.pluginLoader().stopAllPlugins();
        }
        System.out.println("Back");
    }


    @FXML
    public void handleCompileButton(ActionEvent actionEvent) {
        System.out.println("Compilation Nr: " + InformationCore.informationCore().getCompileManager().getCompilationNumber());
        switch (phase){
            case TEST:
                System.out.println("Compiling in TEST");
                InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
                try {
                    System.out.println("Test compiliert");
                    InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("1");

                }catch (IndexOutOfBoundsException e){
                    System.out.println("Test gibt NULL aus");
                }



                phase = InformationCore.informationCore().getCycleManager().getCurrentPhase();
                // Nächste Phase setzen oder Fehler ausgeben
                if(phase.equals(CycleEnum.CODE)){
                    // Settings stoppen + Starten
                    PluginLoader.pluginLoader().stopAllPlugins();
                }

                // Phase auf Nächste setzen
                phase = InformationCore.informationCore().getCycleManager().getCurrentPhase();
                System.out.println(phase);
                if(phase.equals(CycleEnum.CODE)) {
                    PhaseLabel.setText("CODE");
                    codeArea.setEditable(true);
                    testArea.setEditable(false);
                    PluginLoader.pluginLoader().startAllPlugins();
                }
                break;


            case CODE:
                System.out.println("Compiling in CODE");

                //zum überprüfen ob Code und! Test compilieren
                compiliert = false;

                // Code Compilieren und Fails ausgeben
                InformationCore.informationCore().getCompileManager().compileCode(InformationCore.informationCore().getCodeManager().getText());
                try {
                    if(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(2).equals("")){
                        compiliert = true;
                        /*for(int i = 0 ; i < InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).size(); i++){
                            System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(i));
                        }*/
                    }
                    else {
                        compiliert = false;
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Code gibt NULL aus");
                    return;
                }


                // Test Compilieren und Fails ausgeben
                InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
                try {
                    if(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("0")){
                        compiliert = true;
                    }
                    else {
                        compiliert = false;
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Test gibt NULL aus");
                    return;
                }


                // Nächste Phase setzen
                if( compiliert == true) {
                    // Settings stoppen + starten
                    PluginLoader.pluginLoader().stopAllPlugins();
                }


                // Phase auf Nächste setzen
                phase = InformationCore.informationCore().getCycleManager().getCurrentPhase();
                if(phase.equals(CycleEnum.REFACTOR)) {
                    PhaseLabel.setText("REFACTOR");
                    testArea.setEditable(true);
                    codeArea.setEditable(true);
                    PluginLoader.pluginLoader().startAllPlugins();
                }
                break;

            case REFACTOR:

                System.out.println("Compiling in REFACTOR");

                //zum überprüfen ob Code und! Test compilieren
                compiliert = false;


                // Code Compilieren und Fails ausgeben
                InformationCore.informationCore().getCompileManager().compileCode(InformationCore.informationCore().getCodeManager().getText());
                try {
                    if(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(2).isEmpty()){
                        compiliert = true;
                        System.out.println("Code compiliert");
                    }
                    else {
                        compiliert = false;
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Code gibt NULL aus");
                    return;
                }



                // Test Compilieren und Fails ausgeben
                InformationCore.informationCore().getCompileManager().compileTest(InformationCore.informationCore().getTestManager().getText());
                try {
                    if(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0).get(0).equals("0")){
                        compiliert = true;
                        System.out.println("Test compiliert");
                    }
                    else {
                        compiliert = false;
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Test gibt NULL aus");
                    return;
                }

                // Nächste Phase setzen
                if( compiliert == true) {
                    // Settings stoppen + starten
                    PluginLoader.pluginLoader().stopAllPlugins();
                }
                if(phase.equals(TEST)) {
                    PhaseLabel.setText("TEST");
                    testArea.setEditable(true);
                    codeArea.setEditable(false);
                    PluginLoader.pluginLoader().startAllPlugins();
                }
                break;
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