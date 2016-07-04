package de.hhu.propra.tddt.cycle;

/**
 * Created by Subject-16 on 25.06.2016.
 */

/*********************************************************
 * Class: TestFail
 *
 * Task: First phase of the cycle, user gets prompted to write
 * a test which fails and/or their code isn't compilable.
 *
 *
 * @author Kevin
 ********************************************************/
public class TestFail extends AbstractCycleElement{

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
     * uses virtual kata, the code isn't allowed to compile here,
     * look at annotation 1 on the project7.pdf
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
     * only one test is allowed to fail in this phase
     *
     * @return: boolean expression
     ***************************************************************/

    @Override
    public boolean runTest(){

        return false;
    }


    /*********************************************************
     * Method: editTest
     *
     * function to edit the test in this cycle-phase,
     * return type not yet clear, hence the void
     *
     * @return: void for now
     ***************************************************************/

    public void editTest(){

    }

    /**
     * @TODO:
     * fill the class with code
     * are more methods needed?
     * **/
}
