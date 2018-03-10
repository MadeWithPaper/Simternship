package simternship.simternship;

import java.util.List;
import java.util.Iterator;

/**
 * Created by joel on 3/9/18.
 */

public interface QuestionSession<T extends Question> extends Iterator<T> {
    boolean hasHints();

    void begin();

    List<T> getQuestions();

    void submitAnswer(String answer);
}
