package de.hhu.propra.tddt.settings;

/**
 * Created by zeljko On 01.07.2016
 */
public class SettingsManager {

    Settings settings = new Settings();

    public SettingsManager(){
        settings.loadSettings();
    }

    public Setting loadSetting(String id) throws SettingException {
        return settings.loadSetting(id);
    }

    public void addSetting(Setting setting) throws SettingException {
        settings.addSetting(setting);
    }

    public void removeSetting(String id) throws SettingException {
        settings.removeSetting(id);
    }

}
