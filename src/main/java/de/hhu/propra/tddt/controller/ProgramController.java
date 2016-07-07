package de.hhu.propra.tddt.controller;
import javafx.fxml.Initializable;

/**
 * Created by MichaelLiske on 04.07.16.
 */
public abstract class ProgramController extends CustomController {
    private MainController mainController;
    private ProgramDifficulty programDifficulty;

    @FXML
    private void exitProgram() {
        mainController.switchToStartMenue();
    }

    @Override
    public void init(URL url, ResourceBundle resourceBundle) {
        this.gridpane = new Gridpane();
        borderPane.setCenter(gridPane);
        ProgramCombine programCombine = new ProgramCombine(programDifficulty, this);
        gridPane = programCombine.fillBoard(gridPane);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setProgramDifficulty(ProgramController programController) {
        this.programDifficulty = programDifficulty;
    }


    void close();
}