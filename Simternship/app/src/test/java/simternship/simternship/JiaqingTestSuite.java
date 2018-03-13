package simternship.simternship;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by jiaqing on 3/13/18.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestPrepQuestion.class,
        TestPrepQuestionController.class,
        TestPrepQuestionAndPrepSession.class
})
public class JiaqingTestSuite {
}