package csc309.simternship;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewUser extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button signUp;
    EditText fillFirstNameView;
    EditText fillLastNameView;
    EditText fillEmailView;
    EditText fillPasswordView;
    NumberPicker fillDifficultyPicker;

   private static final String TAG = "MainActivity";

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
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
        signUp = findViewById(R.id.register);
        fillFirstNameView = findViewById(R.id.fillFirstName);
        fillLastNameView = findViewById(R.id.fillLastName);
        fillEmailView = findViewById(R.id.fillEmail);
        fillPasswordView = findViewById(R.id.fillPassword);
        fillDifficultyPicker = findViewById(R.id.difficultyPicker);

        fillEmailView.requestFocus();

      findViewById(R.id.newUserLayOut).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                  0);
         }
      });

        /*fillLastNameView.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View view, int i, KeyEvent keyEvent) {
              boolean keyEntered = false;
              if (i == keyEvent.KEYCODE_ENTER && (!getString(fillFirstNameView).equals(""))
                    && (!getString(fillLastNameView).equals(""))
                    && (!getString(fillEmailView).equals(""))
                    && (!getString(fillPasswordView).equals(""))
                    && (fillDifficultyPicker.getValue() > 0)) {
                 keyEntered = true;
                 signUpAttempt();
           }
           return keyEntered;
        }
        });*/

        final String [] difficulty = {"Freshman (1)", "Sophomore (2)", "Junior (3)", "Senior (4)"};
        fillDifficultyPicker.setMinValue(0);
        fillDifficultyPicker.setMaxValue(difficulty.length-1);
        fillDifficultyPicker.setDisplayedValues(difficulty);

        /*fillDifficultyPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
           @Override
           public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {

           }
        });*/
    }
   public void onClickSignUp(View view) {
       signUpAttempt();
   }

   private void signUpAttempt () {
       String firstName = getString(fillFirstNameView);
       String lastName = getString(fillLastNameView);
       String email = getString(fillEmailView);
       String password = getString(fillPasswordView);
       int difficulty = fillDifficultyPicker.getValue();

      if (!firstName.equals("") && !lastName.equals("") && !email.equals("") && !password.equals("")
            && difficulty > 0) {
         createAccount(email, password);
      }
   }

   @Override
   public void onStart() {
       super.onStart();
       mAuth.addAuthStateListener(mAuthListener);
   }

   @Override
   public void onStop(){
       super.onStop();
       if(mAuthListener != null) {
          mAuth.removeAuthStateListener(mAuthListener);
       }
   }

   public void createAccount(String email, String password) {
      mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
               Toast.makeText(NewUser.this, "Registration success!", Toast.LENGTH_SHORT).show();
               Intent i = new Intent(NewUser.this, MainActivity.class);
               startActivity(i);
            }
            else
               Toast.makeText(NewUser.this, "Registration failed!", Toast.LENGTH_SHORT).show();
         }
      });
   }

   private String getString(EditText view) {
      if (!view.getText().toString().equals(null) && !view.getText().toString().equals(""))
         return view.getText().toString();
      else {
         Toast.makeText(NewUser.this, "Fields are empty", Toast.LENGTH_SHORT).show();
         return "";
      }
   }
}
