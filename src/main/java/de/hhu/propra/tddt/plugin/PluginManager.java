package de.hhu.propra.tddt.plugin;

/**
 * Created by zeljko On 23.06.2016
 */

import de.hhu.propra.tddt.contentmanager.CycleManager;
import de.hhu.propra.tddt.contentmanager.TextManager;

/******************************************************************************
 * Interface: PluginManager
 * <p>
 * Task: Handling and interacting with the Plugins requires a Manager or
 * Handler. And here you see the PluginManager.
 * <p>
 * The PluginManager is a 'gate' which lets the plugins access information the
 * programm can provide. But we don't want the plugins to access all information
 * so we setup the PluginManager, that the Plugins can easily access the needed
 * and allowed information.
 *
 * @author zeljko
 * @version 0.1 first try
 ******************************************************************************/
public interface PluginManager {

    /**************************************************************************
     * Method: getCodeManager
     * <p>
     * Gives the plugins the information about the code you wrote.
     *
     * @return The CodeManager which contains all information about the code
     ***************************************************************************/
    public TextManager getCodeManager();

    /**************************************************************************
     * Method: getTestManager
     * <p>
     * Gives the plugins the information about the test you wrote.
     *
     * @return The TestManager which contains all information about the test
     ***************************************************************************/
    public TextManager getTestManager();

    /**************************************************************************
     * Method: getCycleManager
     * <p>
     * Gives the plugins the information about the phase the user is in.
     *
     * @return The CycleManager which contains all information about the cycle
     **************************************************************************/
    public CycleManager getCycleManager();
}
