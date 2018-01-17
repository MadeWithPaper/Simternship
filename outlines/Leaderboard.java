import java.util.*;

/**
 * <h1>Leaderboard</h1>
 * The Leaderboard class stores the data needed
 * to display a leaderboard.
 *
 * @author Joel Dentici
 * @version 0.1
 * @since 2018-01-16
 */
class Leaderboard
{
   private List<Player> players;

   /**
    * Constructs a Leaderboard
    *
    * @param players The players the leaderboard will report on.
    */
   public Leaderboard(List<Player> players)
   {
      this.players = players;
   }

   /**
    * Computes and returns the current state of
    * the leaderboard.
    */
   public List<LeaderboardEntry> getEntries()
   {
      //create leaderboard from players
      List<LeaderboardEntry> entries = new ArrayList<>();
      for (Player player : this.players)
      {
         entries.add(new LeaderboardEntry(
            player.firstName + " " + player.lastName,
            player.getScore()
         ));
      }

      //sort the leaderboard
      Collections.sort(entries, new Comparator<LeaderboardEntry>() {
         @Override
         public int compare(LeaderboardEntry one, LeaderboardEntry two)
         {
            return (new Integer(two.score)).compare(one.score);
         }
      });

      return entries;
   }   

   /**
    * Called when a new player starts playing to
    * add them to the leaderboard.
    *
    * @param player The newly created player.
    */
   public void addPlayer(Player player)
   {

   }

   /**
    * Called when a player removes their "account"
    * to remove them from the leaderboard.
    *
    * @param player The player that was removed.
    */
   public void removePlayer(Player player)
   {

   }

   /**
    * <h1>Player.LeaderboardEntry</h1>
    * LeaderboardEntry objects represent an
    * entry in the leaderboard.
    *
    * @author Joel Dentici
    * @version 0.1
    * @since 2018-01-16
    */
   static class LeaderboardEntry
   {
      public final String playerName;
      public final int score;

      /**
       * Constructs a LeaderboardEntry
       *
       * @param playerName The name of the player
       * @param score The player's score
       */
      public LeaderboardEntry(String playerName, int score)
      {
         this.playerName = playerName;
         this.score = score;
      }
   }
}