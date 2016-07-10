package de.hhu.propra.tddt.plugin;

/**
 * Created by zeljko On 23.06.2016
 */

import de.hhu.propra.tddt.contentmanager.TextManager;
import de.hhu.propra.tddt.cycle.CycleManager;
import de.hhu.propra.tddt.settings.SettingsManager;

/******************************************************************************
 * Handling and interacting with the Plugins requires a Manager or Handler. And
 * here you see the PluginManager.
 * <p>
 * The PluginManager is a 'gate' which lets the plugins access information the
 * program can provide. But we don't want the plugins to access all information
 * so we setup the PluginManager, that the Plugins can easily access the needed
 * and allowed information.
 *
 * @author zeljko
 * @version 0.5
 ******************************************************************************/
public interface PluginManager {

    /**************************************************************************
     * Gives the plugins the information about the code you wrote.
     *
     * @return The CodeManager which contains all information about the code
     ***************************************************************************/
    public TextManager getCodeManager();

    /**************************************************************************
     * Gives the plugins the information about the test you wrote.
     *
     * @return The TestManager which contains all information about the test
     ***************************************************************************/
    public TextManager getTestManager();

    /**************************************************************************
     * Gives the plugins the information about the phase the user is in.
     *
     * @return The CycleManager which contains all information about the cycle
     **************************************************************************/
    public CycleManager getCycleManager();

    /**************************************************************************
     * Gives the plugins the information about the settings the user has set.
     *
     * @return The SettingsManager which contains all information about the
     * settings the user has specified
     **************************************************************************/
    public SettingsManager getSettingsManager();

    /**************************************************************************
     * Gives the plugins the information about the compilations and their
     * results.
     *
     * @return The CompileManager provides the information about the
     * compilations the user made.
     **************************************************************************/
    public Object getCompileManager();
}
