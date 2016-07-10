package de.hhu.propra.tddt.test.cycletesting;


import de.hhu.propra.tddt.compiler.CompileResults;
import de.hhu.propra.tddt.cycle.Cycle;
import de.hhu.propra.tddt.cycle.CycleEnum;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Kevin on 08.07.16.
 */
public class TestCycle {
    CompileResults compileResults = new CompileResults();
    Cycle testCycle = new Cycle();
    CycleEnum testPhase = CycleEnum.TEST;
    Path path =null;
    CycleEnum testResultPhase;

    @Test
    public void testCycleTestPhase() {

            String testCode = getTestCode();
            testResultPhase = testCycle.testingPhase(testCode,testPhase);
            Assert.assertEquals(CycleEnum.TEST, testResultPhase);
    }


    @Test
    public void testCycleCodePhase(){
            testPhase = CycleEnum.CODE;
            String testCode = getTestCode();
            String code = getCodeCode();
            testResultPhase = testCycle.codingPhase(code,testCode,testPhase);
           // Assert.assertEquals(CycleEnum.REFACTOR, testResultPhase);
    }

    @Test
    public void testCycleRefactorPhase(){

            testPhase = CycleEnum.REFACTOR;
            String testcode =getTestCode();
            String code = getCodeCode();
            testResultPhase = testCycle.refactoringPhase(code, testcode, testPhase);
           // Assert.assertEquals(CycleEnum.TEST,testResultPhase);
    }


    @Test
    public void testResetPhaseTEST(){

            testPhase = CycleEnum.TEST;
            testCycle.resetPhase(testPhase);
            Assert.assertEquals("You can't change into the same phase again.", compileResults.getCycleError());
    }


    @Test
    public void testResetPhaseCODE(){
        testPhase = CycleEnum.CODE;
        testResultPhase= testCycle.resetPhase(testPhase);
        Assert.assertEquals(CycleEnum.TEST, testResultPhase);
    }

    @Test(expected = IllegalStateException.class)
    public void testResetPhaseRefactor(){
        testPhase = CycleEnum.REFACTOR;
        testResultPhase= testCycle.resetPhase(testPhase);

    }







    public String getTestCode(){
        String testCode = null;
        try {
            URL testUrl = getClass().getResource("/TestCode.java");
            Path path = Paths.get(testUrl.getPath());
            testCode = readFile(path);


        } catch (IOException e) {
            System.out.println(path.toAbsolutePath().toString());
        }
        return testCode;
    }

    public String getCodeCode(){
    String codeCode = null;
        try{
            URL codeUrl = getClass().getResource("/NormalCode.java");
            Path path = Paths.get(codeUrl.getPath());
            codeCode = readFile(path);
        }catch (IOException e){
            System.out.println(path.toAbsolutePath().toString());
        }
        return codeCode;
}


    public static String readFile(Path path)
            throws IOException
    {
        final List<String> fileList = Files.readAllLines(path);
        String filecontent = String.join("", fileList);
        return filecontent;
    }
}
