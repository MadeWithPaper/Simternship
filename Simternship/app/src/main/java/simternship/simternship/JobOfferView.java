package simternship.simternship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JobOfferView extends AppCompatActivity
{
    GameState state;
    JobOffer offer;
    Button accept;
    Button decline;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        state = GameState.getInstance();
        offer = state.getCurrentOffer();
        accept = findViewById(R.id.acceptButton);
        decline = findViewById(R.id.declineButton);

        TextView company = findViewById(R.id.companyNameText);
        TextView salary = findViewById(R.id.jobTitleText);

        findViewById(R.id.jobSalaryText).setVisibility(View.INVISIBLE);
        findViewById(R.id.jobLocationText).setVisibility(View.INVISIBLE);

        company.setText(offer.getCompanyName());
        salary.setText("$" + offer.getSalary().toString());

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.setChosenOffer(state.getCurrentOffer());
                state.endGame();
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.removeJobOffer(state.getCurrentOffer());
                finish();
            }
        });
    }
}
