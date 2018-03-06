package simternship.simternship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import java.util.List;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class JobInterviewPreview extends AppCompatActivity
{
    List<JobInterview> interviews;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_interview_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setup();
    }

    private void setup()
    {
        reset();
    }

    public void reset()
    {
        interviews = GameState.getInstance().getCurrentJobInterviews();
        setItems(interviews);
    }

    private void setItems(List<JobInterview> items)
    {
        List<String> names = new ArrayList<>();
        for (JobInterview item : items)
        {
            names.add(item.getCompanyName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);

        //change the id to whatever it is for your list view
        ListView lv = findViewById(R.id.interviewsList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //call whatever function you need to
                JobInterviewPreview.this.goToInterview(position);
            }
        });
    }

    private void goToInterview(int position)
    {
        JobInterview interview = interviews.get(position);
        Toast.makeText(this, interview.getCompanyName() + " clicked!", Toast.LENGTH_SHORT).show();
        GameState.getInstance().setCurrentInterview(interview);
        startActivity(new Intent(this, JobInterviewView.class));
    }
}
