package simternship.simternship;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dustyn Zierman-Felix on 3/12/18.
 * Integration Test testing JobInterview and JobInterviewPreview
 */

public class TestJobInterviewAndInterviewQuestion {
    //dummy company to use in testing
    Company apple = new Company("Apple", 5, true, 4);

    @Test
    public void TestGetCompanyName() throws Exception {
        //dummy questions for testing
        List<InterviewQuestion> questions = new ArrayList<>();
        InterviewQuestion questionOne = new InterviewQuestion("hello", "world");
        InterviewQuestion questionTwo = new InterviewQuestion("cal", "poly");
        InterviewQuestion questionThree = new InterviewQuestion("software", "engineering");
        questions.add(questionOne);
        questions.add(questionTwo);
        questions.add(questionThree);

        JobInterview testGetCompanyName = new JobInterview(apple, questions);
        String output = testGetCompanyName.getCompanyName();
        assertEquals("Apple", output);
    }

    @Test
    public void TestGetQuestion() throws Exception {
        //dummy questions for testing
        List<InterviewQuestion> questions = new ArrayList<>();
        InterviewQuestion questionOne = new InterviewQuestion("hello", "world");
        InterviewQuestion questionTwo = new InterviewQuestion("cal", "poly");
        InterviewQuestion questionThree = new InterviewQuestion("software", "engineering");
        questions.add(questionOne);
        questions.add(questionTwo);
        questions.add(questionThree);

        InterviewQuestion testGetQuestion = new InterviewQuestion("hello", "world");
        String output = testGetQuestion.getQuestion();
        assertEquals("hello", output);
    }
}
