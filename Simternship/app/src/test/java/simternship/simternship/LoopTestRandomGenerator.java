package simternship.simternship;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jiaqing on 3/13/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoopTestRandomGenerator {

    @Test
    public void Test_sample0() {   //loop 0 times,
        RandomGenerator r = new RandomGenerator();
        List<Integer> l = new ArrayList<>();
        l.add(3);
        l.add(3);
        List<Integer> res = r.sample(l,0);
        assertEquals(res.size(),0);
    }
    @Test
    public void Test_sample1() {   //loop 1 time
        RandomGenerator r = new RandomGenerator();
        List<Integer> l = new ArrayList<>();
        l.add(3);
        List<Integer> res = r.sample(l,1);
        assertEquals(res.size(),1);
    }

    @Test
    public void Test_sample2() {   //loop 2 times,
        RandomGenerator r = new RandomGenerator();
        List<Integer> l = new ArrayList<>();
        l.add(3);
        l.add(3);
        List<Integer> res = r.sample(l,2);
        assertEquals(res.size(),2);
    }


    @Test
    public void Test_sample100() {   //loop 100 times,
        RandomGenerator r = new RandomGenerator();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i<100; i++){
            l.add(i);
        }
        List<Integer> res = r.sample(l,100);
        assertEquals(res.size(),100);
    }
}
