
/** Project: Simternship
* 
* @author Jiaqing Mo
* @version 0.1
* 
*/
public class Friend extends NPC
{
   private Company company;

   /**
    * construct a friend 
    * @param name the name of the friend
    * @param company the company of the friend
    */
   public Friend(Name name, Company company)
   {
      this.company = company;
      this.name = name;
   }   

   /** 
    * change the company of a friend
    * @param company the new company of the friend
    */
   public void setCompany(company){
      this.company = company;
   }

   /**
    * return the company of the friend
    */
   public Company getCompany(){
      return this.company;
   }
}

