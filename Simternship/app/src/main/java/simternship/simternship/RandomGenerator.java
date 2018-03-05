package simternship.simternship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by joel on 3/4/18.
 */

public class RandomGenerator {
    private Random random;

    public RandomGenerator() {
        this.random = new Random();
    }

    /**
     * Generate a random integer in [min, max].
     * @param min the minimum integer that can be returned
     * @param max the maximum integer that can be returned
     * @return the random integer that is generated
     */
    public int random(int min, int max) {
        int range = max - min;
        return min + random.nextInt(range + 1);
    }

    public <E> List<E> shuffle(List<E> orig) {
        List<E> copy = new ArrayList<>(orig);
        Collections.shuffle(copy);
        return copy;
    }

    public <E> List<E> sample(List<E> orig, int take) {
        List<E> shuffled = this.shuffle(orig);
        List<E> items = new ArrayList<>();
        for (int i = 0; i < take; i++) {
            items.add(shuffled.get(i));
        }
        return items;
    }

    public <E> E choose(List<E> items) {
        int index = random(0, items.size() - 1);
        return items.get(index);
    }
}
