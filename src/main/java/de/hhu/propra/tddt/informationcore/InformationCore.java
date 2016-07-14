package de.hhu.propra.tddt.informationcore;

import de.hhu.propra.tddt.contentmanager.TextManager;
import de.hhu.propra.tddt.cycle.CycleManager;
import de.hhu.propra.tddt.plugin.PluginManager;
import de.hhu.propra.tddt.settings.SettingsManager;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * Created by zeljko On 10.07.2016
 */

/**
 * The InformationCore is - as the name says - the core of the information the
 * program has. Here happens the interaction of the elements.
 *
 * @author zeljko
 * @version 1.0
 */
public class InformationCore implements PluginManager {

    private static InformationCore informationCore = new InformationCore();

    private TextManager codeManager = null;
    private TextManager testManager = null;
    private CycleManager cycleManager = null;
    private SettingsManager settingsManager = null;
    private Object compileManager = null;

    protected InformationCore(){
        //no constructor for you
    }

    public static InformationCore informationCore(){
        return informationCore;
    }

    /*
     * @TODO
     * bind the compileManager right here in and change the return type of the
     * method in the interface.
     */

    @Setter
    public void setCodeManager(TextManager codeManager) {
        this.codeManager = codeManager;
    }

    @Setter
    public void setCompileManager(Object compileManager) {
        this.compileManager = compileManager;

    }

    @Setter
    public void setCycleManager(CycleManager cycleManager) {
        this.cycleManager = cycleManager;
    }

    @Setter
    public void setSettingsManager(SettingsManager settingsManager) {
        this.settingsManager = settingsManager;
    }

    @Setter
    public void setTestManager(TextManager testManager) {
        this.testManager = testManager;
    }

    @Override
    public TextManager getCodeManager() {
        if(codeManager == null) throw new
                IllegalArgumentException("You have to set the CodeManager before you can pass it");
        return codeManager;
    }

    @Override
    public TextManager getTestManager() {
        if(testManager == null) throw new
                IllegalArgumentException("You have to set the TestManger before you can pass it");
        return testManager;
    }

    @Override
    public CycleManager getCycleManager() {
        if(cycleManager == null) throw new
                IllegalArgumentException("You have to set the CycleManager before you can pass it");
        return cycleManager;
    }

    @Override
    public SettingsManager getSettingsManager() {
        if(settingsManager == null) throw new
                IllegalArgumentException("You have to set the SettingsManager before you can pass it");
        return settingsManager;
    }

    @Override
    public Object getCompileManager() {
        if(compileManager == null) throw new
                IllegalArgumentException("You have to set the CompileManager before you can pass it");
        return compileManager;
    }
}
