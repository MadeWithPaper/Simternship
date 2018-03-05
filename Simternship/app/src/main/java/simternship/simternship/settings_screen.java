package simternship.simternship;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class settings_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

       Switch soundSwitch = findViewById(R.id.soundSwitch);

       soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          AudioManager aManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             if (isChecked) {
                aManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
             } else {
                aManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
             }
          }
       });
    }

   public void onClickSave(View view)
    {
       Intent i = new Intent(settings_screen.this, MainActivity.class);
       Toast.makeText(settings_screen.this, "settings saved!", Toast.LENGTH_LONG).show();
       startActivity(i);
    }

    public void onClickCancel(View view)
    {
       Intent i = new Intent(settings_screen.this, MainActivity.class);
       Toast.makeText(settings_screen.this, "Canceled!", Toast.LENGTH_LONG).show();
       startActivity(i);
    }
}
