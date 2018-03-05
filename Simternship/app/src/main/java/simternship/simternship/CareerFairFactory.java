package simternship.simternship;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by joel on 3/4/18.
 */

public class CareerFairFactory {
    private RandomGenerator generator;
    private int minRecruiters;
    private int maxRecruiters;
    private int minAttendees;
    private int maxAttendees;
    private List<String> names;

    public CareerFairFactory(RandomGenerator generator,
                             int minRecruiters, int maxRecruiters, int minAttendees,
                             int maxAttendees, List<String> names) {
        this.generator = generator;
        this.minRecruiters = minRecruiters;
        this.maxRecruiters = maxRecruiters;
        this.minAttendees = minAttendees;
        this.maxAttendees = maxAttendees;
        this.names = names;
    }

    public CareerFairFactory(int minRecruiters, int maxRecruiters, int minAttendees,
                             int maxAttendees, List<String> names) {
        this(new RandomGenerator(),
                minRecruiters, maxRecruiters, minAttendees,
                maxAttendees, names);
    }

    public CareerFair createCareerFair(List<Company> companies) {
        List<CareerFairBooth> booths = new ArrayList<>();
        for (Company company : companies) {
            booths.add(createBooth(company));
        }

        CareerFair fair = new CareerFair(booths);
        return fair;
    }

    CareerFairBooth createBooth(Company company) {
        CareerFairBooth booth = new CareerFairBooth(company);

        int numRecruiters = generator.random(minRecruiters, maxRecruiters);
        while (numRecruiters-- > 0) {
            booth.addRecruiter(createRecruiter(booth));
        }

        int numAttendees = generator.random(minAttendees, maxAttendees);
        while (numAttendees-- > 0) {
            booth.queueAttendee(createAttendee(booth));
        }

        return booth;
    }


    Recruiter createRecruiter(CareerFairBooth booth) {
        return new Recruiter(createName(), booth, booth.getCompany());
    }

    CareerFairAttendee createAttendee(CareerFairBooth booth) {
        return new CareerFairAttendee(createName(), booth);
    }

    String createName() {
        return names.get(generator.random(0, names.size() - 1));
    }
}
