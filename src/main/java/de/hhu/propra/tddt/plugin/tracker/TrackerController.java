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
    private List<String> errorListStatic = new LinkedList<>();

    @FXML Label durationLabel;
    @FXML VBox contentVBOX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setErrorList(List<String> list){
        errorListStatic = list;
    }

    public void setDurationLabel(){
        durationLabel.setText(String.valueOf(duration.toMillis()) + "milliseconds");
    }

    public void setErrorsFromList(){
        errorListStatic.stream().forEach(e -> contentVBOX.getChildren().add(new Label(e)));
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }
}
