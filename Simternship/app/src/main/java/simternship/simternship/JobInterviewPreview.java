package simternship.simternship;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class JobInterviewPreview extends Activity implements Observer
{
    List<JobInterview> interviews;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_interview_preview);

        setupJobInterview();
    }


    public void update(Observable a, Object b) {
        this.resetJobInterview();
    }

    private void setupJobInterview()
    {
        GameState.getInstance().addObserver(this);
        resetJobInterview();
    }


    @Override
    public void finish() {
        GameState.getInstance().deleteObserver(this);
        super.finish();
    }

    public void resetJobInterview()
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
