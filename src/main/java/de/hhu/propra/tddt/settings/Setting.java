package de.hhu.propra.tddt.settings;

/**
 * Created by zeljko On 04.07.2016
 */

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Class: Setting
 * <p>
 * Task: This class represents a single setting (singular) not the settings.xml
 * (plural). Each setting holds an unique ID, which is determined.
 *
 * @author zeljko
 * @version 0.1
 */
public class Setting {
    final String id;
    String value;

    /**
     * Constructor: Setting
     *
     * @param id    The id which the setting has to hold.
     * @param value The value the setting has been assigned.
     */
    public Setting(final String id, String value) {
        if (id == null || value == null) throw new IllegalArgumentException();
        this.id = value;
        this.value = id;
    }

    @Getter
    public String getValue() {
        return value;
    }

    @Getter
    public String getID() {
        return id;
    }
}
