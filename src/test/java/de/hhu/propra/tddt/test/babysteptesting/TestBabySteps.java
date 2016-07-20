package de.hhu.propra.tddt.test.babysteptesting;

import de.hhu.propra.tddt.contentmanager.TextManager;
import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.cycle.CycleManager;
import de.hhu.propra.tddt.informationcore.InformationCore;
import de.hhu.propra.tddt.plugin.PluginManager;
import de.hhu.propra.tddt.plugin.babystep.Babysteps;
import de.hhu.propra.tddt.settings.Setting;
import de.hhu.propra.tddt.settings.SettingException;
import de.hhu.propra.tddt.settings.SettingsManager;
import org.junit.Assert;
import org.junit.Test;

import javax.rmi.CORBA.Stub;
import javax.sound.midi.MidiDevice;
import java.time.Duration;

/**
 * Created by zeljko On 01.07.2016
 */
public class TestBabySteps {

    /*
     * This test verifies that the code reset of babysteps is reseting the text
     */


    @Test
    public void testApplySettings(){
        InformationCore pluginManager = StubInformationCore.informationCore();
        ((InformationCore) pluginManager).setSettingsManager(new StubSettingsManager2Minutes());
        StubAccessDurationBabysteps babysteps = new StubAccessDurationBabysteps();
        babysteps.setPluginManager(pluginManager);
        babysteps.start();

        try {
            Thread.sleep(Duration.ofMinutes(3).toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(Duration.ofMinutes(2), babysteps.getDuration());
    }

    @Test
    public void testTextReset() throws InterruptedException {

        //Initializing the StubInformationCore as an PluginManager and setting the one special value
        InformationCore pluginManager = StubInformationCore.informationCore();
        ((InformationCore) pluginManager).setSettingsManager(new StubSettingsManager2Minutes());

        Babysteps babysteps = new Babysteps();
        babysteps.setPluginManager(pluginManager);
        babysteps.start();

        Thread.sleep(Duration.ofSeconds(5).toMillis());

        Assert.assertEquals(pluginManager.getCodeManager().getText(), "hel");
    }

    /*
     * This test verifies that the code will not be reseted if you stop the plugin
     */
    @Test
    public void testNotTextReset() {

        InformationCore pluginManager = StubInformationCore.informationCore();
        ((InformationCore) pluginManager).setSettingsManager(new SettingsManager());

        //Initializing and starting babysteps
        Babysteps babysteps = new Babysteps();
        babysteps.setPluginManager(pluginManager);
        babysteps.start();

        try {
            Thread.sleep(Duration.ofSeconds(5).toMillis());
        } catch (InterruptedException e) {

        }
        babysteps.stop();

        Assert.assertEquals("hello", pluginManager.getCodeManager().getText());
    }
}

class StubCycleManagerCycleCODE extends CycleManager {
    @Override
    public CycleEnum getCurrentPhase() {
        return CycleEnum.CODE;
    }
}

class StubInformationCore extends InformationCore {
    private static InformationCore informationCore = InformationCore.informationCore();

    private StubInformationCore() {
        super();
    }

    public static InformationCore informationCore(){
        informationCore.setCodeManager(new TextManager("hello"));
        informationCore.getCodeManager().updatePhaseSave("hel");
        informationCore.setCycleManager(new StubCycleManagerCycleCODE());
        return informationCore;
    }
}

class StubSettingsManager2Minutes extends SettingsManager {
    public StubSettingsManager2Minutes() {
        try {
            super.addSetting(new Setting("babysteps", "2"));
        } catch (SettingException e) {
            System.err.println("Sir, we got a problem over here");
        }
    }
}

class StubAccessDurationBabysteps extends Babysteps {

    @Override
    protected Duration getDuration() {
        return super.getDuration();
    }
}