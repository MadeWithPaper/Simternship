package simternship.simternship;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by matthewbrown on 3/13/18.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestGameState.class,
        TestUser.class,
        TestJobOfferAndGameState.class
})
public class MattTestSuite {
}
