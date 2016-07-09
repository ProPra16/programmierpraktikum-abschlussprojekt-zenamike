package de.hhu.propra.tddt.test.cycletesting;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import de.hhu.propra.tddt.cycle.Cycle;
import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.cycle.CycleInformation;
import de.hhu.propra.tddt.util.classnameparser.ClassNameParserException;
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
    CycleInformation cycleInfo = new CycleInformation();
    Cycle testCycle = new Cycle();
    CycleEnum testPhase = CycleEnum.TEST;
    Path path =null;
    CycleEnum testResultPhase;

    @Test
    public void testCycleTestPhase() {
        try {

            String testCode = getTestCode();
            String code = getCodeCode();
            testResultPhase = testCycle.testingPhase(testCode,code,testPhase);
            System.out.println(testResultPhase);
            System.out.println(cycleInfo.getCompileResults());



        }catch(ClassNameParserException f){
        System.out.println(f.getMessage());
    }
       // Assert.assertEquals(CycleEnum.CODE, testResultPhase);
    }


    @Test
    public void testCycleCodePhase(){
        try{
            testPhase = CycleEnum.CODE;
            String testCode = getTestCode();
            String code = getCodeCode();
            testResultPhase = testCycle.codingPhase(testCode,code,testPhase);
            System.out.println(testResultPhase);
            System.out.println(cycleInfo.getCompileResults());

        }catch(ClassNameParserException f){
            System.out.println(f.getMessage());     // else part of the function
        }
        // Assert.assertEquals(CycleEnum.CODE, testResultPhase);
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
