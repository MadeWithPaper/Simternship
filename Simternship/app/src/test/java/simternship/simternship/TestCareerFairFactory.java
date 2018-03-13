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
public class TestCareerFairFactory {

    @Test
    public void Test_createName() {
        RandomGenerator generator = mock(RandomGenerator.class);

        CareerFairFactory factory = new CareerFairFactory(generator, 5, 10, 5, 10, Arrays.asList(
                "Bob",
                "Jim",
                "Dave"
        ));

        //name should come from index in names list returned by
        //the random number generator
        when(generator.random(anyInt(), anyInt())).thenReturn(1);
        assertEquals("Jim", factory.createName());

        when(generator.random(anyInt(), anyInt())).thenReturn(0);
        assertEquals("Bob", factory.createName());
    }

    @Test
    public void Test_createBooth() {
        RandomGenerator generator = mock(RandomGenerator.class);

        CareerFairFactory factory = new CareerFairFactory(generator, 5, 10, 4, 10, Arrays.asList(
                "Bob",
                "Jim",
                "Dave"
        ));

        when(generator.random(5, 10)).thenReturn(6);

        when(generator.random(4, 10)).thenReturn(5);

        when(generator.random(0, 2)).thenReturn(0);

        Company company = mock(Company.class);
        when(company.getCompanyName()).thenReturn("Apple");

        CareerFairBooth booth = factory.createBooth(company);

        assertEquals(company, booth.getCompany());

        List<CareerFairAttendee> attendees = booth.getAttendees();
        List<Recruiter> recruiters = booth.getRecruiters();

        assertEquals(5, attendees.size());
        assertEquals(6, recruiters.size());

        for (CareerFairAttendee attendee : attendees) {
            assertEquals("Bob", attendee.getName());
        }

        for (Recruiter recruiter : recruiters) {
            assertEquals("Bob", recruiter.getName());
            assertEquals(company, recruiter.getCompany());
        }

    }
}
