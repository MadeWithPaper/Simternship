package simternship.simternship;

import android.app.Activity;

/**
 * Created by joel on 2/23/18.
 */

public class GameState {
    private CareerFair careerFair;

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


}
