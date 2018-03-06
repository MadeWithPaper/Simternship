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

   private Company company;
   private ArrayList<CareerFairAttendee> listOfAttendees;
   private ArrayList<Recruiter> listOfRecuriters;

   /**
   *  construct a career fair booth
   *  @param boothName is the name of the booth, usually a comapny's name
   */
   public CareerFairBooth (Company company)
   {
      this.company = company;
      listOfAttendees = new ArrayList<> ();
      listOfRecuriters = new ArrayList<> ();
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

   public List<Recruiter> getRecruiters() {
      return this.listOfRecuriters;
   }

   public List<CareerFairAttendee> getAttendees() {
      return this.listOfAttendees;
   }

   public void addRecruiter(Recruiter recruiter) {
      listOfRecuriters.add(recruiter);
   }

   public Company getCompany() {
      return this.company;
   }
}