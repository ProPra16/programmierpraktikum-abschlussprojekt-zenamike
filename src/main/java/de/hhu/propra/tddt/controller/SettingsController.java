package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.informationcore.InformationCore;
import de.hhu.propra.tddt.loader.SettingsLoader;
import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;
import de.hhu.propra.tddt.plugin.PluginLoader;
import de.hhu.propra.tddt.settings.Setting;
import de.hhu.propra.tddt.settings.SettingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
    @FXML private TextField Time;



    private static boolean Babybool = false;
    private static String Babysteps = "B-out";
    private static boolean Trackbool= false;
    private static String Track  = "T-out";


    @FXML
    public void handleBabyStepsBox(ActionEvent actionEvent) {
        if(Babybool == false) {
            Babybool = true; Babysteps = "Babysteps-on";
            System.out.println(getBabySteps());
            PluginLoader.pluginLoader().activateBabystep(true);
            try {
                InformationCore.informationCore().getSettingsManager().addSetting(new Setting("babysteps", Time.getText()));
            } catch (SettingException e) {
                e.printStackTrace();
            }

            return;
        }
        if(Babybool == true ) {
            Babybool = false; Babysteps = "B-off";
            System.out.println(getBabySteps());
            PluginLoader.pluginLoader().activateBabystep(false);
            return;
        }
    }
    @FXML
    public void handleTrackingBox(ActionEvent actionEvent) {
        if(Trackbool == false) {Trackbool = true; Track = "Tracking-on";
            System.out.println(getTracking());
            PluginLoader.pluginLoader().activateTracker(true);
            return;
        }
        if(Trackbool == true ) {Trackbool = false;Track = "T-off";
            System.out.println(getTracking());
            PluginLoader.pluginLoader().activateTracker(false);
            return;
        }
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        new StartLoader(SettingsLoader.getWindow());
        System.out.println("Back");
    }
    @FXML
    public void handleStartTDDButton(ActionEvent actionEvent) throws IOException
    {
        new TDDLoader(SettingsLoader.getWindow());
        System.out.println("Start");
    }


    public static String getBabySteps(){
        return Babysteps;
    }
     public static String getTracking() { return Track;     }


}