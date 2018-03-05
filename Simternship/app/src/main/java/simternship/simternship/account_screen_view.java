package simternship.simternship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class account_screen_view extends AppCompatActivity {

    String name;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen_view);

    }

    public void onClickSetting(View view) {
       Toast.makeText(account_screen_view.this, "Setting screen!", Toast.LENGTH_SHORT).show();
       startActivity(new Intent(account_screen_view.this, settings_screen.class));
    }

    public void onClickLeaderboard(View view) {
       Toast.makeText(account_screen_view.this, "Leaderboard!", Toast.LENGTH_SHORT).show();
       startActivity(new Intent(account_screen_view.this, leaderboard_screen.class));
    }

    public void onClickInterviews(View view) {
        Toast.makeText(account_screen_view.this, "Interview!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(account_screen_view.this, JobInterviewPreview.class));
    }

    public void onClickJobOffers(View view) {
        Toast.makeText(account_screen_view.this, "Job Offers!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(account_screen_view.this, JobOfferPreview.class));
    }
}
