package simternship.simternship;

import android.app.Fragment;

/**
 * Created by joel on 3/9/18.
 */

public interface QuestionController {
    void endQuestionSession();

    QuestionSession getSession();

    void setView(Fragment fragment);
}
