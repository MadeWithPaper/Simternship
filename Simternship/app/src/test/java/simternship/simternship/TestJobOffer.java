package simternship.simternship;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dustyn Zierman-Felix on 3/12/18.
 * Unit tests for JobOffer.java
 */

public class TestJobOffer {
    //dummy company to use in testing
    Company apple = new Company("Apple", 5, true, 4);

    @Test
    public void TestGetCompanyName() throws Exception {
        JobOffer testGetCompanyName = new JobOffer(apple, new java.math.BigDecimal(100000));
        String output = testGetCompanyName.getCompanyName();
        assertEquals("Apple", output);
    }

    @Test
    public void TestGetSalary() throws Exception {
        JobOffer testGetSalary = new JobOffer(apple, new java.math.BigDecimal(100000));
        BigDecimal output = testGetSalary.getSalary();
        assertEquals(new java.math.BigDecimal(100000), output);
    }
}
