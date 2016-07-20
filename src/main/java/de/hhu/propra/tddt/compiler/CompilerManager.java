package de.hhu.propra.tddt.compiler;

import java.util.LinkedList;


/**
 * Created by schiggy on 10.07.16.
 */
public class CompilerManager {


    Compiler compiler = new Compiler();
    LinkedList<String> CodeCompileMessages = new LinkedList<>();
    LinkedList<String> TestCompileMessages = new LinkedList<>();
    LinkedList<LinkedList<String>> compileResultList = new LinkedList<>();
    String errorMessage;
    int compilationNumber = 0;


    public CompilerManager(){
        this.compiler = compiler;

    }



    /**
     * Method: compileCode
     * <p>
     * Task: Method that starting the compilation process, if the code produces errors,
     * the linked list will be filled
     *
     *@param code needs the testcode as a string to have something to work with.
     *
     * the list will look like this
     *            LinkedList<String> compileResults
     *                  compile duration
     *                  compile errors
     *
     *@return void
     */

    public void compileCode (String code){
        try{
            CodeCompileMessages = compiler.compileCode(code);

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        addResultsIntoCompileResultList(CodeCompileMessages);
    }

    /**
     * Method: compileTest
     * <p>
     * Task: Method that starting the compilation process for the tests, if the tests succeed
     * or produce any errors or is uncompileable this will be put in this linked list.
     *
     *@param testCode needs the testcode as a string to have something to work with.
     *
     * the list will look like this
     *                LinkedList<String> testResults
     *                  failed tests
     *                  ignored tests
     *                  successful tests
     *                  test duration
     *                  test fail messages
     *
     *@return
     */

    public void compileTest (String testCode){
        try{
            TestCompileMessages = compiler.compileTest(testCode);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        addResultsIntoCompileResultList(TestCompileMessages);
    }


    /****
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


    /**
     * Method: addResultsIntoCompileResultList
     * <p>
     * adds both result list into one list
     *
     * @param list the result list from either compileCode or compileTest
     *
     * @return void
     */

    private void addResultsIntoCompileResultList (LinkedList<String> list){
        compileResultList.add(0, list);
        compilationNumber++;
    }

    /**
     * Method: getCompileResultList
     * <p>
     * gives the result list to the caller
     *
     *
     * @return LinkedList<LinkedList<String>> compileResultList
     */


    public LinkedList<LinkedList<String>> getCompileResultList(){
        return compileResultList;
    }

    /**
     * Method: getCompilationNumber
     * <p>
     * gives the compilation number to the caller, so that one knows
     * how many times the programm compilated sth
     *
     *
     * @return compilationNumber
     */

    public int getCompilationNumber(){
        return compilationNumber;
    }

}
