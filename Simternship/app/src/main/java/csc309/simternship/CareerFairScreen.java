package csc309.simternship;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.Arrays;
import java.util.List;
import android.widget.Button;

public class CareerFairScreen extends AppCompatActivity {

    private List<Button> companyButtons;
    private Button prev, next;
    private List<String> companies; //this would be actual companies later
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_fair_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.companies = Arrays.asList(
            "Google",
            "Facebook",
            "Apple",
            "Hooli",
            "Uber",
            "Lyft"
        );

        this.companyButtons = Arrays.asList(
                (Button) findViewById(R.id.company1),
                (Button) findViewById(R.id.company2),
                (Button) findViewById(R.id.company3),
                (Button) findViewById(R.id.company4)
        );


        prev = (Button) findViewById(R.id.prevbtn);
        next = (Button) findViewById(R.id.nextbtn);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CareerFairScreen.this.setPage(CareerFairScreen.this.page - 1);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CareerFairScreen.this.setPage(CareerFairScreen.this.page + 1);
            }
        });

        this.setPage(0);
    }

    private void setPage(int newPage) {
        //check if this is a valid page number
        int numPages = (int) Math.ceil(
                (double)this.companies.size() / this.companyButtons.size());
        boolean badPageNumber = newPage == numPages || newPage < 0;
        //this shouldn't be able to happen if everything is functioning
        assert(!badPageNumber);
        boolean nextVisible = newPage < numPages - 1;
        boolean prevVisible = newPage > 0;

        //update company buttons
        int start = this.companyButtons.size() * newPage;
        int end = this.companies.size();
        for (int i = 0, j = start; i < 4; i++, j++) {
            Button btn = this.companyButtons.get(i);
            updateButton(btn, j < end);
            if (j < end)
                btn.setText(this.companies.get(j));
        }

        //update nav buttons
        updateButton(next, nextVisible);
        updateButton(prev, prevVisible);

        this.page = newPage;
    }

    private void updateButton(Button button, boolean visible) {
        if (visible) {
            button.setVisibility(View.VISIBLE);
        }
        else {
            button.setVisibility(View.INVISIBLE);
        }
    }


}
