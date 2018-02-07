package csc309.simternship;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.KeyEvent;
import android.app.ProgressDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class StartScreen extends AppCompatActivity {

     EditText userEmail;
     EditText userPass;
     Button logIn;
     ProgressDialog progress;

    public static String password = null;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "StartScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        //hides keyboard after user enters email and password
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(StartScreen.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        //UI views
        userEmail =  findViewById(R.id.editEmail);
        userPass =  findViewById(R.id.editPassword);
        logIn = findViewById(R.id.logInButton);

        setContentView(R.layout.activity_start_screen);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                }
            }
        };

        userEmail.requestFocus();
        userPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                boolean keyEntered = false;
                if (i == keyEvent.KEYCODE_ENTER && (!getString(userPass).equals("") && !getString(userEmail).equals(""))) {
                    keyEntered = true;
                    loginAttempt();

                }
                return keyEntered;
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAttempt();
            }
        });

        //add method to check if user is new or not
    }
        private void loginAttempt()
        {
            progress = ProgressDialog.show(this, "Logging in", "Please wait ...",true);

            String email = getString(userEmail);
            String password = getString(userPass);

            if (email.equals("") || password.equals(""))
                Toast.makeText(StartScreen.this, "Required fields are empty!", Toast.LENGTH_SHORT).show();
            else
                signIn(email, password);
        }

        private String getString(EditText view)
        {
            if (!view.getText().toString().equals(""))
                return view.getText().toString();
            else {
                Toast.makeText(StartScreen.this, "Please enter Email/Password", Toast.LENGTH_SHORT).show();
                return "";
                }
        }

        public void onStart()
        {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);
        }

        public  void onStop()
        {
            super.onStop();
            if (mAuthListener != null)
            {
                mAuth.removeAuthStateListener((mAuthListener));
            }
        }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(progress != null) progress.dismiss();
                if (!task.isSuccessful()){
                    StartScreen.password = null;
                    Toast.makeText(StartScreen.this, "login failed!", Toast.LENGTH_LONG).show();
                } else{
                    Intent i = new Intent(StartScreen.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}
