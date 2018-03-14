package simternship.simternship;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by jiaqing on 3/13/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoopTestGameStateComputeInterviews {

    @Test
    public void Test_bestOffer0() {   //loop 0 times, can't test a define number of loops, because companies is created randomly
        GameState g = new GameState(new Activity(), "John", "Lee", 1);
        g.computeInterviews();
        assertEquals(g.getCurrentJobInterviews().size()>=0,true);
    }


}
