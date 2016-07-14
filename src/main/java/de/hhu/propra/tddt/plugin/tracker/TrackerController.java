package de.hhu.propra.tddt.plugin.tracker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by zeljko On 14.07.2016
 */
public class TrackerController implements Initializable {

    @FXML Label durationLabel;
    @FXML VBox contentVBOX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void setDurationLabel(Duration duration){
        durationLabel.setText(String.valueOf(duration.toMillis()) + "milliseconds");
    }

    public void setErrorsFromList(List<String> errorList){
        errorList.stream().forEach(e -> contentVBOX.getChildren().add(new Label(e)));
    }
}
