package simternship.simternship;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewUser extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button signUp;
    EditText fillFirstNameView;
    EditText fillLastNameView;
    EditText fillEmailView;
    EditText fillPasswordView;

   private DatabaseReference mDatabase;

   private static final String TAG = "MainActivity";

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

      mDatabase = FirebaseDatabase.getInstance().getReference();

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

        fillEmailView.requestFocus();

      findViewById(R.id.newUserLayOut).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                  0);
         }
      });

      signUp.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            signUpAttempt();
         }
      });

    }

   private void signUpAttempt () {
       String firstName = getString(fillFirstNameView);
       String lastName = getString(fillLastNameView);
       String email = getString(fillEmailView);
       String password = getString(fillPasswordView);

      if (!firstName.equals("") && !lastName.equals("") && !email.equals("") && !password.equals("")) {
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

   public void createAccount(final String email, String password) {
      mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
               onAuthSuccess(task.getResult().getUser());
            }
            else
               Toast.makeText(NewUser.this, "Registration failed!", Toast.LENGTH_SHORT).show();
         }
      });
   }

   private String getString(EditText view)
   {
      if (!view.getText().toString().equals(""))
         return view.getText().toString();
      else {
         return "";
      }
   }

   public void clearFields() {
      ViewGroup group = findViewById(R.id.newUserLayOut);
      for (int i = 0, count = group.getChildCount(); i < count; ++i) {
         View view = group.getChildAt(i);
         if (view instanceof EditText) {
            ((EditText) view).setText("");
         }
      }
   }

   private void onAuthSuccess(FirebaseUser user) {
      String firstName = getString(fillFirstNameView);
      String lastName = getString(fillLastNameView);

      // Write new user
      writeNewUser(user.getUid(), user.getEmail(), firstName, lastName);

      // Go to MainActivity
      clearFields();

      Toast.makeText(NewUser.this, "Registration success!", Toast.LENGTH_SHORT).show();
      startActivity(new Intent(NewUser.this, MainActivity.class));
   }


   private void writeNewUser(String userId, String email, String firstName, String lastName) {
      User user = new User(firstName, lastName, email);

      mDatabase.child("users").child(userId).setValue(user);
   }
}
