package de.hhu.propra.tddt.plugin.Tracker;

import de.hhu.propra.tddt.plugin.Plugin;
import de.hhu.propra.tddt.plugin.PluginManager;

/**
 * Created by zeljko On 04.07.2016
 */
public class Tracker implements Plugin{

    PluginManager pluginManager;

    @Override
    public void start() {
        //@TODO finish this
    }

    @Override
    public void stop() {
        //@TODO finish that
    }

    @Override
    public void setPluginManager(PluginManager pluginManager) {
        if(pluginManager == null) throw new IllegalArgumentException();
        this.pluginManager = pluginManager;
    }

    /*
     * @TODO
     * Build a wall and finish this method
     * Still waiting for some test for the xml reader and writer to be sure they work :)
     * FIRE EXCEPTIONS!!
     */
}
