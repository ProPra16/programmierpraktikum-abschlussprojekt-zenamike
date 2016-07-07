package de.hhu.propra.tddt.test.urltesting;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zeljko On 05.07.2016
 */
public class URLTest {

    @Test
    public void testURL(){
        URL url = getClass().getResource("/HelloWorld.java");
        System.out.println(url.getPath());
        Assert.assertNotEquals(null, url);
    }
}
