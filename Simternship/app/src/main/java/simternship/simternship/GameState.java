package simternship.simternship;

import android.app.Activity;
import java.util.LinkedList;

/**
 * Created by joel on 2/23/18.
 */

public class GameState {
    private CareerFair careerFair;
    private int currentEnergy;
    private int currentNetworking;
    private LinkedList<JobOffer> currentJobOffers;
    private LinkedList<JobInterview> currentJobInterviews;
    private int gameDifficulty;
    private String firstName;
    private String lastName;

    //we will use this to invoke timer actions on the UI thread
    private Activity startingActivity;


    /**
     * Create the objects used by a new game and the various
     * screens. You should test your screens with both the field
     * being null and existing, since some things will be constructed
     * by a timer.
     */
    public void newGame(Activity caller) {
        destroyOldGame();
        startingActivity = caller;
        //TODO: career fair should be constructed by timer
        careerFair = new CareerFair();
        currentEnergy = 100;
        currentNetworking = 0;
        currentJobOffers = new LinkedList<>();
        currentJobInterviews = new LinkedList<>();
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

    }

    // Setters
    public void setGameDifficulty(int difficulty) {
        this.gameDifficulty = difficulty;
    }

    public void newJobOffer(JobOffer job) {
        this.currentJobOffers.add(job);
    }

    public void newJobInterview(JobInterview interview) {
        this.currentJobInterviews.add(interview);
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
}
