package de.hhu.propra.tddt.plugin.tracker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by zeljko On 14.07.2016
 */
public class TrackerController implements Initializable {

    private Duration duration = Duration.ZERO;
    private List<String> errorList = new LinkedList<>();

    private Label failedTest = new Label();
    private Label ignoredTest = new Label();
    private Label succededTest = new Label();

    @FXML Label durationLabel;
    @FXML VBox contentVBOX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setErrorList(LinkedList<String> list){
        errorList = list;
    }

    public void setDurationLabel(){
        durationLabel.setText(String.valueOf(duration.toMinutes()) + "minutes");
    }

    public void setErrorsFromList(){
            failedTest.setText(errorList.get(0));
            ignoredTest.setText(errorList.get(1));
            succededTest.setText(errorList.get(2));
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }
}
