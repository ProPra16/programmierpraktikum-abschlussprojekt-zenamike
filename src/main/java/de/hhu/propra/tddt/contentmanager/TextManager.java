package de.hhu.propra.tddt.contentmanager;

import javafx.scene.control.TextArea;
import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Created by zeljko On 30.06.2016
 */

/**
 * Class: TextManager
 * <p>
 * Task: Handling the Text which will be pulled from the a Textfield from the
 * GUI or from a String.
 *
 * @author zeljko
 * @version 0.1
 */
public class TextManager {

    protected TextArea textArea;
    protected String string;
    protected String phaseSave;

    /**
     * Constructor: TextManager
     *
     * @param textArea the textfield from where the text will be pulled
     */
    public TextManager(final TextArea textArea) {
        if (textArea == null) throw new IllegalArgumentException();
        this.textArea = textArea;
        string = null;
    }

    /**
     * The String from where the text will be taken.
     *
     * @param string
     */
    public TextManager(final String string) {
        if (string == null) throw new IllegalArgumentException();
        this.string = string;
        textArea = null;
    }

    /**
     * Method: resetText
     * <p>
     * Task: Reset the text to the last phase save. It is important to set a
     * phase save before calling this method, otherwise will this method set the
     * phase save to an empty string.
     */
    public void resetText() {
        if (phaseSave == null) phaseSave = "";
        if (string == null) textArea.setText(phaseSave);
        if (textArea == null) string = phaseSave;
    }

    @Getter
    public String getText() {
        if (string == null) return textArea.getText();
        if (textArea == null) return string;
        return null;
    }

    /**
     * Method: updatePhaseSave
     *
     * Task: Assign the phase save a new value which is not null
     */
    public void updatePhaseSave(String code) {
        if(code == null) throw new IllegalArgumentException();
        this.phaseSave = code;
    }

    @Getter
    public String getPhaseSave() {
        return phaseSave;
    }
}
