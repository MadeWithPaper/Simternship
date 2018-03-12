package simternship.simternship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class leaderboardScreen extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Leaderboard");
    LinkedList<String> firstNames = new LinkedList<>();
    LinkedList<String> lastNames = new LinkedList<>();
    LinkedList<Integer> scores = new LinkedList<>();
    LinkedList<String> lines = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_screen);
        getLeaderboard();
    }

    void populateLeaderboard() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lines);

        ListView lv = findViewById(R.id.listView);

        lv.setAdapter(adapter);
    }

    void getLeaderboard() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    System.err.print("GETTING THE DATA: ");
                    System.err.println(postSnapshot);
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
                    lines.add(first + " " + last + ": " + scoreInt);
                }
                ListIterator<String> firstIter = firstNames.listIterator();
                while(firstIter.hasNext()) {
                    System.err.println(firstIter.next());
                }
                populateLeaderboard();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //empty method on purpose
            }
        });
    }
}
