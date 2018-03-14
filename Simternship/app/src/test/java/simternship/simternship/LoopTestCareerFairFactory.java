package simternship.simternship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jiaqing on 3/13/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoopTestCareerFairFactory {

    @Test
    public void Test_createCareerFair0() {
        RandomGenerator generator = mock(RandomGenerator.class);

        CareerFairFactory factory = new CareerFairFactory(generator, 5, 10, 5, 10, Arrays.asList(
                "Bob",
                "Jim",
                "Dave"
        ));
        List<Company> companies = new ArrayList<Company>(); //0 elements
        CareerFair c = factory.createCareerFair(companies);
        assertEquals(0, c.getBooths().size());
    }

    @Test
    public void Test_createCareerFair1() { //loop once
        RandomGenerator generator = mock(RandomGenerator.class);

        CareerFairFactory factory = new CareerFairFactory(generator, 5, 10, 5, 10, Arrays.asList(
                "Bob",
                "Jim",
                "Dave"
        ));
        List<Company> companies = new ArrayList<Company>(); //0 elements
        companies.add(new Company("Facebook",1, true, 1));
        CareerFair c = factory.createCareerFair(companies);
        assertEquals(1, c.getBooths().size());
    }

    @Test
    public void Test_createCareerFair2() { //loop twice
        RandomGenerator generator = mock(RandomGenerator.class);

        CareerFairFactory factory = new CareerFairFactory(generator, 5, 10, 5, 10, Arrays.asList(
                "Bob",
                "Jim",
                "Dave"
        ));
        List<Company> companies = new ArrayList<Company>(); //0 elements
        companies.add(new Company("Facebook",1, true, 1));
        companies.add(new Company("Twitter",2, true, 2));
        CareerFair c = factory.createCareerFair(companies);
        assertEquals(2, c.getBooths().size());
    }

    @Test
    public void Test_createCareerFair3() { //loop 10 times
        RandomGenerator generator = mock(RandomGenerator.class);

        CareerFairFactory factory = new CareerFairFactory(generator, 5, 10, 5, 10, Arrays.asList(
                "Bob",
                "Jim",
                "Dave"
        ));
        List<Company> companies = new ArrayList<Company>(); //0 elements
        Company c = new Company("Facebook",1, true, 1);
        for (int i = 1; i<=10; i++){
            companies.add(c);
        }
        CareerFair cf = factory.createCareerFair(companies);
        assertEquals(10, cf.getBooths().size());
    }

}
