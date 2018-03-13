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
public class TestCompanyFactory {

    @Test
    public void Test_createCompany() {
        RandomGenerator generator = mock(RandomGenerator.class);

        CompanyFactory factory = new CompanyFactory(generator, 5, 10,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("1", "2", "3"),
                Arrays.asList(".", ",", ";")
        );

        when(generator.random(anyInt(), anyInt())).thenReturn(0);
        when(generator.choose(anyList())).thenCallRealMethod();

        Company company = factory.createCompany();

        companyAssertions(company);
    }

    @Test
    public void Test_createCompanies() {
        RandomGenerator generator = mock(RandomGenerator.class);

        CompanyFactory factory = new CompanyFactory(generator, 5, 10,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("1", "2", "3"),
                Arrays.asList(".", ",", ";")
        );

        when(generator.random(anyInt(), anyInt())).thenReturn(0);
        when(generator.choose(anyList())).thenCallRealMethod();
        when(generator.random(5, 10)).thenReturn(5);

        List<Company> companies = factory.createCompanies();

        assertEquals(1, companies.size());

        companyAssertions(companies.get(0));

    }

    private void companyAssertions(Company company) {
        assertEquals("A 1 .", company.getCompanyName());
        assertEquals(0, company.getDifficulty());
        assertEquals(0, company.getRating());
        assertEquals(false, company.getAvailability());
    }
}
