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
 * {@link PrepQuestionScoreView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrepQuestionScoreView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrepQuestionScoreView extends android.app.Fragment implements View.OnClickListener{


    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CareerFairView.
     */
    //Rename and change types and number of parameters
    public static PrepQuestionScoreView newInstance() {
        PrepQuestionScoreView fragment = new PrepQuestionScoreView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    private void setPage(android.app.Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.prepScoreFrameLayout, fragment).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.prep_question_score_view, container, false);
        TextView tv2 =  v.getRootView().findViewById(R.id.textView);
        if (tv2 != null) {
            tv2.setText("Good job! You got " + "" + "Questions Prepared!");
            // Inflate the layout for this fragment
        }
        Button b = v.findViewById(R.id.button4);
        b.setOnClickListener(this);
        return v;
    }

    //  Rename method, update argument and hook method into UI event
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
            throw new IllegalStateException("PrepQuestionScoreView must implement OnFragmentInteractionListener");
        }
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
        // : Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onClick(View v) {
        if (v.getId()==R.id.button4) {
                setPage(PrepQuestionStartView.newInstance());
        }
    }
}
