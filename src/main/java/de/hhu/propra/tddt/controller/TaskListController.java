package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.informationcore.InformationCore;
import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;
import de.hhu.propra.tddt.loader.TaskListLoader;
import de.hhu.propra.tddt.plugin.PluginLoader;
import de.hhu.propra.tddt.settings.Setting;
import de.hhu.propra.tddt.settings.SettingException;
import de.hhu.propra.tddt.util.manager.CatalogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.xml.sax.SAXParseException;

import javax.sound.midi.MidiDevice;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Created by Nadine on 09.07.16
 */

/**
 *  Class: handles the scenes' buttons (namely back and startTDD button)
 */

public class TaskListController implements Initializable {

    @FXML public Button Go;
    @FXML private ChoiceBox<String> Tasks;
    @FXML private TextArea Discription;
    @FXML private TextField pathField;
    @FXML String path =  "";
    @FXML private CheckBox BabySteps;
    @FXML private CheckBox Tracking;
    @FXML private Button Back;
    @FXML private Button TDDCycle;
    @FXML private TextField Time;

    static String classCode;
    static String testCode;
    CatalogManager manager;

    private static boolean Babybool = false;
    private static boolean Trackbool= false;

    public static String getClassCode() {
        return classCode;
    }

    public static String getTestCode() {
        return testCode;
    }


    @FXML
    public String getPath(){
        path = pathField.getText();
        return path;
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        new StartLoader(TaskListLoader.getWindow());
        System.out.println("Back");
    }
    @FXML
    public void handleStartTDDButton(ActionEvent actionEvent) throws IOException {
        new TDDLoader(TaskListLoader.getWindow());
        TDDLoader.controller.setCode(classCode);
        TDDLoader.controller.setTest(testCode);

        InformationCore.informationCore().getCodeManager().updatePhaseSave(classCode);
        InformationCore.informationCore().getTestManager().updatePhaseSave(testCode);

        System.out.println(InformationCore.informationCore().getTestManager().getPhaseSave());
        System.out.println(InformationCore.informationCore().getTestManager().getText());

        System.out.println(InformationCore.informationCore().getCodeManager().getPhaseSave());
        System.out.println(InformationCore.informationCore().getCodeManager().getText());
    }

    @FXML
    public void handleGoButton(ActionEvent actionEvent) throws SAXParseException{
        path = getPath();
        System.out.println(path);
        manager = new CatalogManager(path);
        ArrayList<String> names = manager.getExerciseNames();

        for(int i = 0; i < names.size(); i++){
            String current = names.get(i);
            Tasks.getItems().add(current);
        }

        Tasks.getSelectionModel().selectedItemProperty().addListener((observer,oldValue,newValue) ->{
            String description = manager.compareChoiceWithCatalog(newValue);
            Discription.setText(description);
            classCode = manager.getCurrentChosenOne().getClasses();
            testCode = manager.getCurrentChosenOne().getTests();
        });
    }

    @FXML
    public void handleBabyStepsBox(ActionEvent actionEvent) {
        if(Babybool == false) {
            Babybool = true;
            PluginLoader.pluginLoader().activateBabystep(true);
            BabySteps.setSelected(true);
            try {
                if(!Objects.equals(Time.getText(), ""))
                    InformationCore.informationCore().getSettingsManager().addSetting(new Setting("babysteps", Time.getText()));
            } catch (SettingException e) {
                e.printStackTrace();
            }

            return;
        }
        if(Babybool == true ) {
            Babybool = false;
            BabySteps.setSelected(false);
            PluginLoader.pluginLoader().activateBabystep(false);
            return;
        }
    }
    @FXML
    public void handleTrackingBox(ActionEvent actionEvent) {
        if(Trackbool == false) {Trackbool = true;
            Tracking.setSelected(true);
            PluginLoader.pluginLoader().activateTracker(true);
            return;
        }
        if(Trackbool == true ) {Trackbool = false;
            Tracking.setSelected(false);
            PluginLoader.pluginLoader().activateTracker(false);
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tracking.setSelected(Trackbool);
        BabySteps.setSelected(Babybool);
    }
}

