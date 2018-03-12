package simternship.simternship;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.LinkedList;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by joel on 2/23/18.
 */

public class GameState extends Observable {
    private static int minCompanies = 5;
    private static int maxCompanies = 20;
    private static int minRecuriters = 2;
    private static int maxRecuriters = 5;
    private static int minAttendees = 2;
    private static int maxAttendees = 30;

    private CareerFairFactory careerFairFactory;
    private CompanyFactory companyFactory;
    private InterviewFactory interviewFactory;

    //names that recruiters/attendees can have
    //probably would load from file if we had time.
    private List<String> names = Arrays.asList(
            "Bill", "Jim", "John", "James", "Derek",
            "Daniel", "Jason", "Catherine", "Jennifer", "Chad",
            "Charles"
    );

    private CareerFair careerFair;
    private List<Company> companies;
    private CareerFairBooth booth;

    private int currentEnergy;
    private int currentNetworking;
    private LinkedList<JobOffer> currentJobOffers;
    private LinkedList<JobInterview> currentJobInterviews;
    private int gameDifficulty;
    private String firstName;
    private String lastName;
    private JobInterview interview;
    private JobOffer offer;
    private JobOffer chosenOffer;
    private int lastNameCount;
    private RandomGenerator randomGenerator;
    private QuestionController questionController;
    private List<JobInterview> completedInterviews;
    private CareerFairController careerFairController;

    //we will use this to invoke timer actions on the UI thread
    private Activity currentActivity;


    /**
     * Create the objects used by a new game and the various
     * screens. You should test your screens with both the field
     * being null and existing, since some things will be constructed
     * by a timer.
     */
    public static void newGame(Activity caller, String firstName, String lastName, int difficulty) {
        gameState = new GameState(caller, firstName, lastName, difficulty);

        gameState.careerFairController.start();

    }

    public void setCurrentBooth(CareerFairBooth booth) {
        this.booth = booth;
    }

    public CareerFairBooth getCurrentBooth() {
        return this.booth;
    }

    /**
     * Get the career fair for the game. This may be null
     * if the career fair has not yet started.
     * @return the career fair
     */
    public CareerFair getCareerFair() {
        return this.careerFair;
    }

    public static GameState getInstance() {
        return gameState;
    }

    private static GameState gameState;

    private GameState(Activity caller, String firstName, String lastName, int difficulty) {
        this.companyFactory = new CompanyFactory(minCompanies, maxCompanies);
        companies = companyFactory.createCompanies();
        this.careerFairFactory = new CareerFairFactory(minRecuriters, maxRecuriters,
                minAttendees, maxAttendees, names);

        this.careerFairController = new CareerFairController(new UITimer(caller),
                this, companies, careerFairFactory);

        interviewFactory = new InterviewFactory();

        this.randomGenerator = new RandomGenerator();

        currentActivity = caller;
        currentEnergy = 100;
        currentNetworking = 0;
        currentJobOffers = new LinkedList<>();
        currentJobInterviews = new LinkedList<>();
        completedInterviews = new LinkedList<>();

        setFirstName(firstName);
        setLastName(lastName);
        setGameDifficulty(difficulty);

        lastNameCount = 0;
    }

    //actions

    public void showToast(String message, int length) {
        Toast.makeText(currentActivity, message, length).show();
    }

    public void endGame() {
        clearAndStart(EndScreen.class);
    }

    public void clearAndStart(Class<? extends Activity> newClass) {
        Intent intent = new Intent(currentActivity, newClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        currentActivity.startActivity(intent);
    }

    public void forceChooseOffer() {
        if (currentJobOffers.isEmpty()) {
            endGame();
            return;
        }

        clearAndStart(JobOfferPreview.class);
    }

    public void newJobOffer(JobOffer job) {
        this.currentJobOffers.add(job);
        this.gotUpdated();
    }

    public void newJobInterview(JobInterview interview) {
        this.currentJobInterviews.add(interview);
        this.gotUpdated();
    }

    public void removeJobOffer(JobOffer offer) {
        this.currentJobOffers.remove(offer);
        this.gotUpdated();
    }

    public void removeJobInterview(JobInterview interview) {
        this.currentJobInterviews.remove(interview);
        this.gotUpdated();
    }

    public void gotUpdated() {
        this.setChanged();
        this.notifyObservers();
    }

    public String getNextPersonName() {
        char lastNameChar = (char) ('A' + (lastNameCount++ % 26));
        return "Davide " + lastNameChar + ".";
    }

    public void completeInterview(JobInterview interview) {
        if (interview.getScore() >= gameDifficulty
                && randomGenerator.random(1, 10) > 5) {
            Company company = interview.getCompany();
            newJobOffer(new JobOffer(company, computeAmount(company)));
        }
    }

    private JobOffer bestOffer() {
        BigDecimal best = new BigDecimal(0);
        JobOffer bestOffer = null;
        for (JobOffer o : currentJobOffers) {
            if (o.getSalary().compareTo(best) >= 0) {
                bestOffer = o;
                best = o.getSalary();
            }
        }

        return bestOffer;
    }

    int computeScore(JobOffer offer) {
        if (offer == null) {
            return 0;
        }

        return offer.getSalary().intValueExact() * gameDifficulty;
    }

    private BigDecimal computeAmount(Company company) {
        int difficultyOffset = company.getDifficulty() * 10000;
        int ratingOffset = company.getRating() * 10000;

        int baseOffer = randomGenerator.random(50000, 200000);

        int amount = baseOffer + difficultyOffset - ratingOffset;

        return new BigDecimal(amount);
    }

    public void computeInterviews() {
        for (Company company : companies) {
            if (company.getAvailability() && gotInterview()) {
                newJobInterview(interviewFactory.createInterview(company));
            }
        }
    }

    private boolean gotInterview() {
        int random = randomGenerator.random(1, 50);
        int prob = Math.min(random + currentNetworking, 100);
        return prob > 50;
    }

    // Setters
    public void setGameDifficulty(int difficulty) {
        this.gameDifficulty = difficulty;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public void updateEnergy(int change) {
        this.currentEnergy += change;
        if (this.currentEnergy > 100) {
            this.currentEnergy = 100;
        }

        if (this.currentEnergy < 0) {
            this.currentEnergy = 0;
            endGame();
        }
    }

    public void updateNetworking(int change) {
        this.currentNetworking += change;
        if (this.currentNetworking > 100) {
            this.currentNetworking = 100;
        }
    }


    public void setCurrentInterview(JobInterview interview)
    {
        this.interview = interview;
    }

    public void setCurrentOffer(JobOffer offer)
    {
        this.offer = offer;
    }

    public void setChosenOffer(JobOffer offer)
    {
        this.chosenOffer = offer;
    }

    public void setQuestionController(QuestionController controller) {
        questionController = controller;
    }

    // Getters
    public int getGameDifficulty() {
        return this.gameDifficulty;
    }

    public List<JobOffer> getCurrentJobOffers(){
        return this.currentJobOffers;
    }

    public List<JobInterview> getCurrentJobInterviews(){
        return this.currentJobInterviews;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getCurrentEnergy() {
        return this.currentEnergy;
    }

    public int getCurrentNetworking() {
        return this.currentNetworking;
    }

    public int getFinalScore() {return computeScore(chosenOffer); }

    public JobInterview getCurrentInterview()
    {
        return interview;
    }

    public JobOffer getCurrentOffer()
    {
        return offer;
    }

    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    public QuestionController getQuestionController() {
        return questionController;
    }

    public CareerFairController getCareerFairController() {
        return careerFairController;
    }

    public int getCurrentScore() {
        return computeScore(bestOffer());
    }
}
