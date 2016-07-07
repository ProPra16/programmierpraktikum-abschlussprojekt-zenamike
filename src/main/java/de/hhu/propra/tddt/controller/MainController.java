package de.hhu.propra.tddt.controller;
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
    private SettingMenueController settingMenueController;
    private TaskListMenueController taskListMenueController;
    private TDDController tDDController;


    public MainController(final Stage window) throws Exception{
        this.window = window;
        initialise();
    }

    private void initialise() throws Exception{
        StartMenueControllerLoader startMenueControllerLoader = new StartMenueControllerLoader(this);
        startMenueController = (StartMenueController) startMenueControllerLoader.controller();
        startMenue = startMenueControllerLoader.scene();

        SettingsMenueLoader settingsMenueLoader = new SettingsMenueLoader(this);
        settingsMenueLoader = (SettingsMenueLoader) settingsMenueLoader.controller();
        settingsMenue = settingsMenueLoader.scene();

        TaskListMenueLoader taskListMenueLoader = new TaskListMenueLoader(this);
        taskListMenueLoader = (TaskListMenueLoader) taskListMenueLoader.controller();
        taskListMenue = taskListMenueLoader.scene();

        TDDControllerLoader tDDControllerLoader = new TDDCOntrollerLoader(this);
        tDDController = (TDDController) tDDControllerLoader.controller();
        tDDController = tDDControllerLoader.scene();

        this.stage.setScene(startMenue);
        this.stage.show();
    }

    protected void switchToStartMenue() {
        stage.setScene(startMenue);
    }

    protected void switchToSettingMenue() {
        stage.setScene(settingMenue);
    }

    protected void switchToTaskListMenue() {
        stage.setScene(taskListMenue);
    }

    /**
     * TDD Programmdifficulty has to be implemented.
     */
    protected void switchToTDD(){

        Scene tDDScene = tDDControllerLoader.scene();
        stage.setScene(tDDScene);
    }




}
