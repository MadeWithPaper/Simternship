package simternship.simternship;

/**
 * Created by joel on 2/23/18.
 */

public class GameState {
    private static GameState gameState;

    private String sharedState;

    private GameState() {
        sharedState = "Not Set Yet";
    }

    public static GameState getInstance() {
        if (gameState == null) {
            gameState = new GameState();
        }

        return gameState;
    }

    public void setState(String state) {
        sharedState = state;
    }

    public String getState() {
        return sharedState;
    }



}
