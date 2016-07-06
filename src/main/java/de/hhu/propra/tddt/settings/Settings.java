package de.hhu.propra.tddt.settings;

/**
 * Created by zeljko On 04.07.2016
 */

import java.net.URL;
import java.util.LinkedList;
import java.util.Set;

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
    final URL url = getClass().getResource("/rsc/settings/settings.xml");

    /**
     * Method: loadSettings
     *
     * Load the current settings from a specified file.
     */
    protected void loadSettings() {
        /*
         * @TODO
         * 1. Fire rockets
         * 2. Blame Kim Jong-Un(o)
         * 2. Use hte XMLReader
         */
    }

    /**
     * Method: saveSettings
     *
     * Save the settings to a specified file.
     */
    protected void saveSettings() {
        /*
         * @TODO
         * 1. Build new rockets
         * 2. Found team rocket
         * 3. Blame Donald Trump
         * 4. Use the XMLWriter
         */
    }

    /**
     * Method
     */
    public Settings() {

    }

    /**
     * Method: loadSetting
     * <p>
     * Task: Providing the setting for an ID.
     *
     * @param id The ID for the setting you are looking for
     *
     * @return The Setting
     *
     * @throws SettingException If there is no setting for the given ID this
     *                          exception will be thrown
     */
    protected Setting loadSetting(String id) throws SettingException {
        for (Setting setting : settingLinkedList) {
            if (setting.getID().equals(id)) return setting;
        }

        throw new SettingException();
    }

    protected void removeSetting(String id) throws SettingException {
        boolean settingDoesNotExist = true;

        for (int i = 0; i < settingLinkedList.size(); i++) {
            if (settingLinkedList.get(i).getID().equals(id)) {
                settingLinkedList.remove(i);
                settingDoesNotExist = false;
            }
        }

        if(!settingDoesNotExist) throw new SettingException();
    }

    protected void addSetting(Setting setting) throws SettingException {
        boolean settingAlready = false;

        for (int i = 0; i < settingLinkedList.size(); i++) {
            if (settingLinkedList.get(i).getID().equals(setting.getID()))
                settingAlready = true;
        }

        if (!settingAlready) settingLinkedList.add(setting);
        if (settingAlready) throw new SettingException();
    }
}
