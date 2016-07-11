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

    public String getCode(String classname) {
        String codeCode = null;
        try {
            URL codeUrl = getClass().getResource(classname);
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

