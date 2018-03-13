package simternship.simternship;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jacky on 3/13/18.
 */

public class TestCompany {

   @Test
   public void TestsetRatingWithNegative() {
      Company c = new Company("Test", 10, true, 5);
      c.setRating(-1);
      assertEquals(c.getRating(), -1);
   }

   @Test
   public void TestgetDifficulty() {
      Company c = new Company("Test", 10, true, 5);
      c.setDiffculty(6);
      assertEquals(c.getDifficulty(), 6);
   }
}
