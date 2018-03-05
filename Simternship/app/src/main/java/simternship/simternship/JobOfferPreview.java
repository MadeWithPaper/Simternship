package simternship.simternship;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.util.Arrays;
import java.math.BigDecimal;

public class JobOfferPreview extends AppCompatActivity
{
    List<JobOffer> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setup();
    }

    private void setup() {
        offers = Arrays.asList(
                new JobOffer("Google", new BigDecimal("100000")),
                new JobOffer("Apple", new BigDecimal("200000"))
        );

        setItems(offers);
    }

    private void setItems(List<JobOffer> items) {
        List<String> names = new ArrayList<>();
        for (JobOffer item : items) {
            names.add(item.getCompanyName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);

        //change the id to whatever it is for your list view
        ListView lv = findViewById(R.id.offersList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //call whatever function you need to
                JobOfferPreview.this.goToOffer(position);
            }
        });
    }

    private void goToOffer(int position) {
        JobOffer offer = offers.get(position);
        Toast.makeText(this, offer.getCompanyName() + " clicked!", Toast.LENGTH_SHORT).show();

    }

    public void onClickInterview(View view)
    {
        Intent intent = new Intent(this, JobOfferView.class);
        startActivity(intent);
    }
}