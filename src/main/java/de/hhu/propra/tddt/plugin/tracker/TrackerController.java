package de.hhu.propra.tddt.plugin.tracker;

import de.hhu.propra.tddt.informationcore.InformationCore;
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
    private LinkedList<LinkedList<String>> errorList = new LinkedList<>();

    private Label failedTest = new Label();
    private Label ignoredTest = new Label();
    private Label succededTest = new Label();

    @FXML
    Label durationLabel;
    @FXML
    VBox contentVBOX;

    VBox anotherVBOX = new VBox();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setDurationLabel() {
        durationLabel.setText(String.valueOf(duration.toMinutes()) + "minutes");
    }

    public void setErrorsFromList() {
        contentVBOX.getChildren().remove(anotherVBOX);
        anotherVBOX = new VBox();
        errorList = InformationCore.informationCore().getCompileManager().getCompileResultList();
        failedTest.setText(errorList.get(0).get(0));
        ignoredTest.setText(errorList.get(0).get(1));
        succededTest.setText(errorList.get(0).get(2));
        contentVBOX.getChildren().add(new Label("YOU ERRORS ARE:"));
        contentVBOX.getChildren().add(anotherVBOX);
        errorList.get(1).forEach(e -> {
            anotherVBOX.getChildren().add(new Label(e));
            System.out.println(e);
        });

    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
