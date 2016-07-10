package de.hhu.propra.tddt.settings;

/**
 * Created by zeljko On 01.07.2016
 */

/**
 * Handling and saving the settings.
 *
 * @author zeljko
 * @version 0.1
 */
public class SettingsManager {

    private final Settings settings = new Settings();

    public void saveSettings(){
        //@TODO this will 100% be needed to rework
        settings.saveSettings();
    }

    /**
     * Loading one specific Setting from the Settings.
     *
     * @param id The ID of the Setting you are looking for.
     *
     * @return The Setting which fits to the ID.
     *
     * @throws SettingException If the Setting does not exists this exception
     *                          will be thrown.
     */
    public Setting loadSetting(String id) throws SettingException {
        return settings.loadSetting(id);
    }

    /**
     * Adding a new Setting to the Settings of the SettingsManager.
     *
     * @param setting The setting you want to add.
     *
     * @throws SettingException If an Setting with the same ID exists this
     *                          exception will be thrown.
     */
    public void addSetting(Setting setting) throws SettingException {
        settings.addSetting(setting);
    }

    /**
     * Removing an Setting from the Settings in the SettingsManager
     *
     * @param id The id of the Setting you want to remove.
     *
     * @throws SettingException If the Setting does not exist this exception
     *                          will be thrown.
     */
    public void removeSetting(String id) throws SettingException {
        settings.removeSettingWithID(id);
    }

}
