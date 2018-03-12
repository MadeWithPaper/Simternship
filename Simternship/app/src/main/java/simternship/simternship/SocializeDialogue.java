package simternship.simternship;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SocializeDialogue extends Activity {

   GameState state;
   CareerFairBooth booth;
   int pointsToAdd;
   Button dismiss;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_socialize_dialogue);
      dismiss = findViewById(R.id.dismissSocial);
      reset();

      dismiss.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            state.updateNetworking(pointsToAdd);

            finish();
         }
      });
   }

   private void reset() {
      state = GameState.getInstance();
      booth = state.getCurrentBooth();

      TextView name = findViewById(R.id.greetMessage1);
      TextView points = findViewById(R.id.greetMessage2);

      pointsToAdd = state.getRandomGenerator().random(1, 5);

      name.setText(state.getNextPersonName() + " wants to be friends!");
      points.setText("You will gain " + pointsToAdd + " point(s) if you become friends.");
   }
}
