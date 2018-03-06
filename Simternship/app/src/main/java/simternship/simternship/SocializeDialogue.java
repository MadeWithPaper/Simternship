package simternship.simternship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SocializeDialogue extends AppCompatActivity {

   GameState state;
   CareerFairBooth booth;
   int pointsToAdd;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_socialize_dialogue);

      reset();
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

   public void onClickDismissSocial(View view)
   {
      state.updateNetworking(pointsToAdd);

      finish();
   }
}
