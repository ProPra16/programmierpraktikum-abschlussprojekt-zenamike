package de.hhu.propra.tddt.main;

import de.hhu.propra.tddt.controller.SettingsController;
import de.hhu.propra.tddt.controller.StartController;
import de.hhu.propra.tddt.controller.TDDController;
import de.hhu.propra.tddt.controller.TaskListController;

import de.hhu.propra.tddt.loader.SettingsLoader;
import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.loader.TDDLoader;
import de.hhu.propra.tddt.loader.TaskListLoader;


import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by MichaelLiske on 09.07.16 at 20:01.
 */

/**
 *  class: instantiates all controller classes
 */

public class MainControlClass {

    protected Stage window;

    protected Scene start;
    protected Scene setting;
    protected Scene taskList;
    protected Scene tDD;

    protected StartController startController;
    protected SettingsController settingsController;
    protected TaskListController taskListController;
    protected TDDController tDDController;

    public MainControlClass(Stage window) throws Exception{
        this.window = window;
        initialize();
    }

    private void initialize() throws Exception{
        StartLoader startLoader = new StartLoader(window);
        startController = (StartController) startLoader.controller();
        start = startLoader.scene();

        SettingsLoader settingsLoader = new SettingsLoader(window);
        settingsController = (SettingsController) settingsLoader.controller();
        setting = settingsLoader.scene();

        TaskListLoader taskListLoader = new TaskListLoader(window);
        taskListController = (TaskListController) taskListLoader.controller();
        taskList = taskListLoader.scene();

        TDDLoader tDDLoader = new TDDLoader(window);
        tDDController = (TDDController) tDDLoader.controller();
        tDD = tDDLoader.scene();

        this.window.setScene(start);
        this.window.setResizable(false);
        this.window.show();
    }



}
