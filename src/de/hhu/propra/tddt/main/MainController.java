import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Nadine on 03.07.16.
 */
public class MainController {

    private final Stage window;

    private Scene startMenue;
    private Scene settingMenue;
    private Scene taskListMenue;
    private Scene tDD;

    private StartMenueController startMenueController;
    /*private SettingMenueController settingMenueController;
    private TaskListMenueController taskListMenueController;
    private TDDController tddController;*/


    public MainController(final Stage window) throws Exception{
        this.window = window;
        initialise();
    }

    private void initialise() throws Exception{
        StartMenueControllerLoader startMenueControllerLoader = new StartMenueControllerLoader(this);
        startMenueController = (StartMenueController) startMenueControllerLoader.controller();
        startMenue = startMenueControllerLoader.scene();

        this.window.setScene(startMenue);
        this.window.setResizable(false);
        this.window.show();


    }





}
