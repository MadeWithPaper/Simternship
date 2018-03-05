package simternship.simternship;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Created by joel on 3/4/18.
 */

public class CompanyFactory {

    private RandomGenerator generator;
    private List<String> adjectives;
    private List<String> industries;
    private List<String> endings;
    private int minCompanies;
    private int maxCompanies;


    public CompanyFactory(RandomGenerator random, int minCompanies, int maxCompanies,
                          List<String> adjectives, List<String> industries,
                          List<String> endings) {
        this.generator = random;
        this.adjectives = adjectives;
        this.industries = industries;
        this.endings = endings;
        this.minCompanies = minCompanies;
        this.maxCompanies = maxCompanies;
    }

    public CompanyFactory(int minCompanies, int maxCompanies) {
        this(new RandomGenerator(),
                minCompanies,
                maxCompanies,
                Arrays.asList("Awesome", "Bigly", "Best", "Supercooled"),
                Arrays.asList("Semiconductor", "Application", "Software", "Oil", "Energy", "Engineering", "Cosmetic"),
                Arrays.asList("Company", "Corporation", "Inc.", "LLC", "Partners")
        );
    }

    public List<Company> createCompanies() {
        int numCompanies = generator.random(minCompanies, maxCompanies);
        List<Company> companies = new ArrayList<>();
        while (numCompanies-- > 0) {
            companies.add(createCompany());
        }

        return companies;
    }

    public Company createCompany() {
        String adjective = generator.choose(this.adjectives);
        String industry = generator.choose(this.industries);
        String ending = generator.choose(this.endings);

        String name = adjective + " " + industry + " " + ending;
        int difficulty = generator.random(1, 5);
        int rating = generator.random(1, 5);
        boolean availability = generator.random(0, 1) == 1;

        return new Company(name, rating, availability, difficulty);
    }

}
