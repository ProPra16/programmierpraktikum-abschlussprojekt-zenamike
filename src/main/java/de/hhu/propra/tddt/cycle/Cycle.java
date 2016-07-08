package de.hhu.propra.tddt.cycle;

/**
 * Created by zeljko On 23.06.2016
 */


import de.hhu.propra.tddt.util.classnameparser.ClassNameParser;
import de.hhu.propra.tddt.util.classnameparser.ClassNameParserException;
import vk.core.api.CompilationUnit;
import vk.core.internal.InternalCompiler;



/*********************************************************
 * Class: Cycle
 * <p>
 * Task: Class handling the other class objects. The whole process should happen
 * here. Probably needs additional help functions The javaFX magics gonna happen
 * here (responding to the gui)
 *
 * @author Kevin
 ********************************************************/
public class Cycle {

    CycleEnum phase = CycleEnum.TEST;
    CycleInformation cycleInfo = new CycleInformation();


    /**
     * Method: testPhase
     * <p>
     * Task: Method that checks if the compile button is clicked, if the right phase was
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

    public CycleEnum testingPhase(String testCode, String code, CycleEnum currentPhase)throws ClassNameParserException {

        if (currentPhase.equals(CycleEnum.TEST)) {
            boolean isATest = false;
            boolean isARealTest = true;
            String className = ClassNameParser.getClassName(code);
            String testName = ClassNameParser.getClassName(testCode);
            CompilationUnit compilationUnit = new CompilationUnit(className, code, isATest);
            CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
            CompilationUnit compArray [] = new CompilationUnit[2];
            compArray [0] = compilationUnit;
            compArray [1] = compilationTestUnit;

            InternalCompiler internalCompiler = new InternalCompiler(compArray);
            internalCompiler.compileAndRunTests();


            if (internalCompiler.getTestResult().getNumberOfFailedTests() != 1) {
                cycleInfo.setTestResults(internalCompiler);
                System.out.println("your tests sucks you're in !=1");

            }else if (internalCompiler.getTestResult().getNumberOfFailedTests() == 1){
                currentPhase = CycleEnum.CODE;
                System.out.println("you were in == 1");
                return currentPhase;

            }else if (internalCompiler.getCompilerResult().hasCompileErrors()==true);
                cycleInfo.setCycleError(1);
            System.out.println("hahalol error");

                return currentPhase;

        } else {
            throw new IllegalStateException("Wrong function call");

        }


    }


    /**
     * Method: resetPhase
     * <p>
     * Task: Method that checks if the user is in the right phase to reset their code.
     * If not they'll get notified.
     *
     *
     * @param currentPhase CycleEnum currentPhase gives information to the method with which phase
     *                     the user is currently working.
     */

    public void resetPhase(CycleEnum currentPhase){
        if (currentPhase.equals(CycleEnum.REFACTOR)){
            throw new IllegalStateException("Only allowed in the Phase CODE");
        }
        if (currentPhase.equals(CycleEnum.CODE)){
            phase = CycleEnum.TEST;

        }
        if (currentPhase.equals(CycleEnum.TEST)){
            cycleInfo.setCycleError(2);

        }


    }

    /**
     * Method: codingPhase
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

    public CycleEnum codingPhase(String code, String testCode, CycleEnum currentPhase)throws ClassNameParserException {
        if (currentPhase.equals(CycleEnum.CODE)) {
            boolean isATest = false;
            boolean isARealTest = true;
            String className = ClassNameParser.getClassName(code);
            String testName = ClassNameParser.getClassName(testCode);
            CompilationUnit compilationUnit = new CompilationUnit(className, code, isATest);
            CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
            CompilationUnit compArray[] = new CompilationUnit[1];
            compArray[0] = compilationUnit;
            compArray[1] = compilationTestUnit;

            InternalCompiler internalCompiler = new InternalCompiler(compArray);

            if ((internalCompiler.getTestResult().getNumberOfFailedTests() == 0) &&
                    (internalCompiler.getCompilerResult().hasCompileErrors() == false)) {
                currentPhase = CycleEnum.REFACTOR;
                return currentPhase;

            } else {
                cycleInfo.setTestResults(internalCompiler);
                cycleInfo.setCompileResults(internalCompiler, compilationUnit);
                return currentPhase;
            }

        } else {
            throw new IllegalStateException("Wrong function call");
        }
    }


    /**
     * Method: refactoringPhase
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

    public CycleEnum refactoringPhase(String code, String testCode, CycleEnum currentPhase)throws ClassNameParserException {
        if (currentPhase.equals(CycleEnum.REFACTOR)) {
            boolean isATest = false;
            boolean isARealTest = true;
            String className = ClassNameParser.getClassName(code);
            String testName = ClassNameParser.getClassName(testCode);
            CompilationUnit compilationUnit = new CompilationUnit(className, code, isATest);
            CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
            CompilationUnit compArray[] = new CompilationUnit[1];
            compArray[0] = compilationUnit;
            compArray[1] = compilationTestUnit;

            InternalCompiler internalCompiler = new InternalCompiler(compArray);

            if ((internalCompiler.getTestResult().getNumberOfFailedTests() == 0) &&
                    (internalCompiler.getCompilerResult().hasCompileErrors() == true)) {
                currentPhase = CycleEnum.TEST;
                return currentPhase;

            } else {
                cycleInfo.setTestResults(internalCompiler);
                cycleInfo.setCompileResults(internalCompiler, compilationUnit);
                return currentPhase;
            }

        } else {
            throw new IllegalStateException("Wrong function call");
        }
        }

    }


