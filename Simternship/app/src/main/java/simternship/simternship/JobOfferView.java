package simternship.simternship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class JobOfferView extends AppCompatActivity
{
    GameState state;
    JobOffer offer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        state = GameState.getInstance();
        offer = state.getCurrentOffer();

        TextView company = findViewById(R.id.companyNameText);
        TextView salary = findViewById(R.id.jobTitleText);

        findViewById(R.id.jobSalaryText).setVisibility(View.INVISIBLE);
        findViewById(R.id.jobLocationText).setVisibility(View.INVISIBLE);

        company.setText(offer.getCompanyName());
        salary.setText("$" + offer.getSalary().toString());
    }

    /*
    method for Entering into the process
    Todo: Create the actual JobOffer page
    */
    public void onClickAccept(View view)
    {
        state.setChosenOffer(state.getCurrentOffer());
        state.endGame();
    }

    //method for declining an offer
    public void onClickDecline(View view)
    {
        state.removeJobOffer(state.getCurrentOffer());
        finish();
    }
}
