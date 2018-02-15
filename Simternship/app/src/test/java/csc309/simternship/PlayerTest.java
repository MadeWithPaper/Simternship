package csc309.simternship;


import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
/**
 * Created by joel on 2/14/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
    @Test
    public void offer_isCorrect() {
        //Note: we should have JobInterviews associated directly with companies,
        //and mock the company too. Then we could get the company instance and
        //use the difficulty in the score computation.
        JobInterview one = mockInterview("Apple", 5);
        JobInterview two = mockInterview("Google", 8);
        JobInterview three = mockInterview("Facebook", 10);

        Player player = Player.create("Joe", "Smith", "joe@example.com", "1234");
        player.completeInterview(one);
        player.completeInterview(two);
        player.completeInterview(three);


        JobOffer expected = new JobOffer("Facebook", new BigDecimal("100000"));
        JobOffer actual = player.getBestOffer();

        //assertEquals(expected, actual);
    }

    JobInterview mockInterview(String companyName, int score) {
        JobInterview interview = mock(JobInterview.class);
        when(interview.getCompanyName()).thenReturn(companyName);
        when(interview.getScore()).thenReturn(score);

        return interview;
    }

}
