
import org.junit.Assert;
import org.junit.Test;


 public class TestCode {

    @Test
    public void rechnungstest(){
        NormalCode rechnung = new NormalCode();
        int ergebnis = rechnung.awesomerechnung();

    Assert.assertEquals(6,ergebnis);


}}