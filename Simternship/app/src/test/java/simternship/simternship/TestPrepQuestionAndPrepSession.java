package simternship.simternship;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jiaqing on 3/13/18.
 */

public class TestPrepQuestionAndPrepSession {

   @Test
   public void TestGetQuestion() {
      PrepQuestion pq1 = new PrepQuestion("question1","hint1");
      PrepQuestion pq2 = new PrepQuestion("question2","hint2");
      PrepQuestion pq3 = new PrepQuestion("question3","hint3");
      List<PrepQuestion> questions = new ArrayList<>();
      questions.add(pq1);
      questions.add(pq2);
      questions.add(pq3);
      PrepSession ps = new PrepSession(questions);
      ps.begin();
      assertEquals(ps.next().getQuestion(), "question1");
      assertEquals(ps.next().getQuestion(), "question2");
      assertEquals(ps.next().getQuestion(), "question3");
   }

   @Test
   public void TestGetHint() {
      PrepQuestion pq1 = new PrepQuestion("question1","hint1");
      PrepQuestion pq2 = new PrepQuestion("question2","hint2");
      PrepQuestion pq3 = new PrepQuestion("question3","hint3");
      List<PrepQuestion> questions = new ArrayList<>();
      questions.add(pq1);
      questions.add(pq2);
      questions.add(pq3);
      PrepSession ps = new PrepSession(questions);
      ps.begin();
      assertEquals(ps.next().getHint(), "hint1");
      assertEquals(ps.next().getHint(), "hint2");
      assertEquals(ps.next().getHint(), "hint3");
   }
}
