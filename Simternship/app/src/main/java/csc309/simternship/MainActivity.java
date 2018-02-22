package csc309.simternship;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.support.v4.view.ViewPager;
import android.app.Fragment;

/**
 * Created by annie on 1/28/18.
 */

public class MainActivity extends AppCompatActivity
implements CareerFairView.OnFragmentInteractionListener,
        PrepQuestionView.OnFragmentInteractionListener,
        AccountSettingsView.OnFragmentInteractionListener {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TabLayout tl = findViewById(R.id.TabLayout);

        FrameLayout fl = findViewById(R.id.mainFrameLayout);

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setPage(tab.getPosition());
                /*
                switch (tab.getPosition()) {
                    //case R.id.career_fair:
                    case 0:
                        startActivity(new Intent(MainActivity.this, PrepQuestionScreen.class));
                        Toast.makeText(MainActivity.this, "prep question clicked", Toast.LENGTH_SHORT).show();
                        break;
                    //case R.id.prep_question:
                    case 1:
                        //startActivity(new Intent(MainActivity.this, CareerFairScreen.class));
                        setPage(1);
                        Toast.makeText(MainActivity.this, "career fair clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                    //case R.id.account:
                        startActivity(new Intent(MainActivity.this, account_screen_view.class));
                        Toast.makeText(MainActivity.this, "account clicked", Toast.LENGTH_SHORT).show();
                        break;
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setPage(0);
    }

    private void setPage(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();
    }

    private void setPage(int pos) {
        switch (pos) {
            case 0:
                setPage(PrepQuestionView.newInstance());
                break;
            case 1:
                setPage(CareerFairView.newInstance());
                break;
            case 2:
                setPage(AccountSettingsView.newInstance());
                break;
        }
    }

    public void onFragmentInteraction(Uri uri) {

    }

}
