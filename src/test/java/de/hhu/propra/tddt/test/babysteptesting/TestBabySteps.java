package de.hhu.propra.tddt.test.babysteptesting;

import de.hhu.propra.tddt.cycle.CycleManager;
import de.hhu.propra.tddt.contentmanager.TextManager;
import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.informationcore.InformationCore;
import de.hhu.propra.tddt.plugin.babystep.Babysteps;
import de.hhu.propra.tddt.plugin.PluginManager;
import de.hhu.propra.tddt.settings.SettingsManager;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

/**
 * Created by zeljko On 01.07.2016
 */
public class TestBabySteps {

    /*
     * This test verifies that the code reset of babysteps is reseting the text
     */
    @Test
    public void testTextReset() throws InterruptedException {

        //Custom PluginManager and TextManager for this test
        TextManager textManager = new TextManager("hello");
        textManager.updatePhaseSave("hel");

        PluginManager pluginManager = new StubInformationCore();
        ((StubInformationCore) pluginManager).setCodeManager(textManager);
        ((StubInformationCore) pluginManager).setCycleManager(new StubCycleManagerCycleCODE());
        ((StubInformationCore) pluginManager).setSettingsManager(new SettingsManager());

        Babysteps babysteps = new Babysteps();
        babysteps.setDuration(Duration.ofSeconds(2));
        babysteps.setPluginManager(pluginManager);
        babysteps.start();

        Thread.sleep(Duration.ofSeconds(5).toMillis());

        Assert.assertEquals(textManager.getText(), "hel");
    }

    /*
     * This test verifies that the code will not be reseted if you stop the plugin
     */
    @Test
    public void testNotTextReset() {
        //Custom PluginManager and TextManager for this test
        TextManager textManager = new TextManager("hello");
        textManager.updatePhaseSave("hel");

        PluginManager pluginManager = new StubInformationCore();
        ((StubInformationCore) pluginManager).setCodeManager(textManager);
        ((StubInformationCore) pluginManager).setCycleManager(new StubCycleManagerCycleCODE());
        ((StubInformationCore) pluginManager).setSettingsManager(new SettingsManager());

        Babysteps babysteps = new Babysteps();
        //To ensure the other thread will stop the babysteps process

        Duration duration = Duration.ofHours(2);
        babysteps.setDuration(duration);
        babysteps.setPluginManager(pluginManager);
        babysteps.start();
        try {
            Thread.sleep(Duration.ofSeconds(5).toMillis());
        } catch (InterruptedException e) {

        }
        babysteps.stop();


        Assert.assertEquals("hello", textManager.getText());


    }
}

class StubCycleManagerCycleCODE extends CycleManager {
    @Override
    public CycleEnum getCurrentPhase() {
        return CycleEnum.CODE;
    }
}

class StubInformationCore extends InformationCore {
    public StubInformationCore(){

    }
}