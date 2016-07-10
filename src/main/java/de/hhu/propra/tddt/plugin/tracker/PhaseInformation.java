package de.hhu.propra.tddt.plugin.tracker;

import de.hhu.propra.tddt.cycle.CycleEnum;

import java.time.Duration;
import java.util.List;

/**
 * Created by zeljko On 09.07.2016
 */
public class PhaseInformation {
    CycleEnum phase = null;
    Duration duration = null;
    List<String> savedHistroy = null;

    public PhaseInformation(final CycleEnum phase,final Duration duration, List<String> history){
        this.phase = phase;
        this.duration = duration;
    }



}
