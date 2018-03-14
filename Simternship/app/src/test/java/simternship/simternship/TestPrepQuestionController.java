package simternship.simternship;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jiaqing on 3/13/18.
 */

public class TestPrepQuestionController {

   @Test
   public void TestType0() {
      PrepQuestionController p = new PrepQuestionController(0);
      PrepSession ps = (PrepSession)p.getSession();
      ps.begin();
      assertEquals(ps.next().getQuestion(), "What motivated you to apply for this position?");
   }

   @Test
   public void TestType1() {
      PrepQuestionController p = new PrepQuestionController(1);
      PrepSession ps = (PrepSession)p.getSession();
      ps.begin();
      String actual = "polymorphism is the ability (in programming) to present the same interface for differing underlying forms (data types).";
      assertEquals(ps.next().getHint(), actual);
   }
}
