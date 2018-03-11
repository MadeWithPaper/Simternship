package simternship.simternship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class NewGameView extends AppCompatActivity {

    NumberPicker fillDifficultyPicker;

    String firstName;
    String lastName;
    String uid;
    Button startGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_view);
      startGame = findViewById(R.id.register);
       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       uid = user.getUid();
       FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference myRef = database.getReference("users").child(uid);
       myRef.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
             Iterable data = dataSnapshot.getChildren();
             Iterator readData = data.iterator();
             DataSnapshot snap = (DataSnapshot) readData.next();
             System.err.print(snap.getValue());
             snap = (DataSnapshot) readData.next();
             firstName = (String) snap.getValue();
             snap = (DataSnapshot) readData.next();
             lastName = (String) snap.getValue();
             TextView welcome = findViewById(R.id.welcomeText);
             welcome.setText("Welcome " + firstName + " " + lastName);
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
             //empty method on purpose
          }
       });



       fillDifficultyPicker = findViewById(R.id.difficultyPicker);
        final String [] difficulty = {"Freshman (1)", "Sophomore (2)", "Junior (3)", "Senior (4)"};
        fillDifficultyPicker.setMinValue(0);
        fillDifficultyPicker.setMaxValue(difficulty.length-1);
        fillDifficultyPicker.setDisplayedValues(difficulty);

       startGame.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             GameState.newGame(this, firstName, lastName, fillDifficultyPicker.getValue() + 1);

             startActivity(new Intent(NewGameView.this, MainActivity.class));
          }
       });
    }
}
