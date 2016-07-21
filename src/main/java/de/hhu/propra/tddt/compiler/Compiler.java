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

    public Compiler() {
        this.testComp = testComp;
        this.codeComp = codeComp;
        this.compilationUnitName = compilationUnitName;

    }

    /***
     * Method: compileTest
     * <p>
     * Task: Method that takes the information from the CompileManager and
     * compiles the code, results of that compilation is taken to
     * CompileResults.
     *
     * @param testCode
     *            needs the testcode as a string to have something to work with.
     * @param testCode2
     *
     * @return LinkedList<String> testResults in this order: failed tests
     *         ignored tests successful tests test duration test fail messages
     */

    protected LinkedList<String> compileTest(String code, String testCode) throws ClassNameParserException {
        boolean isARealTest = true;
        String codeName = ClassNameParser.getClassName(code);
        String testName = ClassNameParser.getClassName(testCode);
        CompilationUnit compilationTestUnit = new CompilationUnit(testName, testCode, isARealTest);
        CompilationUnit compilationUnit = new CompilationUnit(codeName, code, false);
        compilationUnitName = "compilationTestUnit";
        testCompilerInit(compilationUnit, compilationTestUnit);

        compileResults.setTestResults(testComp);

        compileResults.errorStringInit(testComp, compilationTestUnit);
        return compileResults.getTestResults();
    }

    /**
     * Method: testCompilerInit
     * <p>
     * Task: Initializes the test compiler.
     *
     * @param compUnit
     *            needs a Compilation Unit to starting the compiler.
     * @param compilationTestUnit
     *
     * @return void
     */

    private void testCompilerInit(CompilationUnit compUnit, CompilationUnit compilationTestUnit) {
        testComp = CompilerFactory.getCompiler(compUnit, compilationTestUnit);
        testComp.compileAndRunTests();
    }

    /**
     * Method: codeCompilerInit
     * <p>
     * Task: Initializes the test compiler.
     *
     * @param compUnit
     *            needs a Compilation Unit to starting the compiler.
     *
     * @return void
     */

    private void codeCompilerInit(CompilationUnit compUnit) {
        codeComp = CompilerFactory.getCompiler(compUnit);
        codeComp.compileAndRunTests();
    }

}