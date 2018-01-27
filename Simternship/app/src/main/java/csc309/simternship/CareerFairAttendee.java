
/** Project: Simternship
* 
* @author Jiaqing Mo
* @version 0.1
* 
*/

package csc309.simternship;

public class CareerFairAttendee extends NPC
{
   private CareerFairBooth booth;

   /**
    * construct a career fair attendee
    * @param name is the name of attendee
    * @param booth is the career fair booth he is at
    */
   public CareerFairAttendee(String name, CareerFairBooth booth)
   {
      this.name = name; 
      this.booth = booth;
   }

   /**
    * change the career fair booth of the attendee
    * @param booth the new booth of the attendee
    */
   public void setCareerFairBooth(CareerFairBooth booth)
   {
      this.booth = booth;
   }  

   /**
    * return the career fair booth of the attendee
    */
   public CareerFairBooth getCareerFairBooth()
   {
      return this.booth;
   }   
   
}

