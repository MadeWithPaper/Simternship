package simternship.simternship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class JobInterviewView extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_interview_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
