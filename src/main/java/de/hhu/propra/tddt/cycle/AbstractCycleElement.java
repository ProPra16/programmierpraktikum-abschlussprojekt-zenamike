package de.hhu.propra.tddt.cycle;

/**
 * Created by Subject-16 on 25.06.2016.
 */

/*********************************************************
 * Class: AbstractCycleElement
 * <p>
 * Task: Used to bring structure into the program, nothing really happens here
 * since all is abstract and has to be implemented in the children of this
 * class.
 *
 * @author Kevin
 ********************************************************/


public abstract class AbstractCycleElement {

    public abstract void moveToNextPhase();

    public abstract boolean compileCode();

    public abstract boolean runTest();

    /**
     * @TODO:
     * think about the abstract class, is it really
     * necessary?
     *
     * **/

}
