package de.hhu.propra.tddt.compiler;

import java.util.LinkedList;

/**
 * Created by schiggy on 10.07.16.
 */
public class CompilerManager {



    /*********************************************************
     * Class: CompilerManager
     * <p>
     * Task: Class handling the results from the compiler and
     * the calls from the gui
     *
     * @author Kevin
     * @version the current version is changed in order
     * to work with the information core
     ********************************************************/

    Compiler compiler = new Compiler();
    LinkedList<String> TestCompileMessages = new LinkedList<>();
    LinkedList<String>CodeCompileMessages = new LinkedList<>();
    LinkedList<LinkedList<String>> compileResultList = new LinkedList<>();

    int compilationNumber = 0;


    /**
     *
     *
     * /** Method: compileTest
     * <p>
     * Task: Method that starting the compilation process for the tests, if the
     * tests succeed or produce any errors or is uncompileable this will be put
     * in this linked list.
     *
     * @param testCode
     *            needs the testcode as a string to have something to work with.
     *
     *
     * @param code
     *          needs also the code from the right textfield to work
     *
     *            the list will look like this LinkedList<String> testResults
     *            failed tests ignored tests successful tests test duration test
     *            fail messages
     *
     * @return void
     */

    public void compileTest(String code, String testCode) {
        try {
            TestCompileMessages = compiler.compileTest(code, testCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        addResultsIntoCompileResultList(TestCompileMessages);
        CodeCompileMessages = compiler.getCompilerErrors();
        addResultsIntoCompileResultListAtPlaceOne(CodeCompileMessages);

    }


    /**
     * Method: addResultsIntoCompileResultList
     * <p>
     * adds the result into one bigger list
     *
     * @param list
     *            the result list compileTest
     *
     * @return void
     */

    private void addResultsIntoCompileResultList(LinkedList<String> list) {
        compileResultList.add(0, list);
        compilationNumber++;
    }


    /**
     * Method: addResultsIntoCompileResultListAtPlaceOne
     * <p>
     * adds the compile errors to the list
     *
     * @param list
     *            the compile errors as a list
     *
     * @return void
     */


    private void addResultsIntoCompileResultListAtPlaceOne(LinkedList<String> list) {
        compileResultList.add(1, list);
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

    public LinkedList<LinkedList<String>> getCompileResultList() {
        return compileResultList;
    }

    /**
     * Method: getCompilationNumber
     * <p>
     * gives the compilation number to the caller, so that one knows how many
     * times the programm compilated sth
     *
     *
     * @return compilationNumber
     */

    public int getCompilationNumber() {
        return compilationNumber;
    }

}