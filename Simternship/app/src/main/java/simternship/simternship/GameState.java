package simternship.simternship;

import android.app.Activity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joel on 2/23/18.
 */

public class GameState {
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
        companies = companyFactory.createCompanies();
        careerFair = careerFairFactory.createCareerFair(companies);
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
        this.companyFactory = new CompanyFactory(5, 20);
        this.careerFairFactory = new CareerFairFactory(
                2, 5, 2,
                30, names);

    }


}
