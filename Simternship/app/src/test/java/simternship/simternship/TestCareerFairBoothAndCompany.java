package simternship.simternship;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joel on 3/13/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCareerFairBoothAndCompany {
    @Test
    public void Test_careerFairBoothGetCompany() {
        Company company = new Company("Foo", 0, false, 0);
        CareerFairBooth booth = new CareerFairBooth(company);

        assertEquals(company, booth.getCompany());
    }

    @Test
    public void Test_careerFairBoothAddRecruiter() {
        Company company = new Company("Foo", 0, false, 0);
        CareerFairBooth booth = new CareerFairBooth(company);

        Recruiter recruiterA = mock(Recruiter.class);
        when(recruiterA.getCompany()).thenReturn(company);

        Recruiter recruiterB = mock(Recruiter.class);
        when(recruiterB.getCompany()).thenReturn(null);

        booth.addRecruiter(recruiterA);
        //this recruiter should not be added
        booth.addRecruiter(recruiterB);

        assertEquals(1, booth.getRecruiters().size());
        assertEquals(recruiterA, booth.getRecruiters().get(0));
    }
}
