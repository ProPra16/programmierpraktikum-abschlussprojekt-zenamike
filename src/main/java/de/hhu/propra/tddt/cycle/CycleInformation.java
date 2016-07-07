package de.hhu.propra.tddt.cycle;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.TestFailure;
import vk.core.internal.InternalCompiler;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kevin on 07.07.2016.
 */

/*********************************************************
 * Class: CycleInformation
 * <p>
 * Task: Saves the information the internal Compiler produces into an ArrayList
 * from type String, so that the GUI can work with the errors thrown
 * by the Internal compiler.
 *
 * @author Kevin
 ********************************************************/



public class CycleInformation {
    ArrayList<String> testResults = new ArrayList<>();
    ArrayList<String>compileResults = new ArrayList<>();


    String failedTests = null;
    String ignoredTests = null;
    String successfulTests = null;
    String testDuration = null;
    String testMessage = null;

    String compileErrors = null;
    String compileDuration = null;
    String compileMessage = null;

    String errorMessage = null;


    /**
     * Method: setTestResults
     * <p>
     * Task: Method that saves the errors/information from the internal compiler into
     * an ArrayList.
     *
     * @param internalCompiler so that method can access the information
     *
     * @return void
     */

    protected void setTestResults(InternalCompiler internalCompiler){
        int failedTestsNumber = internalCompiler.getTestResult().getNumberOfFailedTests();
        failedTests = Integer.toString(failedTestsNumber);
        int ignoredTestsNumber = internalCompiler.getTestResult().getNumberOfIgnoredTests();
        ignoredTests = Integer.toString(ignoredTestsNumber);
        int successfulTestsNumber = internalCompiler.getTestResult().getNumberOfSuccessfulTests();
        successfulTests = Integer.toString(successfulTestsNumber);
        Duration testDurationTime = internalCompiler.getTestResult().getTestDuration();
        testDuration = testDurationTime.toString();
        Collection<TestFailure> testFailures = internalCompiler.getTestResult().getTestFailures();
        testMessage = testFailures.toString();

        testResults.add(failedTests);
        testResults.add(ignoredTests);
        testResults.add(successfulTests);
        testResults.add(testDuration);
        testResults.add(testMessage);
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

    public ArrayList<String> getTestResults(){

        return testResults;
    }

    /**
     * Method: setCompileResults
     * <p>
     * Task: Method that saves the errors/information from the internal compiler into
     * an ArrayList.
     *
     * @param internalCompiler so that method can access the information
     * @param compilationUnit is needed for the CompileError collection in order to
     *                        specify with CompilationUnit should be looked at.
     *
     * @return void
     */

    protected void setCompileResults(InternalCompiler internalCompiler, CompilationUnit compilationUnit){
        boolean hasItCompileErrors = internalCompiler.getCompilerResult().hasCompileErrors();
        compileErrors = Boolean.toString(hasItCompileErrors);
        Duration compileDurationTime = internalCompiler.getCompilerResult().getCompileDuration();
        compileDuration = compileDurationTime.toString();
        Collection<CompileError> compileMessages = internalCompiler.getCompilerResult().getCompilerErrorsForCompilationUnit(compilationUnit);
        compileMessage = compileMessages.toString();


        compileResults.add(compileErrors);
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
     * @return ArrayList<String> compileResults
     *          in this order:
     *                  compileErrors
     *                  compileDuration
     *                  compileMessage
     */

    public ArrayList<String> getCompileResults(){
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


    protected void setCycleError (int setError){
        switch(setError){
            case 1: errorMessage = "Code is not allowed to compile here";
                break;
            case 2: errorMessage = "You can't change into the same phase again.";
                break;
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
