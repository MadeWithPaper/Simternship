/** Project: Simternship
* 
* @author Jacky Huang
* @version 0.1
* 
*/

package simternship.simternship;

import java.util.*;

public class CareerFairBooth
{ 

   private String boothName;
   private ArrayList<CareerFairAttendee> listOfAttendees;
   private ArrayList<Recruiter> listOfRecuriters;

   /**
   *  construct a career fair booth
   *  @param boothName is the name of the booth, usually a comapny's name
   */
   public CareerFairBooth (String boothName)
   {
      this.boothName = boothName;
      listOfAttendees = new ArrayList<> ();
      listOfRecuriters = new ArrayList<> ();
      //call methods to add recuriters upon creation of the booth
      //listOfRecuriters.add(new Recuriter(name));
   }

   /**
   *  each SWAG from the booth will give the player 1 - 5 energy points
   *  @param attendee a player that stops by at a booth
   */
   public int giveSWAG(Object attendee)
   {
      Random r = new Random();

      return r.nextInt((5 - 1 ) + 1 ) + 1;
   }

   /**
   *  queue up a list of NPC and player 
   *  @param player object will be added to the list
   */
   public void queueAttendee(CareerFairAttendee player)
   {
      listOfAttendees.add(player);
   }

   //setters
   /**
   *  change the name of the booth
   *  @param newName the new name for the booth
   */
   private void setBoothName(String newName)
   {
      this.boothName = newName;
   }

   //getters
   /**   
   *  return the name of the booth
   */
   private String getBoothName()
   {
      return boothName;
   }

   /**
   *  prints out the list of attendees in the line for the booth
   */
   private void getListOfAttendees()
   {
      System.out.print("The current queue for " + boothName + " are as follows ");
      for (Object p : listOfAttendees)
      {
         System.out.print(p);
      }
      System.out.println(" ");
   }

   /**
   *  prints out a list of recuriters at the booth
   */
   private void getListOfRecuriters()
   {
      System.out.print("Recuriters present at " + boothName + " are as follows ");
      for (Object q : listOfRecuriters)
      {
         System.out.print(q);
      }
      System.out.println(" ");
   }

   public List<Recruiter> getRecruiters() {
      return this.listOfRecuriters;
   }

   public List<CareerFairAttendee> getAttendees() {
      return this.listOfAttendees;
   }
}