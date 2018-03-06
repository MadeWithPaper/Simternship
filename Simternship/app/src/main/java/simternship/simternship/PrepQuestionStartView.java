package simternship.simternship;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrepQuestionStartView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrepQuestionStartView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrepQuestionStartView extends android.app.Fragment implements View.OnClickListener{


    private Context context;
    private OnFragmentInteractionListener mListener;

    public PrepQuestionStartView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CareerFairView.
     */
    // TODO: Rename and change types and number of parameters
    public static PrepQuestionStartView newInstance() {
        PrepQuestionStartView fragment = new PrepQuestionStartView();
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
        View v = inflater.inflate(R.layout.prep_question_start_view, container, false);
        Button b = (Button) v.findViewById(R.id.button3);
        b.setOnClickListener(this);
        return v;

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
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3: //start click
                setPage(PrepQuestionView.newInstance());
        }
    }
    private void setPage(android.app.Fragment fragment) {
        //fragment.getView().setBackgroundColor(Color.WHITE);
        getFragmentManager().beginTransaction().replace(R.id.startPrepFrameLayout, fragment).commit();

    }

}
