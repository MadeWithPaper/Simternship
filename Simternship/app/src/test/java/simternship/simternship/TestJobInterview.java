package simternship.simternship;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.List;

/**
 * Created by Dustyn Zierman-Felix on 3/12/18.
 * Unit tests for JobInterview.java
 */

public class TestJobInterview {
    //dummy company to use in testing
    Company apple = new Company("Apple", 5, true, 4);
    //dummy list for testing
    List<InterviewQuestion> questions;

    @Test
    public void TestGetCompanyName() throws Exception {
        JobInterview testGetCompanyName = new JobInterview(apple, questions);
        String output = testGetCompanyName.getCompanyName();
        assertEquals("Apple", output);
    }

    @Test
    public void TestGetScore() throws Exception {
        JobInterview testGetScore = new JobInterview(apple, questions);
        int output = testGetScore.getScore();
        assertEquals(0, output);
    }
}
