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
    Button logInButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        //UI views
        userEmail =  findViewById(R.id.editEmail);
        userPass =  findViewById(R.id.editPassword);
        logInButton = (Button) findViewById(R.id.logInButton);

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
    }

    public void onClickLogIn(View view) {
        //loginAttempt();
        signIn("test@gmail.com", "123456");
    }
        private void loginAttempt()
        {
            //progress = ProgressDialog.show(this, "Logging in", "Please wait ...",true);

            /*String email = getString(userEmail);
            String password = getString(userPass);

            if (email.equals("") || password.equals(""))
                Toast.makeText(StartScreen.this, "Required fields are empty!", Toast.LENGTH_SHORT).show();
            else
                signIn(email, password);*/

            signIn("test@gmail.com", "123456");
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
                if (task.isSuccessful()){
                    Intent i = new Intent(StartScreen.this, MainActivity.class);

                    Toast.makeText(StartScreen.this, "login success!", Toast.LENGTH_LONG).show();
                    startActivity(i);
                } else{
                    Toast.makeText(StartScreen.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
