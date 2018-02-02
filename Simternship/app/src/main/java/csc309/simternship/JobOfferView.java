package csc309.simternship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class JobOfferView extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /*
    method for Entering into the process
    Todo: Create the actual JobOffer page
    */
    public void onClickAccept(View view)
    {
        //Todo: Change the class we intend to switch to
        Intent intent = new Intent(this, JobOfferPreview.class);
        startActivity(intent);
    }

    //method for declining an offer
    public void onClickDecline(View view)
    {
        Intent intent = new Intent(this, JobOfferPreview.class);
        startActivity(intent);

        //Todo: Delete the offer the user decides to decline
    }

    //method for going back to JobInterviewPreview if the user chooses to ignore for now
    public void onClickIgnore(View view)
    {
        Intent intent = new Intent(this, JobOfferPreview.class);
        startActivity(intent);
    }

    //method for going back to JobOfferPreview from the back button
    public void onClickBack(View view)
    {
        Intent intent = new Intent(this, JobOfferPreview.class);
        startActivity(intent);
    }
}
