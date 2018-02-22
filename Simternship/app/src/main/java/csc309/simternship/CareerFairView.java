package csc309.simternship;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CareerFairView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CareerFairView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CareerFairView extends android.app.Fragment {
    private List<Company> companies;

    private Context context;
    private OnFragmentInteractionListener mListener;

    public CareerFairView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CareerFairView.
     */
    // TODO: Rename and change types and number of parameters
    public static CareerFairView newInstance() {
        CareerFairView fragment = new CareerFairView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_career_fair_view, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        this.context = context;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void visitCompany(int companyNumber) {
        String name = this.companies.get(companyNumber).getCompanyName();

        Toast.makeText(context, name + " clicked!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context, CareerFairBoothScreen.class));
    }

    private void setCompanies(List<Company> companies) {
        this.companies = companies;
        List<String> names = new ArrayList<>();
        for (Company company : companies) {
            names.add(company.getCompanyName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, names);

        ListView lv = this.getView().findViewById(R.id.careerFairCompanyList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CareerFairView.this.visitCompany(position);
            }
        });
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
