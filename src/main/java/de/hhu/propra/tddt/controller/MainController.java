package de.hhu.propra.tddt.controller;
import de.hhu.propra.tddt.controllerloader.SettingsControllerLoader;
import de.hhu.propra.tddt.controllerloader.StartControllerLoader;
import de.hhu.propra.tddt.controllerloader.TDDControllerLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Nadine on 03.07.16.
 */
public class MainController {

    /**private final Stage window;

    private Scene start;
    private Scene setting;
    private Scene taskList;
    private Scene tDD;

    private StartController startController;
    private SettingsController settingController;
    private TaskListController taskListController;
    private ProgramController tDDController;


    public MainController(final Stage window) throws Exception{
        this.window = window;
        initialise();
    }

    private void initialise() throws Exception{
        StartControllerLoader startControllerLoader = new StartControllerLoader(this);
        startController = (StartController) startControllerLoader.controller();
        start = startControllerLoader.scene();

        SettingsControllerLoader settingsControllerLoader = new SettingsControllerLoader(this);
        settingsControllerLoader = (SettingsControllerLoader) settingsControllerLoader.controller();
        settingsController = SettingsControllerLoader.scene();

        TaskListLoader taskListLoader = new TaskListLoader(this);
        taskListLoader = (TaskListLoader) taskListLoader.controller();
        taskList = taskListLoader.scene();

        TDDControllerLoader tDDControllerLoader = new TDDCOntrollerLoader(this);
        tDDController = (TDDController) tDDControllerLoader.controller();
        tDDController = tDDControllerLoader.scene();

        this.stage.setScene(start);
        this.stage.show();
    }

    protected void switchToStartMenue() {
        stage.setScene(start);
    }

    protected void switchToSettingMenue() {
        stage.setScene(setting);
    }

    protected void switchToTaskListMenue() {
        stage.setScene(taskList);
    }

    /**
     * TDD Programmdifficulty has to be implemented.
     *//**
    protected void switchToTDD(){

        Scene tDDScene = tDDControllerLoader.scene();
        stage.setScene(tDDScene);
    }



     */




}
