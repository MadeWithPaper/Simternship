package simternship.simternship;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    // : Rename and change types and number of parameters
    public static PrepQuestionView newInstance() {
        PrepQuestionView fragment = new PrepQuestionView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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

        Button b = v.findViewById(R.id.button6);
        b.setOnClickListener( this);
        Button b1 =  v.findViewById(R.id.button7);
        b1.setOnClickListener( this);
        b1.setVisibility(session.hasHints() ? View.VISIBLE : View.INVISIBLE);
        return v;

    }

    // : Rename method, update argument and hook method into UI event
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

        if (!pqIterator.hasNext()){
            Button b = v.findViewById(R.id.button6);
            b.setText("Submit");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new IllegalStateException("Prep Question View must implement OnFragmentInteractionListener");
        }
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
        //  Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                    tv2 = v.getRootView().findViewById(R.id.textView11);
                    if (tv2!=null){
                        tv2.setVisibility(View.INVISIBLE);//invisible
                    }
                }
                else{
                    controller.endQuestionSession();
                }
                break;
            case R.id.button7:
                tv2 = v.getRootView().findViewById(R.id.textView11);
                if (tv2!=null){
                    tv2.setText(currentQuestion.getHint());
                    tv2.setVisibility(View.VISIBLE);//visible
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
