package simternship.simternship;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by jiaqing on 3/13/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoopTestGameState {

    @Test
    public void Test_bestOffer0() {   //loop 0 times
        GameState g = new GameState(new Activity(), "John", "Lee", 1);
        JobOffer res = g.bestOffer();
        assertEquals(res,null);
    }

    @Test
    public void Test_bestOffer1() {   //loop once
        GameState g = new GameState(new Activity(), "John", "Lee", 1);
        g.newJobOffer(new JobOffer(new Company("Facebook", 1, true, 1),new BigDecimal(201410)));
        JobOffer res = g.bestOffer();
        assertEquals(res.getSalary(),new BigDecimal(201410));
    }

    @Test
    public void Test_bestOffer2() {   //loop twice
        GameState g = new GameState(new Activity(), "John", "Lee", 1);
        Company c1 = new Company("Facebook", 1, true, 1);
        Company c2 = new Company("Google", 1, true, 1);
        g.newJobOffer(new JobOffer(c1,new BigDecimal(201410)));
        g.newJobOffer(new JobOffer(c2,new BigDecimal(414185)));
        JobOffer res = g.bestOffer();
        assertEquals(res.getSalary(),new BigDecimal(414185));
    }
    @Test
    public void Test_bestOffer10() {   //loop ten times
        GameState g = new GameState(new Activity(), "John", "Lee", 1);
        Company c1 = new Company("Facebook", 1, true, 1);
        for (int i = 0; i<10; i++){
            g.newJobOffer(new JobOffer(c1,new BigDecimal(i*10000)));
        }
        JobOffer res = g.bestOffer();
        assertEquals(res.getSalary(),new BigDecimal(90000));
    }
}
