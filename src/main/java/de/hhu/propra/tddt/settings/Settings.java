package de.hhu.propra.tddt.settings;

import de.hhu.propra.tddt.util.xml.reader.XMLCatalogReader;
import de.hhu.propra.tddt.util.xml.reader.XMLReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zeljko On 04.07.2016
 */

/**
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

    HashMap<String, Setting> settingHashMap = new HashMap<>();
    //the place where the settings will be stored, so that the program remembers the changes.
    final URL url = getClass().getResource("/rsc/settings/settings.xml");

    /**
     * Preventing unwanted use of this class by restricting it's accessibility.
     * When Settings becomes initialized it automatically loads the settings from the file
     */
    protected Settings(){

    }

    /**
     * Providing the setting for an ID.
     *
     * @param id The ID for the setting you are looking for
     *
     * @return The Setting
     *
     * @throws SettingException If there is no setting for the given ID this
     *                          exception will be thrown
     */
    protected Setting loadSetting(String id) throws SettingException {
        String idCopy = id.toLowerCase();
        Setting setting = settingHashMap.get(idCopy);
        if(setting == null) throw new SettingException();
        return setting;
    }

    /**
     * Removes a setting with the given ID from the Settings.
     *
     * @param id The Setting with this ID will be removed, nothing will happen
     *           if no setting with the given ID was present.
     */
    protected void removeSettingWithID(String id) throws SettingException {
        String idCopy = id.toLowerCase();
        Setting setting = settingHashMap.get(id);
        if (setting != null) settingHashMap.remove(setting);
        if (setting == null) throw new SettingException();
    }

    /**
     * Adds a setting to the Settings. If the Setting already existed it will be
     * overwritten.
     *
     * @param setting The setting you want to add/overwrite
     */
    protected void addSetting(Setting setting) {
        if (settingHashMap.containsKey(setting.getID())) {
            settingHashMap.replace(setting.getID(), setting);
        }
        if (!settingHashMap.containsKey(setting.getID())) {
            settingHashMap.put(setting.getID(), setting);
        }
    }

    protected List<Setting> dumpSettings(){
        ArrayList<Setting> arrayList = new ArrayList<>();
        arrayList.addAll(settingHashMap.values());
        return arrayList;
    }
}
