package de.hhu.propra.tddt.plugin;

/**
 * Created by zeljko On 24.06.2016
 */

import de.hhu.propra.tddt.cycle.CycleEnum;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/******************************************************************************
 * Class: Babysteps implements Plugin
 * <p>
 * Task: After waiting X Minutes it erase the source code and resets the current
 * phase. You can define the X in the minutes (you should be able to do it).
 * <p>
 * By default it has been set to 3 Minutes.
 *
 * @author zeljko
 * @version 0.1
 ******************************************************************************/

 /*
  * @TODO
  * 1. Finish this program.
  * 2. Write a test for this class to test if it really works how intended.
  * 3. Be happy.
  */
public class Babysteps implements Plugin {

    PluginManager pluginManager;
    Duration duration = Duration.ofMinutes(3);
    Timer timer = new Timer();

    @Override
    public void start() {
        /*
         * @TODO Pull information from the SettingsManager
         */

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                LocalDateTime localDateTime2 = LocalDateTime.now();
                resetCode();
            }
        };
        LocalDateTime localDateTime1 = LocalDateTime.now();
        timer.schedule(timerTask, 10000);

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
        this.pluginManager = pluginManager;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    protected void resetCode() {
        if (pluginManager.getCycleManager().getCurrentPhase() == CycleEnum.TEST) {
            pluginManager.getTestManager().resetText();
        }

        if (pluginManager.getCycleManager().getCurrentPhase() == CycleEnum.CODE) {
            pluginManager.getCodeManager().resetText();
        }
    }
}