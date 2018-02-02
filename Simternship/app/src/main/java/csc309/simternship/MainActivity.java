package csc309.simternship;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by annie on 1/28/18.
 */

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.career_fair:
                        Toast.makeText(MainActivity.this,"career fair clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.prep_question:
                        Toast.makeText(MainActivity.this,"prep question clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.account:
                        Toast.makeText(MainActivity.this,"account clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

}
