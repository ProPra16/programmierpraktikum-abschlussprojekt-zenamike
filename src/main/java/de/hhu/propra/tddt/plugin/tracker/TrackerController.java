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

    private static Duration durationStatic;
    private static List<String> errorListStatic;

    @FXML Label durationLabel;
    @FXML VBox contentVBOX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDurationLabel(){
        durationLabel.setText(String.valueOf(durationStatic.toMillis()) + "milliseconds");
    }

    public void setErrorsFromList(){
        errorListStatic.stream().forEach(e -> contentVBOX.getChildren().add(new Label(e)));
    }

    public static void setDuration(Duration duration){
        durationStatic = duration;
    }
}
