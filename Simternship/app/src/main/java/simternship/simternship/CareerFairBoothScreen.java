package simternship.simternship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CareerFairBoothScreen extends AppCompatActivity {

    private TextView rating, difficulty, recruiters, attendees, companyName;
    private Button interview, swag, socialize;
    private CareerFairBooth booth;
    private Company company;
    private Map<Integer, String> difficulties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_fair_booth_screen);

        difficulties = new HashMap<>();
        difficulties.put(1, "Very Easy");
        difficulties.put(2, "Easy");
        difficulties.put(3, "Medium");
        difficulties.put(4, "Difficult");
        difficulties.put(5, "Very Difficult");

        socialize = findViewById(R.id.socializeButton);
        swag = findViewById(R.id.swagButton);
        interview = findViewById(R.id.recruiterButton);
        rating = findViewById(R.id.ratingScore);
        difficulty = findViewById(R.id.difficultyScore);
        attendees = findViewById(R.id.attendeeCount);
        recruiters = findViewById(R.id.recruiterCount);
        companyName = findViewById(R.id.companyHeader);

        interview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CareerFairBoothScreen.this.startInterview();
            }
        });

        swag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CareerFairBoothScreen.this.collectSwag();
            }
        });

        socialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CareerFairBoothScreen.this.meetFriend();
            }
        });

        CareerFairBooth booth = GameState.getInstance().getCurrentBooth();
        Company company = booth.getCompany();
        setBooth(booth);
        setCompany(company);
    }

    public void setBooth(CareerFairBooth booth) {
        this.booth = booth;
        this.update();
    }

    public void setCompany(Company company) {
        this.company = company;
        this.update();
    }

    private void update() {
        if (booth != null && company != null) {
            this.rating.setText(getRating(company.getRating()));
            this.difficulty.setText(getDifficulty(company.getDifficulty()));
            this.recruiters.setText(getRecruiters(booth.getRecruiters()));
            this.attendees.setText(getAttendees(booth.getAttendees()));
            this.companyName.setText(company.getCompanyName());
        }
    }

    private String getRating(int rating) {
        String result = "";
        while (rating-- > 0) {
            result += "*";
        }
        return result;
    }

    private String getDifficulty(int difficulty) {
        return difficulties.get(difficulty);
    }

    private <T> String getRecruiters(List<T> recruiters) {
        return "# Recruiters: " + recruiters.size();
    }

    private <T> String getAttendees(List<T> attendees) {
        return "# Attendees: " + attendees.size();
    }

    private void startInterview() {
        fakeAction("Talk to Recruiter");
    }

    private void collectSwag() {
        fakeAction("Collect Swag");
    }

    private void meetFriend() {
        fakeAction("Socialize");
    }

    private void fakeAction(String action) {
        Toast.makeText(this, action + " clicked!", Toast.LENGTH_SHORT).show();
    }

}
