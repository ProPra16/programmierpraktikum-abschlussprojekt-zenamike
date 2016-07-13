package de.hhu.propra.tddt.compiler;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestFailure;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by schiggy on 10.07.16.
 */
public class CompileResults {

    /*********************************************************
            * Class: CompileResults
    * <p>
    * Task: Saves the information the internal Compiler produces into an LinkedList
    * from type String, so that the GUI can work with the errors thrown
    * by the Internal compiler.
     * *
     * @author Kevin
    ********************************************************/


    LinkedList<String> testResults;
    LinkedList<String>compileResults;


    String failedTests = null;
    String ignoredTests = null;
    String successfulTests = null;
    String testDuration = null;
    String compileDuration = null;


    public CompileResults(){
        testResults = new LinkedList();
        compileResults = new LinkedList();

    }






        /**
         * Method: setCompileErrors
         * <p>
         * Task: Method that saves the compileerrors in one convinent linked list
         *
         * @param compileErrors A preprade string from Cycle.java which is nicely formated
         *
         * @return void
         */



        protected void addCompileErrors(String compileErrors){
            compileResults.add(compileErrors);
        }





        /**
         * Method: setTestResults
         * <p>
         * Task: Method that saves the errors/information from the internal compiler into
         * an ArrayList.
         *
         * @param compiler so that method can access the information
         *
         * @return void
         */

        protected void setTestResults(JavaStringCompiler compiler){
            int failedTestsNumber = compiler.getTestResult().getNumberOfFailedTests();
            failedTests = Integer.toString(failedTestsNumber);
            int ignoredTestsNumber = compiler.getTestResult().getNumberOfIgnoredTests();
            ignoredTests = Integer.toString(ignoredTestsNumber);
            int successfulTestsNumber = compiler.getTestResult().getNumberOfSuccessfulTests();
            successfulTests = Integer.toString(successfulTestsNumber);
            Duration testDurationTime = compiler.getTestResult().getTestDuration();
            testDuration = Long.toString(testDurationTime.getSeconds());
            Collection<TestFailure> testFailures = compiler.getTestResult().getTestFailures();
            String testFailureCollectionString ="";
            if (!testFailures.isEmpty()) {
                String arr[] = new String[testFailures.size()];
                testFailures.toArray(arr);
                testFailureCollectionString = Arrays.toString(arr);
            }


            testResults.add(failedTests);
            testResults.add(ignoredTests);
            testResults.add(successfulTests);
            testResults.add(testDuration);
            testResults.add(testFailureCollectionString);

        }

        /**
         * Method: getTestResults
         * <p>
         * Task: Method that makes the information from setTestResults accessable.
         * **important** for the GUI
         *
         *
         *
         * @return LinkedList<String> testResults
         *              in this order:
         *                  failed tests
         *                  ignored tests
         *                  successful tests
         *                  test duration
         *                  test fail messages
         */

        protected LinkedList<String> getTestResults(){

            return testResults;
        }

        /**
         * Method: setCompileResults
         * <p>
         * Task: Method that saves the errors/information from the internal compiler into
         * an ArrayList.
         *
         * @param compiler so that method can access the information
         * @param compilationUnit is needed for the CompileError collection in order to
         *                        specify with CompilationUnit should be looked at.
         *
         * @return void
         */

        protected void setCodeResults(JavaStringCompiler compiler, CompilationUnit compilationUnit){
            Duration compileDurationTime = compiler.getCompilerResult().getCompileDuration();
            compileDuration = Long.toString(compileDurationTime.getSeconds());
            compileResults.add(compileDuration);
            errorStringInit(compiler, compilationUnit);


        }

        /**
         * Method: getCompileResults
         * <p>
         * Task: Method that makes the information from setTestResults accessable.
         * **important** for the GUI
         *
         *
         *
         * @return LinkedList<String> compileResults
         *          in this order:
         *                  compile duration
         *                  compile errors
         */

        protected LinkedList<String> getCompileResults(){
            return compileResults;
        }



    /**
     * Method: errorStringInit
     * <p>
     * Task: gets the compile errors and formats them in a readable way
     *
     *
     * @return void
     */

    protected void errorStringInit (JavaStringCompiler compiler, CompilationUnit cu){
        String errorString = "";
        for (CompileError compileError :
                compiler.getCompilerResult().getCompilerErrorsForCompilationUnit(cu)) {
            errorString = "Line " + compileError.getLineNumber() + ": " + compileError.getMessage() +
                    ": \n " + compileError.getCodeLineContainingTheError() + "\n" +
                    compileError.getMessage() + "\n";
            addCompileErrors(errorString);
        }

    }
}

