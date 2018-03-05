package simternship.simternship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;

public class EndScreen extends AppCompatActivity
{
    LinkedList<String> firstNames = new LinkedList<>();
    LinkedList<String> lastNames = new LinkedList<>();
    LinkedList<Integer> scores = new LinkedList<>();
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Leaderboard");
    GameState theGame = GameState.getInstance();
    int finalScore = theGame.getFinalScore();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        theGame.setLastName("Samuel");
        theGame.setFirstName("Jay");
        theGame.setFinalScore(95555);
        getLeaderboard();
    }

    void getLeaderboard() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    System.out.print("GETTING THE DATA: ");
                    System.out.println(postSnapshot);
                    Iterable values = postSnapshot.getChildren();
                    Iterator iter = values.iterator();
                    DataSnapshot firstNameJSON = (DataSnapshot) iter.next();
                    DataSnapshot lastNameJSON = (DataSnapshot) iter.next();
                    DataSnapshot scoreJSON = (DataSnapshot) iter.next();
                    String first = (String) firstNameJSON.getValue();
                    firstNames.add(first);
                    String last = (String) lastNameJSON.getValue();
                    lastNames.add(last);
                    Long scoreLong = (Long) scoreJSON.getValue();
                    Integer scoreInt = scoreLong.intValue();
                    scores.add(scoreInt);
                }



                ListIterator<String> firstIter = firstNames.listIterator();
                while(firstIter.hasNext()) {
                    System.out.println(firstIter.next());
                }

                updateLeaderboard();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateLeaderboard() {
        finalScore = theGame.getFinalScore();
        System.out.print("FINAL SCORE: ");
        System.out.println(finalScore);

        ListIterator<Integer> checkScore = scores.listIterator();

        for (int i =0; checkScore.hasNext(); i++)
        {
            if (finalScore > checkScore.next() || !checkScore.hasNext()) {
                System.out.println("Adding score" + finalScore);
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



    //method for starting the game again
    public void onClickPlayAgain(View view) {
        //todo: figure out how to send to beginning of game again
        //todo: change the class that we intend to go to
        Intent intent = new Intent(this, JobOfferPreview.class);
        startActivity(intent);
    }
}
