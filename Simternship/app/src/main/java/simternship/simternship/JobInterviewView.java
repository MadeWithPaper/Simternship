package simternship.simternship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class JobInterviewView extends AppCompatActivity
{

    TextView companyName = findViewById(R.id.companyNameText);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_interview_view);

        
        companyName.setText(GameState.getInstance().getCurrentInterview().getCompanyName());
    }

    /*
    method for Entering into the Interview process
    Todo: Create the actual interview process
     */
    public void onClickEnter(View view)
    {
        //Todo: Change the class we intend to switch to
        Intent intent = new Intent(this, JobInterviewPreview.class);
        startActivity(intent);
    }

    //method for going back to JobInterviewPreview
    public void onClickBack(View view)
    {
        Intent intent = new Intent(this, JobInterviewPreview.class);
        startActivity(intent);
    }
}
