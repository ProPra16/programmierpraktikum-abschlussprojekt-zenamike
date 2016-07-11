package de.hhu.propra.tddt.test.cycletesting;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by schiggy on 11.07.16.
 */
public class GetCode {

    Path path = null;

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

    public String getCodeCode() {
        String codeCode = null;
        try {
            URL codeUrl = getClass().getResource("/NormalCode.java");
            Path path = Paths.get(codeUrl.getPath());
            codeCode = readFile(path);
        } catch (IOException e) {
            System.out.println(path.toAbsolutePath().toString());
        }
        return codeCode;
    }


    public static String readFile(Path path)
            throws IOException {
        final List<String> fileList = Files.readAllLines(path);
        String filecontent = String.join("", fileList);
        return filecontent;
    }
}

