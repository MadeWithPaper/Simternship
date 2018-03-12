package simternship.simternship;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;

/**
 * Created by annie on 1/28/18.
 */

public class MainActivity extends AppCompatActivity
implements CareerFairView.OnFragmentInteractionListener,
        PrepQuestionView.OnFragmentInteractionListener,
        AccountSettingsView.OnFragmentInteractionListener,
        PrepQuestionStartView.OnFragmentInteractionListener,
        PrepQuestionScoreView.OnFragmentInteractionListener{
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TabLayout tl = findViewById(R.id.TabLayout);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setPage(tab.getPosition());
            }

            // Compliant
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //ignore
            }

            // Compliant
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //ignore
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
                setPage(PrepQuestionStartView.newInstance());
                break;
            case 1:
                setPage(CareerFairView.newInstance());
                break;
            case 2:
                setPage(AccountSettingsView.newInstance());
                break;
            default:
                break;
        }
    }

    // Compliant
    @Override
    public void onFragmentInteraction(Uri uri) {
        //ignore
    }

}
