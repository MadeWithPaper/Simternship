package simternship.simternship;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccountSettingsView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccountSettingsView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountSettingsView extends android.app.Fragment {
    private Context context;
    private OnFragmentInteractionListener mListener;

    public AccountSettingsView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AccountSettingsView.
     */
    public static AccountSettingsView newInstance() {
        AccountSettingsView fragment = new AccountSettingsView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_settings_view, container, false);
    }

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
            throw new IllegalStateException(context.toString()
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

        ProgressBar energyBar = getView().findViewById(R.id.energyBar);
        energyBar.setProgress(GameState.getInstance().getCurrentEnergy());
        ProgressBar networkBar = getView().findViewById(R.id.networkBar);
        networkBar.setProgress(GameState.getInstance().getCurrentNetworking());
        TextView name = getView().findViewById(R.id.acountName);
        name.setText(GameState.getInstance().getFirstName() + " "
                + GameState.getInstance().getLastName());

        getView().findViewById(R.id.settings).setOnClickListener(
                new Listener(context, SettingsScreen.class)
        );

        getView().findViewById(R.id.leaderboard).setOnClickListener(
                new Listener(context, LeaderboardScreen.class)
        );

        getView().findViewById(R.id.button2).setOnClickListener(
                new Listener(context, JobInterviewPreview.class)
        );

        getView().findViewById(R.id.jobOffer).setOnClickListener(
                new Listener(context, JobOfferPreview.class)
        );
    }

    private class Listener implements Button.OnClickListener {
        Class type;
        Context context;
        Listener(Context context, Class type) {
            this.type = type;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            startActivity(new Intent(context, type));
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
        void onFragmentInteraction(Uri uri);
    }
}
