package simternship.simternship;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.KeyEvent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class StartScreen extends AppCompatActivity {

    EditText userEmail;
    EditText userPass;
    Button logInButton;
    Button signUpButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //UI views
        userEmail =  findViewById(R.id.editEmail);
        userPass =  findViewById(R.id.editPassword);
        logInButton = findViewById(R.id.logInButton);
        signUpButton = findViewById(R.id.registration);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                   Toast.makeText(StartScreen.this, "Please Log In!", Toast.LENGTH_SHORT).show();
                }
            }
        };

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

       logInButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             loginAttempt();
          }
       });

       signUpButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             Toast.makeText(StartScreen.this, "New Account!", Toast.LENGTH_SHORT).show();
             clearFields();
             startActivity(new Intent(StartScreen.this, NewUser.class));
          }
          });

       findViewById(R.id.loginView).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
             im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                   0);
          }
       });
    }

        private void loginAttempt()
        {

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
                return "";
                }
        }
         @Override
        public void onStart()
        {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);
        }
         @Override
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
                    Intent i = new Intent(StartScreen.this, NewGameView.class);
                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                   String uid = user.getUid();
                   i.putExtra("user id", uid);
                    clearFields();
                    startActivity(i);
                } else{
                    Toast.makeText(StartScreen.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   public void clearFields() {
      ViewGroup group = findViewById(R.id.loginView);
      for (int i = 0, count = group.getChildCount(); i < count; ++i) {
         View view = group.getChildAt(i);
         if (view instanceof EditText) {
            ((EditText) view).setText("");
         }
      }
   }
}
