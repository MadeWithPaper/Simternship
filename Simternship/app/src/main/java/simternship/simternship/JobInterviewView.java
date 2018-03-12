package simternship.simternship;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JobInterviewView extends AppCompatActivity
    implements PrepQuestionView.OnFragmentInteractionListener
{

    JobInterview interview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_interview_view);

        GameState state = GameState.getInstance();
        interview = state.getCurrentInterview();
        QuestionController controller = new InterviewQuestionController(interview, this);

        GameState.getInstance().setQuestionController(controller);

        setPage(PrepQuestionView.newInstance());

    }
    @Override
    public void finish() {
        GameState.getInstance().completeInterview(interview);
        GameState.getInstance().removeJobInterview(interview);

        super.finish();
    }

    private void setPage(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();
    }

    public void onFragmentInteraction(Uri uri) {
        //method left empty on purpose
    }
}
