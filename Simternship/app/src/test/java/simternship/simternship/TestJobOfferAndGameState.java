package simternship.simternship;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.math.BigDecimal;

/**
 * Created by matthewbrown on 3/13/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestJobOfferAndGameState {
    @Test
    public void TestNewJobOffer() {
        NewGameView ngv = mock(NewGameView.class);
        GameState.newGame(ngv, "joe", "smith", 1);
        GameState pointer = GameState.getInstance();
        Company c = new Company("apple", 10, true, 10);
        JobOffer j = new JobOffer(c, new java.math.BigDecimal(100000));
        pointer.newJobOffer(j);
        JobOffer result = pointer.getCurrentJobOffers().get(0);
        assertEquals(result, j);
    }

    @Test
    public void TestRemoveJobOffer() {
        NewGameView ngv = mock(NewGameView.class);
        GameState.newGame(ngv, "joe", "smith", 1);
        GameState pointer = GameState.getInstance();
        Company c = new Company("apple", 10, true, 10);
        JobOffer j = new JobOffer(c, new java.math.BigDecimal(100000));
        pointer.newJobOffer(j);
        pointer.removeJobOffer(j);
        int result = pointer.getCurrentJobOffers().size();
        assertEquals(0, result);
    }
}
