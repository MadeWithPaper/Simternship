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
import android.widget.Toast;

public class CareerFairScreen extends AppCompatActivity {

    private List<Button> companyButtons;
    private Button prev, next;
    private List<Company> companies;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_fair_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.companyButtons = Arrays.asList(
                (Button) findViewById(R.id.company1),
                (Button) findViewById(R.id.company2),
                (Button) findViewById(R.id.company3),
                (Button) findViewById(R.id.company4)
        );



        prev = findViewById(R.id.prevbtn);
        next = findViewById(R.id.nextbtn);

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

        int i = 0;
        for (Button btn : this.companyButtons) {
            final int buttonNumber = i++;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CareerFairScreen.this.visitCompany(buttonNumber);
                }
            });
        }

        this.setCompanies(Arrays.asList(
                new Company("Google", 5, true, 5),
                new Company("Facebook", 5, true, 5),
                new Company("Apple", 5, true, 5),
                new Company("Hooli", 5, true, 5),
                new Company("Uber", 5, true, 5),
                new Company("Lyft", 5, true, 5),
                new Company("HP", 5, true, 5),
                new Company("Adobe Systems", 5, true, 5),
                new Company("Intel", 5, true, 5),
                new Company("Nvidia", 5, true, 5),
                new Company("Pied Piper", 5, true, 5),
                new Company("Western Digital", 5, true, 5),
                new Company("VMware", 5, true, 5)
        ));
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
                btn.setText(this.companyName(j));
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

    private void visitCompany(int buttonNumber) {
        int companyNumber = this.page * this.companyButtons.size() + buttonNumber;
        String company = this.companyName(companyNumber);
        Toast.makeText(this, company + " clicked!", Toast.LENGTH_SHORT).show();
    }

    private String companyName(int companyNumber) {
        return this.companies.get(companyNumber).getCompanyName();
    }

    private void setCompanies(List<Company> companies) {
        this.companies = companies;
        this.setPage(0);
    }


}
