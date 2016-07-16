package de.hhu.propra.tddt.test.classnameparsertesting;

import de.hhu.propra.tddt.util.classnameparser.ClassNameParser;
import de.hhu.propra.tddt.util.classnameparser.ClassNameParserException;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Kevin on 05.07.16.
 */
public class TestClassNameParser {
    /*
    this test verifies the function of ClassNameParser
     */




    @Test
    public void testParsingAbstractClassName() {
        String classname = null;
        Path path = null;
        try {
            URL url = getClass().getResource("/HelloWorld.java");
            URI uri = url.toURI();
            path = Paths.get(uri.getPath());

            classname = readFile(path);
            classname = ClassNameParser.getClassName(classname);
            System.out.println(classname);




        }catch(IOException e){
            System.out.println(path.toAbsolutePath().toString());
        }catch(ClassNameParserException f){
            System.out.println(f.getMessage());
        }catch (URISyntaxException g){
            System.out.println(g.getMessage());
        }
        Assert.assertEquals("HelloWorld", classname);


    }

    @Test
    public void testParsingStrictfpClassName() {
        String classname2 = null;
        Path path2 = null;
        try {
            URL url = getClass().getResource("/HelloWorld2.java");
            path2 = Paths.get(url.getPath());
            classname2 = readFile(path2);
            classname2 = ClassNameParser.getClassName(classname2);
            System.out.println(classname2);




        }catch(IOException e){
            System.out.println(path2.toAbsolutePath().toString());
        }catch(ClassNameParserException f){
            System.out.println(f.getMessage());
        }
        Assert.assertEquals("HelloWorld2", classname2);


    }

    @Test
    public void testParsingFinalClassName() {
        String classname3 = null;
        Path path3 = null;
        try {
            URL url = getClass().getResource("/HelloWorld3.java");
            path3 = Paths.get(url.getPath());
            classname3 = readFile(path3);
            classname3 = ClassNameParser.getClassName(classname3);
            System.out.println(classname3);




        }catch(IOException e){
            System.out.println(path3.toAbsolutePath().toString());
        }catch(ClassNameParserException f){
            System.out.println(f.getMessage());
        }
        Assert.assertEquals("HelloWorld3", classname3);


    }

    public static String readFile(Path path)
            throws IOException
    {
        final List<String> fileList = Files.readAllLines(path);
        String filecontent = String.join("", fileList);
        return filecontent;
    }
}
