package de.hhu.propra.tddt.test.cycletesting;

import de.hhu.propra.tddt.cycle.Cycle;
import de.hhu.propra.tddt.cycle.CycleEnum;
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
    Cycle testCycle = new Cycle();
    CycleEnum testPhase = CycleEnum.TEST;
    Path path =null;
    CycleEnum testResultPhase;

    @Test
    public void testCycleTestPhase() {
        try {
            URL testUrl = getClass().getResource("/TestCode.java");
            Path path = Paths.get(testUrl.getPath());
            URL codeUrl = getClass().getResource("/NormalCode.java");
            Path codePath = Paths.get(codeUrl.getPath());
            String testCode = readFile(path);
            String code = readFile(codePath);
            testResultPhase = testCycle.testingPhase(testCode,code,testPhase);



        } catch (IOException e) {
            System.out.println(path.toAbsolutePath().toString());
        }catch(ClassNameParserException f){
        System.out.println(f.getMessage());
    }
       // Assert.assertEquals(CycleEnum.CODE, testResultPhase);
    }



    public static String readFile(Path path)
            throws IOException
    {
        final List<String> fileList = Files.readAllLines(path);
        String filecontent = String.join("", fileList);
        return filecontent;
    }
}
