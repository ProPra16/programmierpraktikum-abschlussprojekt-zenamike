package de.hhu.propra.tddt.plugin;

import de.hhu.propra.tddt.informationcore.InformationCore;
import de.hhu.propra.tddt.plugin.babystep.Babysteps;
import de.hhu.propra.tddt.plugin.tracker.Tracker;

/**
 * Created by zeljko On 23.06.2016
 */

/*
 * Because we had not enough time, I hardcoded this
 */
public class PluginLoader {

    private static PluginLoader pluginLoader = new PluginLoader();

    private static final Plugin babystep = new Babysteps();
    private static final Plugin tracker = new Tracker();

    private static boolean isBabystepActivated = false;
    private static boolean isTrackerActivated = false;

    private PluginLoader(){ }
    static {
        babystep.setPluginManager(InformationCore.informationCore());
        tracker.setPluginManager(InformationCore.informationCore());
    }

    public static PluginLoader pluginLoader(){
        return pluginLoader;
    }

    public void activateBabystep(boolean activate){
        isBabystepActivated = activate;
    }

    public void activateTracker(boolean activate){
        isTrackerActivated = activate;
    }

    public void startAllPlugins(){
        if(isBabystepActivated == true) babystep.starting();
        if(isTrackerActivated == true) tracker.starting();
    }

    public void stopAllPlugins(){
        if(isBabystepActivated == true) babystep.stopping();
        if(isTrackerActivated == true) tracker.stopping();
    }
}
