package simternship.simternship;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class SettingsScreen extends Activity {

    boolean soundOn;
    AudioManager aManager;
    int stream;
    Button save;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        aManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        stream = AudioManager.STREAM_NOTIFICATION;
        save = findViewById(R.id.saveSettings);
        cancel = findViewById(R.id.cancelSettings);
        Switch soundSwitch = findViewById(R.id.soundSwitch);
        soundOn = !isMuted();
        soundSwitch.setChecked(soundOn);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                SettingsScreen.this.soundOn = isChecked;
            }
        });

        TextView currentScore = findViewById(R.id.scoreView);
        currentScore.setText("Current Score: " + GameState.getInstance().getCurrentScore());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int adjust = soundOn ? AudioManager.ADJUST_UNMUTE : AudioManager.ADJUST_MUTE;
                    aManager.adjustStreamVolume(stream, adjust, 0);
                }
                else {
                    setStreamMute();
                }
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setStreamMute() {
        try {
            Method m = AudioManager.class.getMethod("setStreamMute", int.class,
                    boolean.class);
            m.invoke(stream, !soundOn);
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            /* Ignored because we don't care about it */
        }
    }

    private boolean isMuted() {
        try {
            Method m = AudioManager.class.getMethod("isStreamMute", int.class);
            return (boolean) m.invoke(aManager, stream);
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            return false;
        }
    }
}
