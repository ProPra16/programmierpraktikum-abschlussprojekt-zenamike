package de.hhu.propra.tddt.compiler;

import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.util.classnameparser.ClassNameParser;
import de.hhu.propra.tddt.util.classnameparser.ClassNameParserException;
import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;

import static de.hhu.propra.tddt.compiler.CompileResults.setCycleError;

/**
 * Created by schiggy on 10.07.16.
 */
public class CompilerManager {
    boolean firstcyle = true;
    CycleEnum phase = CycleEnum.TEST;
    CompileResults compResult = new CompileResults();




    /**
     * Method: compileTest
     * <p>
     * Task: Method that checks if the compile button is clicked, if the right phase was
     * given to the method. If it got the right phase, the method compiles the test and if
     * there is only one failed test or the code doesn't compile then the method sets the phase to
     * the CODE phase.
     *
     * @param testCode String testCode is the whole test the user typed into the textbox
     * @param currentPhase CycleEnum currentPhase gives information to the method with which phase
     *                     the user is currently working. More a failsafe check for the backend
     *                     processes than for the user.
     *
     * @return returns the current phase in which the cycle is at the moment
     */

    public CycleEnum compileTest(String testCode, CycleEnum currentPhase)throws ClassNameParserException{
        boolean isARealTest = true;
        String testName = ClassNameParser.getClassName(testCode);
        CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
        JavaStringCompiler testComp;
        testComp = CompilerFactory.getCompiler(compilationTestUnit);
        testComp.compileAndRunTests();

        if (testComp.getCompilerResult().hasCompileErrors() && firstcyle) {
            phase = CycleEnum.CODE;
            return currentPhase;
        }else {setCycleError(1);}


        if (testComp.getCompilerResult().hasCompileErrors() && !firstcyle) {
            errorStringInit(testComp, compilationTestUnit);


        }else if (!testComp.getCompilerResult().hasCompileErrors() && !firstcyle) {
            compResult.setTestResults(testComp);

            if (testComp.getTestResult().getNumberOfFailedTests() == 1) {

                phase = CycleEnum.CODE;
                return phase;

            }else{
                setCycleError(3);
            }
        }

        return currentPhase;
    }


    /**
     * Method: compileCode
     * <p>
     * Task: Method that checks when the compile button is clicked, if the right phase was
     * given to the method. If it got the right phase, the method compiles the test and if
     * there is only one failed test or the code doesn't compile then the method sets the phase to
     * the CODE phase.
     *
     * @param testCode String testCode is the whole test the user typed into the textbox
     * @param code  String code is the whole code which is going to be tested
     * @param currentPhase CycleEnum currentPhase gives information to the method with which phase
     *                     the user is currently working. More a failsafe check for the backend
     *                     processes than for the user.
     *
     * @return returns the current phase in which the cycle is at the moment
     */



    public CycleEnum compileCode (String code, String testCode, CycleEnum currentPhase)throws ClassNameParserException{
        boolean isATest = false;
        boolean isARealTest = true;
        String className = ClassNameParser.getClassName(code);
        String testName = ClassNameParser.getClassName(testCode);
        CompilationUnit compilationUnit = new CompilationUnit(className, code, isATest);
        CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
        JavaStringCompiler testComp;
        JavaStringCompiler codeComp;
        testComp = CompilerFactory.getCompiler(compilationTestUnit);
        codeComp = CompilerFactory.getCompiler(compilationUnit);

        testComp.compileAndRunTests();
        codeComp.compileAndRunTests();

        if(testComp.getTestResult().getTestFailures().isEmpty()
                && !codeComp.getCompilerResult().hasCompileErrors()
                && testComp.getTestResult().getNumberOfIgnoredTests() == 0){

            phase = CycleEnum.REFACTOR;
            return phase;

        } else if (codeComp.getCompilerResult().hasCompileErrors()) {
            errorStringInit(codeComp, compilationUnit);
            compResult.setCodeResults(codeComp, compilationUnit);


        } else if (testComp.getCompilerResult().hasCompileErrors()) {
            errorStringInit(testComp, compilationTestUnit);
            compResult.setTestResults(testComp);
        }


        return currentPhase;

    }


    /**
     * Method: compileRefactor
     * <p>
     * Task: (does almost the same as codingPhase)
     * Method that checks when the compile button is clicked, if the right phase was
     * given to the method. If it got the right phase, the method compiles the test and all tests and
     * code compiles then the user is allowed to move to complete the cycle and move to the
     * the test phase again.
     *
     * @param testCode String testCode is the whole test the user typed into the textbox
     * @param code  String code is the whole code which is going to be tested
     * @param currentPhase CycleEnum currentPhase gives information to the method with which phase
     *                     the user is currently working. More a failsafe check for the backend
     *                     processes than for the user.
     *
     * @return returns the current phase in which the cycle is at the moment
     */

    public CycleEnum compileRefactor(String code, String testCode, CycleEnum currentPhase)throws ClassNameParserException{
        boolean isATest = false;
        boolean isARealTest = true;
        String className = ClassNameParser.getClassName(code);
        String testName = ClassNameParser.getClassName(testCode);
        CompilationUnit compilationUnit = new CompilationUnit(className, code, isATest);
        CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
        JavaStringCompiler testComp;
        JavaStringCompiler codeComp;
        testComp = CompilerFactory.getCompiler(compilationTestUnit);
        codeComp = CompilerFactory.getCompiler(compilationUnit);

        testComp.compileAndRunTests();
        codeComp.compileAndRunTests();


        if(testComp.getTestResult().getTestFailures().isEmpty()
                && !codeComp.getCompilerResult().hasCompileErrors()
                && testComp.getTestResult().getNumberOfIgnoredTests() == 0){

            phase = CycleEnum.TEST;
            return phase;

        } else if (codeComp.getCompilerResult().hasCompileErrors()) {
            errorStringInit(codeComp, compilationUnit);
            compResult.setCodeResults(codeComp, compilationUnit);


        } else if (testComp.getCompilerResult().hasCompileErrors()){
            errorStringInit(testComp,compilationTestUnit);
            compResult.setTestResults(testComp);
        }


        return currentPhase;
    }






    private void errorStringInit (JavaStringCompiler compiler, CompilationUnit cu){
        String errorString = "";
        for (CompileError compileError :
                compiler.getCompilerResult().getCompilerErrorsForCompilationUnit(cu)) {
            errorString = "Line " + compileError.getLineNumber() + ": " + compileError.getMessage() +
                    ": \n " + compileError.getCodeLineContainingTheError() + "\n" +
                    compileError.getMessage() + "\n";
            compResult.setCompileErrors(errorString);
        }

    }



}
