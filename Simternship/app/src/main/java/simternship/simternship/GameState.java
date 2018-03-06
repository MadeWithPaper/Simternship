package simternship.simternship;

import android.app.Activity;
import android.widget.Toast;

import java.util.LinkedList;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * Created by joel on 2/23/18.
 */

public class GameState extends Observable {
    private static int MIN_COMPANIES = 5;
    private static int MAX_COMPANIES = 20;
    private static int MIN_RECRUITERS = 2;
    private static int MAX_RECRUITERS = 5;
    private static int MIN_ATTENDEES = 2;
    private static int MAX_ATTENDEES = 30;

    private CareerFairFactory careerFairFactory;
    private CompanyFactory companyFactory;

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
    private int finalScore;
    private JobInterview interview;
    private JobOffer offer;
    private JobOffer chosenOffer;

    //we will use this to invoke timer actions on the UI thread
    private Activity currentActivity;


    /**
     * Create the objects used by a new game and the various
     * screens. You should test your screens with both the field
     * being null and existing, since some things will be constructed
     * by a timer.
     */
    public void newGame(Activity caller, String firstName, String lastName, int difficulty) {
        destroyOldGame();
        currentActivity = caller;
        //TODO: career fair should be constructed by timer
        companies = companyFactory.createCompanies();
        careerFair = careerFairFactory.createCareerFair(companies);
        currentEnergy = 100;
        currentNetworking = 0;
        currentJobOffers = new LinkedList<>();
        currentJobInterviews = new LinkedList<>();

        setFirstName(firstName);
        setLastName(lastName);
        setGameDifficulty(difficulty);

        //tests for jobInterviews
        currentJobInterviews.add(new JobInterview("Apple"));
        currentJobInterviews.add(new JobInterview("Amazon"));
        currentJobInterviews.add(new JobInterview("Microsoft"));
        currentJobInterviews.add(new JobInterview("Facebook"));
        currentJobInterviews.add(new JobInterview("Sears"));
        currentJobInterviews.add(new JobInterview("Return of the Jedi"));
        currentJobInterviews.add(new JobInterview("Computer Genies"));
        currentJobInterviews.add(new JobInterview("Borat Sagdiyev"));
        currentJobInterviews.add(new JobInterview("Kim Jung Un"));
        currentJobInterviews.add(new JobInterview("Donald Trump"));

        //tests for jobOffers
        currentJobOffers.add(new JobOffer("Apple", new java.math.BigDecimal("100000")));
        currentJobOffers.add(new JobOffer("Amazon", new java.math.BigDecimal("120000")));
        currentJobOffers.add(new JobOffer("Microsoft", new java.math.BigDecimal("140000")));
        currentJobOffers.add(new JobOffer("Facebook", new java.math.BigDecimal("80000")));
        currentJobOffers.add(new JobOffer("Sears", new java.math.BigDecimal("50000")));
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
        if (gameState == null) {
            gameState = new GameState();
        }

        return gameState;
    }

    /**
     * Destroy the state/timers/resources used by an
     * old instance of the game here.
     */
    private void destroyOldGame() {

    }

    private static GameState gameState;

    private GameState() {
        this.companyFactory = new CompanyFactory(MIN_COMPANIES, MAX_COMPANIES);
        this.careerFairFactory = new CareerFairFactory(MIN_RECRUITERS, MAX_RECRUITERS,
                MIN_ATTENDEES, MAX_ATTENDEES, names);
    }

    //actions

    public void endGame() {
        Toast.makeText(currentActivity, "Game Ended", Toast.LENGTH_SHORT).show();
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
    }

    public void updateNetworking(int change) {
        this.currentNetworking += change;
    }

    public void setFinalScore(int score) {this.finalScore = score; }

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

    // Getters
    public int getGameDifficulty() {
        return this.gameDifficulty;
    }

    public LinkedList<JobOffer> getCurrentJobOffers(){
        return this.currentJobOffers;
    }

    public LinkedList<JobInterview> getCurrentJobInterviews(){
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

    public int getFinalScore() {return this.finalScore; }

    public JobInterview getCurrentInterview()
    {
        return interview;
    }

    public JobOffer getCurrentOffer()
    {
        return offer;
    }
}
