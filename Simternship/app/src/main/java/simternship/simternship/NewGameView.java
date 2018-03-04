package simternship.simternship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class NewGameView extends AppCompatActivity {

    NumberPicker fillDifficultyPicker;
    TextView welcomeMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_view);

       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       TextView welcome = findViewById(R.id.welcomeText);
       welcome.setText("Welcome " + user.getEmail());


       fillDifficultyPicker = findViewById(R.id.difficultyPicker);
        final String [] difficulty = {"Freshman (1)", "Sophomore (2)", "Junior (3)", "Senior (4)"};
        fillDifficultyPicker.setMinValue(0);
        fillDifficultyPicker.setMaxValue(difficulty.length-1);
        fillDifficultyPicker.setDisplayedValues(difficulty);
    }

    public void onClickBeginGame(View view) {
        GameState.getInstance().newGame(this);
        startActivity(new Intent(this, MainActivity.class));
    }

}
