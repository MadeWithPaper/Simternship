package simternship.simternship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import java.util.List;
import java.util.ArrayList;
import java.util.Observer;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Observable;

public class JobOfferPreview extends AppCompatActivity implements Observer
{
    List<JobOffer> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setup();
    }

    public void update(Observable a, Object b) {
        this.reset();
    }

    private void setup()
    {
        GameState.getInstance().addObserver(this);
        reset();
    }

    @Override
    public void finish() {
        GameState.getInstance().deleteObserver(this);
        super.finish();
    }

    public void reset()
    {
        offers = GameState.getInstance().getCurrentJobOffers();
        setItems(offers);
    }

    private void setItems(List<JobOffer> items)
    {
        List<String> names = new ArrayList<>();
        for (JobOffer item : items)
        {
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

    private void goToOffer(int position)
    {
        JobOffer offer = offers.get(position);
        GameState.getInstance().setCurrentOffer(offer);
        startActivity(new Intent(this, JobOfferView.class));
    }
}