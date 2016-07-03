package de.hhu.propra.tddt.contentmanager;

import javafx.scene.control.TextField;

/**
 * Created by zeljko On 30.06.2016
 */

/**
 *
 */
public class TextManager {

    protected TextField textField;
    protected String string;
    protected String phaseSave;

    public TextManager(final TextField textField) {
        if (textField == null) throw new IllegalArgumentException();
        this.textField = textField;
        string = null;
    }

    public TextManager(final String string) {
        if (string == null) throw new IllegalArgumentException();
        this.string = string;
        textField = null;
    }

    public void resetText() {
        if(phaseSave == null) phaseSave = "";
        if (string == null) textField.setText(phaseSave);
        if (textField == null) string = phaseSave;
    }

    public String getText() {
        if (string == null) return textField.getText();
        if (textField == null) return string;
        return null;
    }

    public void updatePhaseSave(String code) {
        this.phaseSave = code;
    }

    public String getPhaseSave(){
        return phaseSave;
    }
}
