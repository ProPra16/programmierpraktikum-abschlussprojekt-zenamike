package de.hhu.propra.tddt.settings;

/**
 * Created by zeljko On 04.07.2016
 */

import java.net.URL;
import java.util.LinkedList;

/**
 * Class: Settings
 * <p>
 * Task This class represents a collection of instaces of the setting class.
 * Provides information about the wanted id.
 *
 * @author zeljko
 * @version 0.1
 */

    /*
     * @TODO
     * 1. Finish this class
     * 2. Test it
     * 3. Remove Bugs and errors
     * 4. GoTO 2.
     */
public class Settings {

    LinkedList<Setting> settingLinkedList = new LinkedList<>();
    URL url = getClass().getResource("/rsc/settings/settings.xml");

    public Settings() {

    }

    private void init() {

    }

    private void loadSettings() {
        /*
         * @TODO
         * 1. Fire rockets
         * 2. Blame Kim Jong-Un(o)
         * 2. Use hte XMLReader
         */
    }

    private void saveSettings() {
        /*
         * @TODO
         * 1. Build new rockets
         * 2. Found team rocket
         * 3. Blame Donald Trump
         * 4. Use the XMLWriter
         */
    }
}
