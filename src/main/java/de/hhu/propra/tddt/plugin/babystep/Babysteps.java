package de.hhu.propra.tddt.plugin.babystep;

/**
 * Created by zeljko On 24.06.2016
 */

import com.sun.org.apache.xpath.internal.SourceTree;
import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.plugin.Plugin;
import de.hhu.propra.tddt.plugin.PluginManager;
import de.hhu.propra.tddt.settings.Setting;
import de.hhu.propra.tddt.settings.SettingException;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

/******************************************************************************
 * After waiting X Minutes it erase the source code and resets the current
 * phase. You can define the X in the minutes (you should be able to do it).
 * <p>
 * By default it has been set to 3 Minutes.
 *
 * @author zeljko
 * @version 1.0
 ******************************************************************************/
public class Babysteps implements Plugin {

    private PluginManager pluginManager;
    private Duration duration = Duration.ofMinutes(3);
    private Timer timer = new Timer();

    @Override
    public void starting() {


        if (pluginManager == null)
            throw new IllegalStateException("The PluginManager has not been set. Please set the PluginManager before using this Plugin.");
        try {
            applySettingSetting();
        } catch (SettingException e) {
            System.err.println("COULD NOT LOAD SETTINGS FROM THE SETTINGSMANAGER");

        }
        System.err.println("STARTED");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run(){
                System.out.println("Your code has gone");
                resetCode();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, duration.toMillis());
    }

    @Override
    public void stopping() {
        timer.cancel();
    }

    @Override
    public void setPluginManager(PluginManager pluginManager) {
        if (pluginManager == null) throw new IllegalArgumentException();
        this.pluginManager = pluginManager;
    }

    private void applySettingSetting() throws SettingException {
        pluginManager.getSettingsManager().dump();
        Setting setting = pluginManager.getSettingsManager().loadSetting("babysteps");
        duration = Duration.ofMinutes(Long.parseLong(setting.getValue()));
    }

    private void resetCode() {
        if (pluginManager.getCycleManager().getCurrentPhase() == CycleEnum.TEST) {
            pluginManager.getTestManager().resetText();
        }

        if (pluginManager.getCycleManager().getCurrentPhase() == CycleEnum.CODE) {
            pluginManager.getCodeManager().resetText();
        }
    }

    protected Duration getDuration(){
        return duration;
    }
}