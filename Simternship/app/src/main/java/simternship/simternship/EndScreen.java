package simternship.simternship;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;

public class EndScreen extends Activity
{
    LinkedList<String> firstNames = new LinkedList<>();
    LinkedList<String> lastNames = new LinkedList<>();
    LinkedList<Integer> scores = new LinkedList<>();
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Leaderboard");
    GameState theGame = GameState.getInstance();
    int finalScore = theGame.getFinalScore();
    TextView scoreView;
    TextView nameView;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        scoreView = findViewById(R.id.endScore);
        nameView = findViewById(R.id.nameView);
        playAgain = findViewById(R.id.playAgainButton);

        scoreView.setText("Score: " + theGame.getFinalScore());
        nameView.setText(theGame.getFirstName() + " " + theGame.getLastName());

        getLeaderboard();

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theGame.clearAndStart(NewGameView.class);
            }
        });
    }

    void getLeaderboard() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Iterable ivalues = postSnapshot.getChildren();
                    Iterator myIter = ivalues.iterator();
                    DataSnapshot firstNamesJSON = (DataSnapshot) myIter.next();
                    DataSnapshot lastNamesJSON = (DataSnapshot) myIter.next();
                    DataSnapshot scoresJSON = (DataSnapshot) myIter.next();
                    String firstName = (String) firstNamesJSON.getValue();
                    firstNames.add(firstName);
                    String lastName = (String) lastNamesJSON.getValue();
                    lastNames.add(lastName);
                    Long scoreLongValue = (Long) scoresJSON.getValue();
                    Integer scoreIntValue = scoreLongValue.intValue();
                    scores.add(scoreIntValue);
                }

                updateLeaderboard();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //empty placeholder method
            }
        });
    }

    public void updateLeaderboard() {
        finalScore = theGame.getFinalScore();

        ListIterator<Integer> checkScore = scores.listIterator();

        for (int i =0; checkScore.hasNext(); i++)
        {
            if (finalScore > checkScore.next() || !checkScore.hasNext()) {
                scores.add(i, finalScore);
                firstNames.add(i, theGame.getFirstName());
                lastNames.add(i, theGame.getLastName());
                break;
            }
        }

        if(scores.size() > 100)
        {
            scores.removeLast();
            firstNames.removeLast();
            lastNames.removeLast();
        }

        ListIterator<String> checkFirstName = firstNames.listIterator();
        ListIterator<String> checkLastName = lastNames.listIterator();
        checkScore = scores.listIterator();
        for (int i = 1; i <= 100 && checkFirstName.hasNext(); i++)
        {
            myRef.child(Integer.toString(i)).child("FirstName").setValue(checkFirstName.next());
            myRef.child(Integer.toString(i)).child("LastName").setValue(checkLastName.next());
            myRef.child(Integer.toString(i)).child("Score").setValue(checkScore.next());
        }
    }

}
