package de.hhu.propra.tddt.informationcore;

/**
 * Created by zeljko On 10.07.2016
 */

/**
 * The Singelton pattern accomplishes, that a class has only one instance of an
 * object. In this case it is totally useless to have multiple instaces of
 * InformationCore and therefore it is meaningfull to use this pattern.
 * <p>
 * Furthermore it is easier to access the InformationCore with this pattern.
 *
 * @author zeljko
 * @version 1.0
 */
public class SingeltonInformationCore {

    private static InformationCore informationcore = null;

    private SingeltonInformationCore() {

    }

    /**
     * @return Returns (only one and everytime the same) instance of the
     * InformationCore class.
     */
    public static InformationCore getInformationCore() {
        if (informationcore == null) informationcore = new InformationCore();
        return informationcore;
    }
}
