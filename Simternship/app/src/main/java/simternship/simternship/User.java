package simternship.simternship;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by jacky on 3/4/18.
 */

@IgnoreExtraProperties
public class User {

      private String firstName;
      private String lastName;
      private String email;

      public User() {
         // Default constructor required for calls to DataSnapshot.getValue(User.class)
      }

      public User(String firstName, String lastName, String email) {
         this.firstName = firstName;
         this.lastName = lastName;
         this.email = email;
      }

      public void setFirstName (String firstName) {this.firstName = firstName;}

      public void setLastName (String lastName) {this.lastName = lastName;}

      public void setEmail (String email) {this.email = email;}

      public String getFirstName () {return this.firstName;}

      public String getLastName () {return this.lastName;}

      public String getEmail () {return this.email;}
}
