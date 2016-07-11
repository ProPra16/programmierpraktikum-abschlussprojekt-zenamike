package de.hhu.propra.tddt.test.cycletesting;


import de.hhu.propra.tddt.compiler.CompileResults;
import de.hhu.propra.tddt.cycle.Cycle;
import de.hhu.propra.tddt.cycle.CycleEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Kevin on 08.07.16.
 */
public class TestCycle {
    CompileResults compileResults = new CompileResults();
    Cycle testCycle = new Cycle();
    CycleEnum testPhase = CycleEnum.TEST;
    GetCode getCode = new GetCode();
    CycleEnum testResultPhase;
    boolean firstCycle = true;

    @Test
    public void testCycleTestPhase() {

        String testCode = getCode.getCode("/TestCode.java");
        testResultPhase = testCycle.testingPhase(testCode, testPhase, firstCycle);
        Assert.assertEquals(CycleEnum.TEST, testResultPhase);
    }


    @Test
    public void testCycleCodePhase() {
        testPhase = CycleEnum.CODE;
        String testCode = getCode.getCode("/TestCode.java");
        String code = getCode.getCode("/NormalCode.java");
        testResultPhase = testCycle.codingPhase(code, testCode, testPhase);
        Assert.assertEquals(CycleEnum.REFACTOR, testResultPhase);
    }

    @Test
    public void testCycleRefactorPhase() {

        testPhase = CycleEnum.REFACTOR;
        String testcode = getCode.getCode("/TestCode.java");
        String code = getCode.getCode("/NormalCode.java");
        testResultPhase = testCycle.refactoringPhase(code, testcode, testPhase);
        Assert.assertEquals(CycleEnum.TEST, testResultPhase);
    }


    @Test
    public void testResetPhaseTEST() {

        testPhase = CycleEnum.TEST;
        testCycle.resetPhase(testPhase);
        Assert.assertEquals("You can't change into the same phase again.", compileResults.getCycleError());
    }


    @Test
    public void testResetPhaseCODE() {
        testPhase = CycleEnum.CODE;
        testResultPhase = testCycle.resetPhase(testPhase);
        Assert.assertEquals(CycleEnum.TEST, testResultPhase);
    }

    @Test(expected = IllegalStateException.class)
    public void testResetPhaseRefactor() {
        testPhase = CycleEnum.REFACTOR;
        testResultPhase = testCycle.resetPhase(testPhase);

    }
}



