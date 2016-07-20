package de.hhu.propra.tddt.compiler;


import de.hhu.propra.tddt.util.classnameparser.ClassNameParser;
import de.hhu.propra.tddt.util.classnameparser.ClassNameParserException;
import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;

import java.util.LinkedList;


/**
 * Created by schiggy on 13.07.16.
 */
public class Compiler {

    JavaStringCompiler testComp;
    JavaStringCompiler codeComp;
    String compilationUnitName = null;
    CompileResults compileResults = new CompileResults();

    public Compiler(){
        this.testComp = testComp;
        this.codeComp = codeComp;
        this.compilationUnitName = compilationUnitName;

    }

    /***
     * Method: compileTest
     * <p>
     * Task: Method that takes the information from the CompileManager and compiles
     * the code, results of that compilation is taken to CompileResults.
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


    protected LinkedList<String> compileTest(String testCode)throws ClassNameParserException {
        boolean isARealTest = true;
        String testName = ClassNameParser.getClassName(testCode);
        CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
        compilationUnitName = "compilationTestUnit";
        testCompilerInit(compilationTestUnit);
        compileResults.setTestResults(testComp);
        compileResults.errorStringInit(testComp, compilationTestUnit);

        return compileResults.getTestResults();
    }


    /**
     * Method: compileCode
     * <p>
     * Task: Method that takes the information from the CompileManager and compiles
     * the code, results of that compilation is taken to CompileResults.
     *
     *@param code needs the testcode as a string to have something to work with.
     *
     * @return LinkedList<String> compileResults
     *          in this order:
     *                  compile duration
     *                  compile errors
     */

    protected LinkedList<String> compileCode (String code)throws ClassNameParserException{
        boolean isATest = false;
        String className = ClassNameParser.getClassName(code);
        CompilationUnit compilationUnit = new CompilationUnit(className, code, isATest);
        codeCompilerInit(compilationUnit);
        compilationUnitName = "compilationUnit";
        compileResults.setCodeResults(codeComp, compilationUnit);
        compileResults.errorStringInit(codeComp, compilationUnit);

        return compileResults.getCompileResults();
    }

    /**
     * Method: testCompilerInit
     * <p>
     * Task: Initializes the test compiler.
     *
     *@param compUnit needs a Compilation Unit to starting the compiler.
     *
     * @return void
     */

    private void testCompilerInit(CompilationUnit compUnit){
        testComp = CompilerFactory.getCompiler(compUnit);
        testComp.compileAndRunTests();
    }

    /**
     * Method: codeCompilerInit
     * <p>
     * Task: Initializes the test compiler.
     *
     *@param compUnit needs a Compilation Unit to starting the compiler.
     *
     * @return void
     */

    private void codeCompilerInit(CompilationUnit compUnit){
        codeComp = CompilerFactory.getCompiler(compUnit);
        codeComp.compileAndRunTests();
    }

}
