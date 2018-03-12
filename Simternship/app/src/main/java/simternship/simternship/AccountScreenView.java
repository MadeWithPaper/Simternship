package simternship.simternship;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;


public class AccountScreenView extends Activity {
    String name;
    Button settings;
    Button jobInterviews;
    Button jobOffers;
    Button leaderBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen_view);

        settings = findViewById(R.id.settings);
        jobInterviews = findViewById(R.id.jobInterview);
        jobOffers = findViewById(R.id.jobOffer);
        leaderBoard = findViewById(R.id.leaderboard);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AccountScreenView.this, "Setting screen!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AccountScreenView.this, SettingsScreen.class));
            }
        });
        jobOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AccountScreenView.this, "Job Offers!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AccountScreenView.this, JobOfferPreview.class));
            }
        });
        jobInterviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AccountScreenView.this, "Interview!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AccountScreenView.this, JobInterviewPreview.class));
            }
        });
        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AccountScreenView.this, "Leaderboard!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AccountScreenView.this, LeaderboardScreen.class));
            }
        });

    }
}
