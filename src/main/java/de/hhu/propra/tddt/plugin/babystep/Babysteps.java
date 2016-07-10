package de.hhu.propra.tddt.plugin.babystep;

/**
 * Created by zeljko On 24.06.2016
 */

import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.plugin.Plugin;
import de.hhu.propra.tddt.plugin.PluginManager;
import de.hhu.propra.tddt.settings.Setting;
import de.hhu.propra.tddt.settings.SettingException;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/******************************************************************************
 * Class: Babysteps implements Plugin
 * <p>
 * Task: After waiting X Minutes it erase the source code and resets the current
 * phase. You can define the X in the minutes (you should be able to do it).
 * <p>
 * By default it has been set to 3 Minutes.
 *
 * @author zeljko
 * @version 0.2
 ******************************************************************************/
public class Babysteps implements Plugin {

    private PluginManager pluginManager;
    private Duration duration = Duration.ofMinutes(3);
    private Timer timer = new Timer();

    @Override
    public void start() {

        try {
            Setting setting = pluginManager.getSettingsManager().loadSetting("babysteps");
        } catch (SettingException e) {
            System.out.println("COULD NOT LOAD SETTINGS FROM THE SETTINGSMANAGER");
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                resetCode();
            }
        };
        timer.schedule(timerTask, duration.toMillis());

        /*
         * If you have exceeded the given time this plugin will reset the code
         * you wrote in this cycle element and reset the cycle
         */
    }

    @Override
    public void stop() {
        timer.cancel();
    }

    @Override
    public void setPluginManager(PluginManager pluginManager) {
        if(pluginManager == null) throw new IllegalArgumentException();
        this.pluginManager = pluginManager;
    }

    /**
     * Method: setDuration
     * <p>
     * Task: Setting the time of you have to write you code for each phase.
     *
     * @param duration a duration, where you can enter nearly every value.
     */
    public void setDuration(Duration duration) {
        if(duration == null) throw new IllegalArgumentException();
        this.duration = duration;
    }

    /**
     * Method: resetCode
     *
     * Task: Reseting the code depending on in which phase you are.
     */
    private void resetCode() {
        if (pluginManager.getCycleManager().getCurrentPhase() == CycleEnum.TEST) {
            pluginManager.getTestManager().resetText();
        }

        if (pluginManager.getCycleManager().getCurrentPhase() == CycleEnum.CODE) {
            pluginManager.getCodeManager().resetText();
        }
    }
}