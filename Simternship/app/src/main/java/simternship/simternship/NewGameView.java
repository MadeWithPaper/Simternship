package simternship.simternship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.NumberPicker;

public class NewGameView extends AppCompatActivity {

    NumberPicker fillDifficultyPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_view);

        fillDifficultyPicker = findViewById(R.id.difficultyPicker);
        final String [] difficulty = {"Freshman (1)", "Sophomore (2)", "Junior (3)", "Senior (4)"};
        fillDifficultyPicker.setMinValue(0);
        fillDifficultyPicker.setMaxValue(difficulty.length-1);
        fillDifficultyPicker.setDisplayedValues(difficulty);
    }

    public void onClickBeginGame(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

}
