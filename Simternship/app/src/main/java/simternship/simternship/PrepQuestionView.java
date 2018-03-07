package simternship.simternship;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrepQuestionView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrepQuestionView#newInstance} factory method to
 * create an instance of this fragment.
 */

public class PrepQuestionView extends android.app.Fragment implements View.OnClickListener{
    private Context context;
    private OnFragmentInteractionListener mListener;
    TextView tv2;

    PrepQuestion currentQuestion;
    Iterator<PrepQuestion> pqIterator;
    public PrepQuestionView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PrepQuestionView.
     */
    // TODO: Rename and change types and number of parameters
    public static PrepQuestionView newInstance() {
        PrepQuestionView fragment = new PrepQuestionView();
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
        View v = inflater.inflate(R.layout.fragment_prep_question_view, container, false);
        int questionType = PrepQuestionStartView.questionType;
        PrepSession ps = new PrepSession();
        pqIterator = ps.getQuestionList(questionType).iterator();
        if (pqIterator.hasNext()) {
            currentQuestion = pqIterator.next();
        }
        TextView tv1 = (TextView)v.getRootView().findViewById(R.id.textView7);
        if (tv1!=null) {
            tv1.setText(currentQuestion.getQuestion());
        }
        Button b = (Button) v.findViewById(R.id.button6);
        b.setOnClickListener( this);
        Button b1 = (Button) v.findViewById(R.id.button7);
        b1.setOnClickListener( this);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

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

    private void setPage(android.app.Fragment fragment) {
        //fragment.getView().setBackgroundColor(Color.WHITE);
        getFragmentManager().beginTransaction().replace(R.id.prepFrameLayout, fragment).commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6: //next click
                if (pqIterator.hasNext()) {
                    currentQuestion = pqIterator.next();
                    TextView tv1 = (TextView)v.getRootView().findViewById(R.id.textView7);
                    if (tv1!=null) {
                        tv1.setText(currentQuestion.getQuestion());
                    }
                    tv2 = (TextView)v.getRootView().findViewById(R.id.textView11);
                    if (tv2!=null){
                        tv2.setVisibility(View.INVISIBLE);//invisible
                    }
                    if (!pqIterator.hasNext()){
                        Button b = (Button) v.findViewById(R.id.button6);
                        b.setText("Submit");
                    }
                }
                else{
                    //Intent i = new Intent( , PrepQuestionScoreScreen.class);

                    setPage(PrepQuestionScoreView.newInstance());
                    //setPage(CareerFairView.newInstance());
                }


                break;
            case R.id.button7:
                tv2 = (TextView)v.getRootView().findViewById(R.id.textView11);
                if (tv2!=null){
                    tv2.setText(currentQuestion.getAnswer());
                    tv2.setVisibility(View.VISIBLE);//visible
                }
                break;
        }
    }

}
