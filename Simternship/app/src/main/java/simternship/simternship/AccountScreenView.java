package simternship.simternship;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

//public class AccountScreenView extends AppCompatActivity {
public class AccountScreenView extends Activity {
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen_view);

    }

    public void onClickSetting(View view) {
       Toast.makeText(AccountScreenView.this, "Setting screen!", Toast.LENGTH_SHORT).show();
       startActivity(new Intent(AccountScreenView.this, settingsScreen.class));
    }

    public void onClickLeaderboard(View view) {
       Toast.makeText(AccountScreenView.this, "Leaderboard!", Toast.LENGTH_SHORT).show();
       startActivity(new Intent(AccountScreenView.this, leaderboardScreen.class));
    }

    public void onClickInterviews(View view) {
        Toast.makeText(AccountScreenView.this, "Interview!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AccountScreenView.this, JobInterviewPreview.class));
    }

    public void onClickJobOffers(View view) {
        Toast.makeText(AccountScreenView.this, "Job Offers!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AccountScreenView.this, JobOfferPreview.class));
    }
}
