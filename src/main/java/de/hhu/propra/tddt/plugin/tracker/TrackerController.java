package de.hhu.propra.tddt.plugin.tracker;

import de.hhu.propra.tddt.informationcore.InformationCore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.Duration;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Created by zeljko On 14.07.2016
 */
public class TrackerController implements Initializable {

    private Duration duration = Duration.ZERO;
    private LinkedList<LinkedList<String>> errorList = new LinkedList<>();

    private Label failedTest = new Label();
    private Label ignoredTest = new Label();
    private Label succededTest = new Label();

    private Label thisFuckingLabel = new Label();


    @FXML
    Label durationLabel;
    @FXML
    VBox contentVBOX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contentVBOX.getChildren().addAll(failedTest, ignoredTest, succededTest, new Label("YOUR ERRORS ARE: "), thisFuckingLabel);
    }

    public void setDurationLabel() {
        durationLabel.setText(String.valueOf(duration.toMinutes()) + "minutes");
    }

    public void setErrorsFromList() {
        errorList = InformationCore.informationCore().getCompileManager().getCompileResultList();
        updateShortCompileResultLabel();
        updateMoreCompileResultLabel();

    }

    private void updateShortCompileResultLabel(){

        if(errorList.get(0).get(0).equals("") || errorList.get(0).get(0).equals(" ")){
            failedTest.setText("FAILED TESTS: THE TEST IS NOT COMPILEABLE" );
            ignoredTest.setText("");
            succededTest.setText("");
        } else {
            failedTest.setText("FAILED TESTS: " + errorList.get(0).get(0));
            ignoredTest.setText("IGNORED TESTS: "+errorList.get(0).get(1));
            succededTest.setText("SUCCEDED TESTS: "+errorList.get(0).get(2));
        }
    }

    private void updateMoreCompileResultLabel(){
        String fuckingString = "";
        for (String string: errorList.get(1)) {
            fuckingString = fuckingString + string;
        }
        System.out.println(fuckingString);
        thisFuckingLabel.setText(fuckingString);
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }


}
