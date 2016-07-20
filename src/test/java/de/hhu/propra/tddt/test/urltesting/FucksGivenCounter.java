package de.hhu.propra.tddt.test.urltesting;

import org.junit.Assert;
import org.junit.Test;
public class FucksGivenCounter {

    @Test
    public void testSomething() {
        double numberajdflk = Math.random();
        System.out.println(numberajdflk);
        int number = (int) (numberajdflk*2);
        Assert.assertEquals(1,number);
    }

    @Test
    public void testNothing(){
        Assert.assertEquals(1,1);
    }

}
        