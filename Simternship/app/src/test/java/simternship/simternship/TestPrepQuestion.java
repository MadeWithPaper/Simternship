package simternship.simternship;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jiaqing on 3/13/18.
 */

public class TestPrepQuestion {

   @Test
   public void TestGetQuestion() {
      PrepQuestion pq = new PrepQuestion("question","hint");

      assertEquals(pq.getQuestion(), "question");
   }

   @Test
   public void TestGetHint() {
      PrepQuestion pq = new PrepQuestion("question","hint");

      assertEquals(pq.getHint(), "hint");
   }
}
