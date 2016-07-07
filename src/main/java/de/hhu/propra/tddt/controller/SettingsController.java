package de.hhu.propra.tddt.controller;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

/**
 * Created by MichaelLiske on 04.07.16.
 */
public class MainController {
    private MainController mainController;
    private ProgramDifficulty programDifficulty = ProgramDifficulty.CLEAN;


    @FXML CheckBox babySteps;
    @FXML CheckBox tracking;
    @FXML CheckBox Hard;

    @FXML
    private void setProgramClean(){
        this.programDifficulty = ProgramDifficulty.Clean;
    }
    @FXML
    private void setProgramBabySteps(){
        this.programDifficulty = ProgramDifficulty.BABYSTEPS;
    }
    @FXML
    private void setProgramTracking(){
        this.programDifficulty = ProgramDifficulty.TRACKING;
    }
    @FXML
    private void setProgramHard(){
        this.programDifficulty = ProgramDifficulty.Hard;
    }
    @FXML
    private void startNewProgram() throws Exception {
        mainController.switchToTDD();
    }
    @FXML
    private void exitProgram() {
        System.exit(0);
    }
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    /**
     * init?
     */

}