package simternship.simternship;

import android.app.Fragment;
import android.app.Activity;

/**
 * Created by joel on 3/9/18.
 */

public class InterviewQuestionController implements QuestionController {
    private JobInterview interview;
    private Activity activity;

    public InterviewQuestionController(JobInterview interview, Activity activity) {
        this.interview = interview;
        this.activity = activity;
    }

    @Override
    public void endQuestionSession() {
        this.activity.finish();
    }

    @Override
    public QuestionSession getSession() {
        return interview;
    }

    @Override
    public void setView(Fragment fragment) {
        //method left empty on purpose
    }
}
