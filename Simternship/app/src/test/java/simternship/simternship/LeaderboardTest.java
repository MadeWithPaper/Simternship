package simternship.simternship;


import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joel on 2/14/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LeaderboardTest {
    @Test
    public void entries_areSorted() {
        Player one = createPlayer("Jim", "Smith", 100);
        Player two = createPlayer("Joe", "Johnson", 200);
        Player three = createPlayer("Jane", "Johnson", 150);
        List<Player> players = Arrays.asList(one, two, three);

        List<Leaderboard.LeaderboardEntry> expected = Arrays.asList(
                new Leaderboard.LeaderboardEntry("Joe Johnson", 200),
                new Leaderboard.LeaderboardEntry("Jane Johnson", 150),
                new Leaderboard.LeaderboardEntry("Jim Smith", 100)
        );

        Leaderboard leaderboard = new Leaderboard(players);

        List<Leaderboard.LeaderboardEntry> actual = leaderboard.getEntries();

        assertEquals(expected, actual);

    }


    Player createPlayer(String first, String last, int score) {

        Player player = mock(Player.class);
        when(player.getFullName()).thenReturn(first + " " + last);
        when(player.getScore()).thenReturn(score);

        return player;
    }
}
