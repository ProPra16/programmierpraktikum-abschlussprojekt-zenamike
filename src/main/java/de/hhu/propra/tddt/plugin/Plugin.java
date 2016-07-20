package de.hhu.propra.tddt.plugin;

/**
 * Created by zeljko On 23.06.2016
 */

/*
 * The link which influenced this and my idea of a Plugin (and probably the
 * PluginManager) is this one:
 * https://wiki.byte-welt.net/wiki/Java-Programme_durch_PlugIns_erweitern @ [13:00]
 *
 */

/******************************************************************************
 * Interface: Plugin
 * <p>
 * Task: Implementing the "Extensions" made easy. The information the plugins
 * are allowed to receive are specified in with this Interface and the way you
 * have to deal with a plugin is generalized. So therefor the PluginManager can
 * do his work properly and can be kept cleaner (without typecast and so on).
 *
 * @author zeljko
 * @version 1.0
 ******************************************************************************/
public interface Plugin {

    /**************************************************************************
     * Method: starting
     * <p>
     * Starting a plugin requires this method.
     **************************************************************************/
    public void starting();

    /**************************************************************************
     * Method: stopping
     * <p>
     * Stopping a plugin will happen with this method. Be it an 'emergency-stopping'
     * or you just want to turn it off.
     **************************************************************************/
    public void stopping();

    /**************************************************************************
     * Method: setPluginManager
     * <p>
     * To let the Plugin interact with the program we need to give something
     * with what it can work. The PluginManager is the best 'something' because
     * it knows what kind of information the Plugins are allowed to get. And
     * with the plugin manager We have specified what information the plugins
     * are allowed to use.
     **************************************************************************/
    public void setPluginManager(PluginManager pluginManager);
}
