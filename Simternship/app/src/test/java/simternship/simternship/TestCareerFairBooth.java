package simternship.simternship;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jacky on 3/13/18.
 */

public class TestCareerFairBooth {

   @Test
   public void TestsetBoothName() {
      Company c = new Company("Test", 5, true, 5);
      CareerFairBooth a = new CareerFairBooth(c);
      assertEquals(a.getCompany().getCompanyName(), "Test");
   }

   @Test
   public void TestcanGetSwag() {
      Company c = new Company("Test", 5, true, 5);
      CareerFairBooth a = new CareerFairBooth(c);
      boolean meet = a.canGetSwag();
      assertEquals(meet, true);
   }
}
