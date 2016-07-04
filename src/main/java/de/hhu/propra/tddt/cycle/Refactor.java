package de.hhu.propra.tddt.cycle;

/**
 * Created by Subject-16 on 25.06.2016.
 */

/*********************************************************
 * Class: Refactor
 * <p>
 * Task: Everything regarding the refactor phase of the program will happen
 * here. Entering the refactor phase and leaving it means the user code and test
 * have to be checked.
 *
 * @author Kevin
 ********************************************************/
public class Refactor extends AbstractCycleElement {

    /*********************************************************
     * Method: moveToNextPhase
     * <p>
     * Button click event handler, calls compileCode and runTest only if both
     * return true, the user is allow to move on.
     *
     * @return: void
     ***************************************************************/

    @Override
    public void moveToNextPhase() {

    }

    /*********************************************************
     * Method: compileCode
     * <p>
     * does what the name says, uses virtual kata.
     *
     * @return: boolean expression
     ***************************************************************/


    @Override
    public boolean compileCode() {

        return false;
    }

    /*********************************************************
     * Method: runTest
     * <p>
     * re-checks so that after the improvement of the code, the program is still
     * working
     *
     * @return: boolean expression
     ***************************************************************/

    @Override
    public boolean runTest() {

        return false;
    }

    /*********************************************************
     * Method: improveCode
     * <p>
     * User is allowed to improve their code in order to start a new cycle or
     * finish the exercise
     *
     * @return: boolean expression
     ***************************************************************/

    public void improveCode() {

    }

    /**
     * @TODO:
     * fill the class with code
     * think about the abstract class
     * are more methods needed?
     * **/


}
