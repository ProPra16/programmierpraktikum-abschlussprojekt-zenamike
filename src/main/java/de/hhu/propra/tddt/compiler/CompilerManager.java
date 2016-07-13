package de.hhu.propra.tddt.compiler;

import java.util.LinkedList;


/**
 * Created by schiggy on 10.07.16.
 */
public class CompilerManager {


    Compiler compiler = new Compiler();
    LinkedList<String> CodeCompileMessages = new LinkedList<>();
    LinkedList<String> TestCompileMessages = new LinkedList<>();
    String errorMessage;


    public CompilerManager(){
        this.compiler = compiler;

    }



    /**
     * Method: compileCode
     * <p>
     * Task: Method that start the compilation process, if the code produces errors,
     * the linked list will be filled
     *
     *@param code needs the testcode as a string to have something to work with.
     *
     * @return LinkedList<String> compileResults
     *          in this order:
     *                  compile duration
     *                  compile errors
     */

    public LinkedList<String> compileCode (String code){
        try{
            CodeCompileMessages = compiler.compileCode(code);

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return CodeCompileMessages;
    }

    /**
     * Method: compileTest
     * <p>
     * Task: Method that start the compilation process for the tests, if the tests succeed
     * or produce any errors or is uncompileable this will be put in this linked list.
     *
     *@param testCode needs the testcode as a string to have something to work with.
     *
     * @return LinkedList<String> testResults
     *              in this order:
     *                  failed tests
     *                  ignored tests
     *                  successful tests
     *                  test duration
     *                  test fail messages
     */

    public LinkedList<String> compileTest (String testCode){
        try{
            TestCompileMessages = compiler.compileTest(testCode);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return TestCompileMessages;
    }


    /**
     * Method: setCycleError
     * <p>
     * Task: Method that decides which error is saved to a String so the GUI can
     * pop an alarm box
     *
     * @param setError tells clearly which errors it's going to be
     *
     * @return void
     */


    public void setCycleError (int setError){  // remove or set somewhere else

        switch(setError){

            case 1: errorMessage = "Code is not allowed to compile here";
                break;
            case 2: errorMessage = "You can't change into the same phase again.";
                break;
            case 3: errorMessage = "You shallow not pass since something does not work, check" +
                    "the following: ";
                break;
            default: errorMessage = null;
        }

    }

    /**
     * Method: getCycleError
     * <p>
     * Task: Method that just gives the errorMessage String to the GUI
     *
     *
     * @return errorMessage
     */

    public String getCycleError(){
        return errorMessage;
    }


}

