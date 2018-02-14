package csc309.simternship;
import java.util.*;
import java.math.BigDecimal;

/**
 * <h1>Player</h1>
 * The Player class keeps track of information about
 * the person playing the game.
 *
 * @author Joel Dentici
 * @version 0.1
 * @since 2018-01-16
 */
public class Player extends Observable
{
   private double networkingScore;
   private double energyScore;
   public final String firstName;
   public final String lastName;
   private String email; //do we need this?
   private String hashedPassword; //do we need this?
   private Resume currentResume;
   private List<JobInterview> completedInterviews;
   private List<PrepSession> completedPrepSessions;
   private List<Friend> friends; //not sure how this fits into game yet


   /**
    * Constructs a Player.
    *
    * @param firstName The first name of the player
    * @param lastName The last name of the player
    * @param email The email of the player
    * @param hashedPassword The hashed password
    * @param networkingScore The player's networking score
    * @param energyScore The player's energy score
    */
   public Player(String firstName, String lastName, String email,
      String hashedPassword, double networkingScore, double energyScore)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.hashedPassword = hashedPassword;
      this.networkingScore = networkingScore;
      this.energyScore = energyScore;
      this.completedInterviews = new ArrayList<>();
      this.completedPrepSessions = new ArrayList<>();
      this.friends = new ArrayList<>();
   }

   /**
    * Creates a new player. Use the constructor to load a Player from
    * persistence (note, this is assuming we don't just serialize everything
    * using Java serialization or a serialization library).
    *
    * @param firstName The first name of the player
    * @param lastName The last name of the player
    * @param email The email of the player
    * @param password The password as the player entered it.
    */
   public static Player create(String firstName, String lastName, String email,
      String password)
   {
      return new Player(firstName, lastName, email,
         Player.hashPassword(password), 0, 0);
   }

   /**
    * Hashes the player's password before storing it on the object.
    *
    * @param password The plaintext password to hash.
    */
   public static String hashPassword(String password)
   {
      return "";
   }

   /**
    * This should be called whenever the player completes
    * an interview. This will be used to compute job offers.
    *
    * @param interview The interview that was completed
    */
   public void completeInterview(JobInterview interview)
   {
      this.completedInterviews.add(interview);
      this.changeOccurred();
   }

   /**
    * This should be called whenever the player completes
    * a question prep session. This will be used to compute
    * the player's score.
    *
    * @param session The prep session that was completed.
    */
   public void completePrepSession(PrepSession session)
   {
      this.completedPrepSessions.add(session);
      this.changeOccurred();
   }

   /**
    * This should be called whenever the player makes a new
    * friend in the game. This will be used to compute the
    * player's score.
    *
    * @param friend The new friend.
    */
   public void makeFriend(Friend friend)
   {
      this.friends.add(friend);
      this.changeOccurred();
   }

   /**
    * Computes the job offers tha the player has received, along with
    * their respective salaries, and sorts them by salary (descending). Returns this
    * sorted list of job offers.
    */
   public List<JobOffer> getJobOffers()
   {
      return new ArrayList<>();
   }

   public JobOffer getBestOffer() {
      List<JobOffer> offers = getJobOffers();
      if (offers.isEmpty()) {
         return null;
      }

      return offers.get(0);
   }

   /**
    * Returns the salary of the best job offer. If there are no offers,
    * then 0 is returned to signify there is no salary.
    */
   public BigDecimal getBestSalary()
   {
      JobOffer bestOffer = getBestOffer();
      if (bestOffer != null) {
         return bestOffer.salary;
      }

      return new BigDecimal(0);
   }

   /**
    * Computes and returns the total score for the user.
    */
   public int getScore()
   {
      return 0;
   }

   /**
    * Returns the current networking score.
    */
   public double getNetworkingScore()
   {
      return 0;
   }

   /**
    * Returns the current energy score.
    */
   public double getEnergyScore()
   {
      return 0;
   }

   /**
    * Call to gain energy from collecting swag.
    *
    * @param swag The swag that was collected
    */
   public void collectSwag(Swag swag)
   {

      this.changeOccurred();
   }

   /**
    * Call to gain energy at the beginning of the
    * month. This should be called when the Player logs
    * in with the current date. Energy will be awarded for
    * any months that energy has not yet been awarded.
    *
    * @param date The current date.
    */
   public void addEnergy(Date date)
   {

      this.changeOccurred();
   }

   /**
    * Call to lose energy when waiting in a line. If the
    * line length is below a certain threshold, no energy is
    * lost.
    *
    * @param lineLength The length of the line.
    */
   public void loseEnergy(int lineLength)
   {

      this.changeOccurred();
   }

   /**
    * Call when a recruiter is met. This will increase the
    * networking score.
    *
    * @param recruiter The recruiter that was met.
    */
   public void meetRecruiter(Recruiter recruiter)
   {

      this.changeOccurred();
   }

   /**
    * Called by mutator methods to notify observers of
    * the change to the Player's data.
    */
   private void changeOccurred()
   {
      this.setChanged();
      this.notifyObservers();
   }

   /**
    * Gets the Player's full name.
    * @return String the full name
    */
   public String getFullName() {
      return this.firstName + " " + this.lastName;
   }

}