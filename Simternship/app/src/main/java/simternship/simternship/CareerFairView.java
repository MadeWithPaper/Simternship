package simternship.simternship;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CareerFairView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CareerFairView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CareerFairView extends android.app.Fragment implements Observer {
    private CareerFair careerFair;

    private Context context;
    private OnFragmentInteractionListener cfvListener;
    private CountDownTimer timer;

    public CareerFairView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CareerFairView.
     */
    public static CareerFairView newInstance() {
        CareerFairView fragment = new CareerFairView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_career_fair_view, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (cfvListener != null) {
            cfvListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            cfvListener = (OnFragmentInteractionListener) context;
        } else {
            throw new IllegalStateException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        this.context = context;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GameState.getInstance().addObserver(this);
        this.resetState();
    }

    @Override
    public void onDetach() {
        timer.cancel();
        GameState.getInstance().deleteObserver(this);
        super.onDetach();
        cfvListener = null;
    }

    private void visitCompany(int companyNumber) {
        CareerFairBooth booth = careerFair.getBooths().get(companyNumber);

        String name = booth.getCompany().getCompanyName();

        GameState.getInstance().setCurrentBooth(booth);

        Toast.makeText(context, name + " clicked!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context, CareerFairBoothScreen.class));
    }

    private void resetState() {
        ListView lv = this.getView().findViewById(R.id.careerFairCompanyList);
        this.careerFair = GameState.getInstance().getCareerFairController().getCareerFair();
        if (careerFair != null)
            this.setCompanies();
        else
            lv.setVisibility(View.INVISIBLE);

        this.setTime();
    }

    private void setTime() {
        long time = GameState.getInstance().getCareerFairController().timeRemaining();

        if (timer != null)
            timer.cancel();

        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                CareerFairView.this.setCurrentTime(l);
            }

            @Override
            public void onFinish() {
                //empty placeholder method
            }
        };

        timer.start();

        setCurrentTime(time);
    }

    private void setCurrentTime(long time) {
        TextView tv = getView().findViewById(R.id.CareerFairTimer);

        long min = time / (60 * 1000);
        long minInSec = min * 60;
        long sec = time / 1000 - minInSec;

        String timeStr = String.format("%02d:%02d", min, sec);

        String message = careerFair != null ? "Remaining: " : "Time To Career Fair: ";

        tv.setText(message + timeStr);
    }

    private void setCompanies() {
        List<String> names = new ArrayList<>();
        for (CareerFairBooth booth : careerFair.getBooths()) {
            names.add(booth.getCompany().getCompanyName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, names);

        ListView lv = this.getView().findViewById(R.id.careerFairCompanyList);
        lv.setVisibility(View.VISIBLE);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CareerFairView.this.visitCompany(position);
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        this.resetState();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
