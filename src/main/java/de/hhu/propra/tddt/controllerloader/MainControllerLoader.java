import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by Nadine on 02.07.16.
 */
public class MainControllerLoader {

    @FXML protected void handleSettingsButton(ActionEvent eventSetting){
        System.out.println("SettingsButton pressed!");
    }

    @FXML protected void handleTaskButton(ActionEvent eventTask){
        System.out.println("TaskButton pressed!");
    }

    @FXML protected void handleStartButton(ActionEvent eventStart){
        System.out.println("StartButton pressed!");
    }
}
