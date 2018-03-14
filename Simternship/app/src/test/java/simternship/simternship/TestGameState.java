package simternship.simternship;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by matthewbrown on 3/13/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestGameState {
    @Test
    public void TestUpdateNetwork() {
        NewGameView ngv = mock(NewGameView.class);
        GameState.newGame(ngv, "joe", "smith", 1);
        GameState pointer = GameState.getInstance();
        pointer.updateNetworking(10);
        int result = pointer.getCurrentNetworking();
        assertEquals(result, 10);
    }

    @Test
    public void TestGSLastName() {
        NewGameView ngv = mock(NewGameView.class);
        GameState.newGame(ngv, "joe", "smith", 1);
        GameState pointer = GameState.getInstance();
        pointer.updateEnergy(-10);
        int result = pointer.getCurrentEnergy();
        assertEquals(result, 90);
    }
}
