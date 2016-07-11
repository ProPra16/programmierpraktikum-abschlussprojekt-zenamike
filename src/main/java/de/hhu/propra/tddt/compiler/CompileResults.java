package de.hhu.propra.tddt.compiler;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestFailure;

import java.time.Duration;
import java.util.ArrayList;
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
    LinkedList<String> compilerErrorList;
    static String errorMessage;

    String failedTests = null;
    String ignoredTests = null;
    String successfulTests = null;
    String testDuration = null;
    String compileDuration = null;
    String compileMessage = null;


    public CompileResults(){
        testResults = new CustomLinkedList();
        compileResults = new CustomLinkedList();
        compilerErrorList = new CustomLinkedList();
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



        protected void setCompileErrors(String compileErrors){
            compilerErrorList.add(compileErrors);
        }

        /**
         * Method: getCompilerErrors
         * <p>
         * Task: Method that makes the LinkedList from setCompileErrors accessable
         *
         *
         * @return LinkedList compilerErrorList
         */

        public LinkedList<String> getCompilerErrors(){
            return compilerErrorList;
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
         * @return ArrayList<String> testResults
         *              in this order:
         *                  failedTests
         *                  ignoredTests
         *                  successfulTests
         *                  testDuration
         *                  testMessage
         */

        public LinkedList<String> getTestResults(){

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
            Collection<CompileError> compileMessages = compiler.getCompilerResult().getCompilerErrorsForCompilationUnit(compilationUnit);

            if (!compileMessages.isEmpty()) {
                String arr[] = new String[compileMessages.size()];
                compileMessages.toArray(arr);
                compileMessage = Arrays.toString(arr);
            }

            compileResults.add(compileDuration);
            compileResults.add(compileMessage);
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
         *                  compileErrors
         *                  compileDuration
         *                  compileMessage
         */

        public LinkedList<String> getCompileResults(){
            return compileResults;
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


        public static void setCycleError (int setError){

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


class CustomLinkedList extends LinkedList{

    @Override
    public String toString() {
        String result = "";
        for (int j = 0; j<size(); j++) result += get(j);
        return result;
    }
        }