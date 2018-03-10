package simternship.simternship;

import android.app.Fragment;

import java.util.Arrays;

/**
 * Created by joel on 3/9/18.
 */

public class PrepQuestionController implements QuestionController {
    PrepSession session;
    Fragment view;

    public PrepQuestionController(int type) {
        if (type == 0) {
            PrepQuestion q1 = new PrepQuestion("What motivated you to apply for this position?", "Your response should cover two main areas: 1) Why this opportunity appeals" +
                    "to you and 2) How your skills and career goals will contribute to the employer." +
                    "Thorough research of the employer and position is key to articulating why" +
                    "they appeal to you. The employer wants to know that you are genuinely" +
                    "interested. To describe how your skills and career goals fit with the position," +
                    "review the job description and develop examples to demonstrate how your" +
                    "skills and experience meet the employer’s needs.");
            PrepQuestion q2 = new PrepQuestion("Describe your current or most recent job, internship, or leadership experience.", "It’s easy to get lost in describing the day-to-day details of your most recent" +
                    "experience. What employers really want to know is how this experience" +
                    "makes you qualified for the position. Focus on accomplishments and" +
                    "achievements, using specific examples (quantify when possible), to highlight" +
                    "your ability to contribute to the employer.");

            this.session = new PrepSession(Arrays.asList(q1, q2));
        }
        else {
            PrepQuestion q1 = new PrepQuestion("What is polymorphism?", "polymorphism is the ability (in programming) to present the same interface for differing underlying forms (data types).");
            PrepQuestion q2 = new PrepQuestion("Find the common elements of 2 int arrays", "think about sorting first");

            this.session = new PrepSession(Arrays.asList(q1, q2));
        }
    }

    private void setPage(Fragment fragment) {
        view.getFragmentManager().beginTransaction().replace(R.id.prepFrameLayout, fragment).commit();

    }

    @Override
    public void endQuestionSession() {
        setPage(PrepQuestionScoreView.newInstance());
    }

    @Override
    public QuestionSession getSession() {
        return session;
    }

    @Override
    public void setView(Fragment fragment) {
        this.view = fragment;
    }
}
