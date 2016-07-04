package de.hhu.propra.tddt.cycle;

/**
 * Created by Subject-16 on 25.06.2016.
 */


/*********************************************************
 * Class: TestSucceed
 *
 * Task: Second phase of the cycle, user is allowed to change
 * code in this phase, in order that all tests are working. The
 * user can also switch back to phase one, but their code(NOT TEST)
 * will be deleted.
 *
 * @author Kevin
 ********************************************************/
public class TestSucceed extends AbstractCycleElement {

    /*********************************************************
     * Method: moveToNextPhase
     *
     * Button click event handler, calls compileCode and runTest
     * only if both return true, the user is allow to move on.
     *
     * @return: void
     ***************************************************************/

    @Override
    public void moveToNextPhase() {

    }

    /*********************************************************
     * Method: compileCode
     *
     * uses virtual kata here, just use the basic function of virtual kata
     *
     * discuss with others if this is really necessary here though
     *
     * @return: boolean expression
     ***************************************************************/

    @Override
    public boolean compileCode(){

        return false;
    }

    /*********************************************************
     * Method: runTest
     *
     * check so that after the improvement of the code,
     * the program is still working after editCode()
     *
     * @return: boolean expression
     ***************************************************************/

    @Override
    public boolean runTest(){

        return false;
    }

    /*********************************************************
     * Method: returnToTestFail
     *
     * Button click event handler, allows the user to move back to
     * the first phase, resulting in deleting everything except
     * what the user did in the TESTFAIL phase
     *
     * @return: void
     ***************************************************************/
    public void returnToTestFail(){

    }

    /*********************************************************
     * Method: editTest
     *
     * function to edit the code, which is tested, in this cycle-phase
     * so that all tests are working
     *
     * @return: void for now
     ***************************************************************/

    public void editCode(){

    }


    /**
     * @TODO:
     * fill the class with code
     * are more methods needed?
     * **/
}
