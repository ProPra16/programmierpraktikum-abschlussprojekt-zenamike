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

    private static final InformationCore informationcore = new InformationCore();

    private TextManager codeManager = null;
    private TextManager testManager = null;
    private CycleManager cycleManager = null;
    private SettingsManager settingsManager = null;
    private Object compileManager = null;

    protected InformationCore(){
        //no parameter is required, but instead of taking 5 parameter i am
    }

    /*
     * @TODO
     * bind the compileManager right here in and change the return type of the
     * method in the interface.
     */
    public static InformationCore informationCore(){
        return informationcore;
    }

    @Setter
    public void setCodeManager(TextManager codeManager) {
        if (this.codeManager != null) throw new IllegalStateException();
        if (this.codeManager == null) this.codeManager = codeManager;
    }

    @Setter
    public void setCompileManager(Object compileManager) {
        if (this.compileManager != null) throw new IllegalStateException();
        if (this.compileManager == null) this.compileManager = compileManager;

    }

    @Setter
    public void setCycleManager(CycleManager cycleManager) {
        if (this.cycleManager != null) throw new IllegalStateException();
        if (this.cycleManager == null) this.cycleManager = cycleManager;
    }

    @Setter
    public void setSettingsManager(SettingsManager settingsManager) {
        if (this.settingsManager != null) throw new IllegalStateException();
        if (this.settingsManager == null) this.settingsManager = settingsManager;
    }

    @Setter
    public void setTestManager(TextManager testManager) {
        if(this.testManager != null) throw new IllegalStateException();
        if(this.testManager == null) this.testManager = testManager;
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
