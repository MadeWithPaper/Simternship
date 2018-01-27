package csc309.simternship;

/** Project: Simternship
* 
* @author Jacky Huang
* @version 0.1
* 
*/

import java.io.*;
import java.util.*;

public class Company
{  
   //instance variables
   private String companyName;
   private boolean availability;
   private int rating;
   private int diffculty;

   /**
   * Construct a company
   *
   * @param companyName is the name of the company 
   * @param rating is the how high the company is rated among other company more desireable for players to apply to
   * @param availability is if the company is hiring at the time of the event
   * @param diffculty is the scale which measures how hard it is to be accepted into the company
   */
   public Company(String name, int rating, boolean availability, int diffculty)
   {
      this.companyName = name;
      this.rating = rating;
      this.availability = availability;
      this.diffculty = diffculty;
   }

   /**
   *  determine if a applicant object will be hired
   *  @param applicant a player object that is applying 
   *
   */
   public boolean hire(Object applicant)
   {
      return true; //currently return true, will fill in this method when needed
   }

   //setter methods
   /**
   *  change the name of the company
   *  @param newName the new name of the company
   */
   private void setCompanyName(String newName)
   {
      this.companyName = newName;
   }

   /**
   *  sets the rating of the company
   *  @param newRating the new rating of the company
   */
   private void setRating(int newRating)
   {
      this.rating = newRating;
   }

   /**
   *  sets the availability of the company
   *  @param newAvailability the new availability of the company
   */
   private void setAvailability(boolean newAvailability)
   {
      this.availability = newAvailability;
   }

   /**
   *  sets the diffculty of the company
   *  @param newDiffculty sets the new diffculty of the company
   */
   private void setDiffculty(int newDiffculty)
   {
      this.diffculty = newDiffculty;
   }


   //getter methods
   /**
   *  returns the company name
   */
   private String getCompanyName()
   {
      return companyName;
   }

   /**
   *  returns the rating of the company
   */
   private int getRating()
   {
      return rating;
   }

   /** 
   *  returns the diffculty of the company
   */
   private int getDiffculty()
   {
      return diffculty;
   }

   /**
   *  returns the availability of the company
   */
   private boolean getAvailability()
   {
      return availability;
   }

   /**
   *  prints object to screen
   */
   public String toText()
   {
      return ("CompanyName: " + companyName + "\n" +
               "Availability: " + availability + "\n" +
               "Rating" + rating + "\n" +
               "Diffculty" + diffculty + "\n");
   }
}
