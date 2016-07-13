package de.hhu.propra.tddt.plugin.tracker;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by zeljko On 11.07.2016
 */
public class TimeCalculator {

    private static LocalTime start;

    public static void startTime() {
        if (start == null) start = LocalTime.now();
    }

    public static Duration endTime() {
        if (start == null) throw new NullPointerException(
                "You have not started the time calculation. You have to call " +
                        "startTime() before you call this method.");
        Duration duration = Duration.between(start, LocalTime.now());
        start = null;
        return duration;
    }
}
