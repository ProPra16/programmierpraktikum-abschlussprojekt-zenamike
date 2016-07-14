package de.hhu.propra.tddt.plugin;

import de.hhu.propra.tddt.plugin.babystep.Babysteps;
import de.hhu.propra.tddt.plugin.tracker.Tracker;

/**
 * Created by zeljko On 23.06.2016
 */

/*
 * Because we had not enough time, I hardcoded this
 */
public class PluginLoader {

    private static final Plugin babystep = new Babysteps();
    private static final Plugin tracker = new Tracker();

    private static boolean isBabystepActivated = false;
    private static boolean isTrackerActivated = false;

    public void activateBabystep(boolean activate){
        isBabystepActivated = activate;
    }

    public void activateTracker(boolean activate){
        isTrackerActivated = activate;
    }

    public void startAllPlugins(){
        if(isBabystepActivated == true) babystep.start();
        if(isTrackerActivated == true) tracker.start();
    }
    
    public void stopAllPlugins(){
        if(isBabystepActivated == true) babystep.stop();
        if(isTrackerActivated == true) tracker.stop();
    }
}
