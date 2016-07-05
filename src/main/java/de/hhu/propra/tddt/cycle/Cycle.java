package de.hhu.propra.tddt.cycle;

/**
 * Created by zeljko On 23.06.2016
 */



import com.sun.org.apache.xpath.internal.SourceTree;
import de.hhu.propra.tddt.util.classnameparser.ClassNameParser;
import de.hhu.propra.tddt.util.classnameparser.ClassNameParserException;
import vk.core.api.CompilationUnit;
import vk.core.internal.InternalCompiler;


import java.util.LinkedList;

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

    public int listPosition = 0;
    public LinkedList<CycleEnum> cycleList = new LinkedList<CycleEnum>();
    CycleEnum phase = CycleEnum.TEST;


    /*********************************************************
     * Method: startCycle
     * <p>
     * Start a new cycle
     *
     * @return: void
     ***************************************************************/

    public void startCycle() {
        cycleList.add(CycleEnum.TEST);
        cycleList.add(CycleEnum.CODE);
        cycleList.add(CycleEnum.REFACTOR);

        // TODO: 05.07.2016 Ask Zeljiko for the fourth time why a linked list is useful.

        }

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
     */

    public CycleEnum testingPhase(String testCode, String code, CycleEnum currentPhase)throws ClassNameParserException {

        if (currentPhase.equals(CycleEnum.TEST)) {
            boolean isATest = false;
            boolean isARealTest = true;
            String className = ClassNameParser.getClassName(code);
            String testName = ClassNameParser.getClassName(testCode);
            CompilationUnit compilationUnit = new CompilationUnit(className, code, isATest);
            CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
            CompilationUnit compArray [] = new CompilationUnit[1];
            compArray [0] = compilationUnit;
            compArray [1] = compilationTestUnit;

            InternalCompiler internalCompiler = new InternalCompiler(compArray);

            if (internalCompiler.getTestResult().getNumberOfFailedTests() != 1) {
                System.out.println("Failed tests: " +internalCompiler.getTestResult().getNumberOfFailedTests());
                System.out.println("Ignored tests: " +internalCompiler.getTestResult().getNumberOfIgnoredTests());
                System.out.println("Successful tests: "+internalCompiler.getTestResult().getNumberOfSuccessfulTests());
                System.out.println("Test duration: " +internalCompiler.getTestResult().getTestDuration());
                System.out.print("Too many or too less failed tests. Only one test is allowed to fail");

            }else if (internalCompiler.getTestResult().getNumberOfFailedTests() == 1){
                currentPhase = CycleEnum.CODE;
                return currentPhase;

            }else if (internalCompiler.getCompilerResult().hasCompileErrors()==false);
                currentPhase = CycleEnum.CODE;
                return currentPhase;

        } else {
            throw new IllegalStateException("Wrong function call");

        }

        // TODO: 05.07.2016 how to implement babysteps in this function?
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
            // TODO: 05.07.2016 kill the already written code
        }
        if (currentPhase.equals(CycleEnum.TEST)){
            System.out.println("You can't change into the same phase again.");
        }
        // TODO: 05.07.2016 how to stop them Babysteps?

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
                    (internalCompiler.getCompilerResult().hasCompileErrors() == true)) {
                currentPhase = CycleEnum.REFACTOR;
                return currentPhase;

            } else {
                System.out.println("Failed tests: " + internalCompiler.getTestResult().getNumberOfFailedTests());
                System.out.println("Ignored tests: " + internalCompiler.getTestResult().getNumberOfIgnoredTests());
                System.out.println("Successful tests: " + internalCompiler.getTestResult().getNumberOfSuccessfulTests());
                System.out.println("Test duration: " + internalCompiler.getTestResult().getTestDuration());
                System.out.println("");
                System.out.println("Has it compile errors?" + internalCompiler.getCompilerResult().hasCompileErrors());
                System.out.println("Compile duration" + internalCompiler.getCompilerResult().getCompileDuration());
                return currentPhase;
            }
            // TODO: 05.07.2016 the same as with the methods above
        } else {
            throw new IllegalStateException("Wrong function call");
        }
    }






}
