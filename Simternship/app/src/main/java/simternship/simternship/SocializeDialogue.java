package simternship.simternship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SocializeDialogue extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_socialize_dialogue);
   }

   public void onClickDismissSocial(View view)
   {
      finish();
   }
}
