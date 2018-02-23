package simternship.simternship;

/** Project: Simternship
* 
* @author Jiaqing Mo
* @version 0.1
* 
*/

public class Recruiter extends NPC
{
   private Company company;
   private CareerFairBooth booth;

   /**
    * construct a recruiter 
    * @param name the name of the recruiter
    * @param booth the career fair booth of the recruiter
    * @param company the company of the recruiter
    */
   public Recruiter(String name, CareerFairBooth booth, Company company)
   {
      this.name = name;
      this.booth = booth;
      this.company = company;
   }

   /**
    * change the company of the recruiter
    * @param comapny the company of the recuiter
    */
   public void setCompany(Company company){
      this.company = company;
   }

   /**
    * return the company of the recruiter
    */
   public Company getCompany(){
      return this.company;
   }

   /**
    * change the career fair booth of the recruiter
    * @param booth the career fair booth of the recruiter
    */
   public void setCareerFairBooth(CareerFairBooth booth)
   {
      this.booth = booth;
   }   

   /**
    * return the career fair booth of the recruiter
    */
   public CareerFairBooth getCareerFairBooth()
   {
      return this.booth;
   }   

}

