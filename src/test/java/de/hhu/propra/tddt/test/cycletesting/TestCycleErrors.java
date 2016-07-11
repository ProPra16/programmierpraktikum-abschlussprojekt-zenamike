package de.hhu.propra.tddt.test.cycletesting;

import de.hhu.propra.tddt.compiler.CompileResults;
import de.hhu.propra.tddt.compiler.CompilerManager;
import de.hhu.propra.tddt.cycle.Cycle;
import de.hhu.propra.tddt.cycle.CycleEnum;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by schiggy on 10.07.16.
 */
public class TestCycleErrors {

    GetCode getCode = new GetCode();
    CycleEnum testResultPhase = CycleEnum.CODE;
    CycleEnum testPhase = CycleEnum.TEST;
    Cycle testCycle = new Cycle();
    CompileResults compileResults = new CompileResults();
    boolean firstCycle = false;

    @Test
    public void testTestPhaseFailedTest (){
        testPhase = CycleEnum.TEST;
        String failingtestcode = getCode.getCode("/ErrorTest.java");
        testResultPhase = testCycle.testingPhase(failingtestcode, testPhase, firstCycle);
        Assert.assertEquals(CycleEnum.TEST, testResultPhase);

    }

    @Test
    public void testTestPhaseNotCompileableTest(){
        testPhase = CycleEnum.TEST;
        String failingtestcode = getCode.getCode("/NotCompileTest.java");
        testResultPhase = testCycle.testingPhase(failingtestcode, testPhase,firstCycle);
        LinkedList<String> list1 =  compileResults.getCompileResults();
        String list2 = compileResults.getCompilerErrors().toString();
        //System.out.println(compileResults.getCycleError());
        //System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(list2);
        Assert.assertEquals(CycleEnum.TEST, testResultPhase);

    }


}
