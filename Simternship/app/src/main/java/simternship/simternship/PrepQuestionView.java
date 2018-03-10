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
import android.widget.EditText;
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

    Question currentQuestion;
    Iterator<? extends Question> pqIterator;
    QuestionController controller;
    QuestionSession<? extends Question> session;
    int questionNumber = 0;
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

        controller = GameState.getInstance().getQuestionController();

        controller.setView(this);

        session = controller.getSession();

        session.begin();

        pqIterator = controller.getSession();
        if (pqIterator.hasNext()) {
            currentQuestion = pqIterator.next();
        }

        setQuestion(v);

        Button b = (Button) v.findViewById(R.id.button6);
        b.setOnClickListener( this);
        Button b1 = (Button) v.findViewById(R.id.button7);
        b1.setOnClickListener( this);
        b1.setVisibility(session.hasHints() ? View.VISIBLE : View.INVISIBLE);
        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void setQuestion(View v) {
        String questionNum = "Question " + (++questionNumber) + ": ";
        String question = questionNum + currentQuestion.getQuestion();

        TextView tv1 = v.getRootView().findViewById(R.id.textView7);
        if (tv1!=null) {
            tv1.setText(question);
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
                    if (currentQuestion != null) {
                        EditText text = v.getRootView().findViewById(R.id.question);
                        session.submitAnswer(text.getText().toString());
                    }

                    currentQuestion = pqIterator.next();
                    setQuestion(v);
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

                    controller.endQuestionSession();
                    //setPage(CareerFairView.newInstance());
                }


                break;
            case R.id.button7:
                tv2 = (TextView)v.getRootView().findViewById(R.id.textView11);
                if (tv2!=null){
                    tv2.setText(currentQuestion.getHint());
                    tv2.setVisibility(View.VISIBLE);//visible
                }
                break;
        }
    }

}
