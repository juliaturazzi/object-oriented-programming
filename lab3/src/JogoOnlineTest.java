import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JogoOnlineTest {

    JogoOnline newJogo;

    @Before
    public void setUp() {
        newJogo = new JogoOnline();
        newJogo.Register("Julia", "jujuba123");
        newJogo.Register("Kayan", "kaka321");
        newJogo.Register("Bruen", "bruen789");
        newJogo.Register("Fatima", "fafa567");
        newJogo.Register("Euvaldo", "eueu");
    }

    @Test
    public void RegisterTest() { // Players register
        Assert.assertEquals(5, newJogo.getPlayers().size()); // 5 people

        newJogo.Register("Nara", "nana");
        Assert.assertEquals(6, newJogo.getPlayers().size()); // 6 people
    }

    @Test
    public void LoginTest() { // Players login
        newJogo.Login("Kayan", "kaka321");
        newJogo.Login("Julia", "jujuba123");
        newJogo.Login("Joana", "jujuba123"); // Wrong username
        newJogo.Login("Bruen", "bruen123"); // Incorrect password

        Jogador Kayan = newJogo.GetPlayer("Kayan");
        Jogador Julia = newJogo.GetPlayer("Julia");
        Jogador Bruen = newJogo.GetPlayer("Bruen");
        Assert.assertFalse(Kayan.IsOffline()); // Online
        Assert.assertFalse(Julia.IsOffline()); // Online
        Assert.assertTrue(Bruen.IsOffline()); // Offline
    }

    @Test
    public void LogoutTest() { // Players logout
        Jogador Julia = newJogo.GetPlayer("Julia");
        Assert.assertTrue(Julia.IsOffline()); // Offline

        newJogo.Login("Julia", "jujuba123");
        Assert.assertFalse(Julia.IsOffline()); // Online

        newJogo.Logout("Julia");
        Assert.assertTrue(Julia.IsOffline()); // Offline
    }

    @Test
    public void PlayingTest() { // All players playing
        Jogador Fatima = newJogo.GetPlayer("Fatima");
        Jogador Euvaldo = newJogo.GetPlayer("Euvaldo");

        newJogo.Login("Fatima", "fafa567");
        newJogo.Login("Euvaldo", "eueu");
        Assert.assertFalse(Fatima.IsOffline()); // Online
        Assert.assertFalse(Euvaldo.IsOffline()); // Offline

        JogoOnline.Match(Fatima, Euvaldo);
        Assert.assertTrue(Fatima.IsPlaying()); // Is playing
        Assert.assertTrue(Euvaldo.IsPlaying()); // Is playing
    }

    @Test
    public void PlayingTest2() { // 1 player offline
        Jogador Fatima = newJogo.GetPlayer("Fatima");
        Jogador Euvaldo = newJogo.GetPlayer("Euvaldo");

        newJogo.Login("Fatima", "fafa567");
        newJogo.Login("Euvaldo", "eueu");
        Assert.assertFalse(Fatima.IsOffline()); // Online
        Assert.assertFalse(Euvaldo.IsOffline()); // Online

        newJogo.Logout("Euvaldo");
        Assert.assertTrue(Euvaldo.IsOffline()); // Offline

        JogoOnline.Match(Fatima, Euvaldo);
        Assert.assertFalse(Fatima.IsPlaying()); // Not playing
        Assert.assertFalse(Euvaldo.IsPlaying()); // Not playing
    }

    @Test
    public void MatchClosedTest() { // Match closed + match score + final score
        Jogador Kayan = newJogo.GetPlayer("Kayan");
        Jogador Bruen = newJogo.GetPlayer("Bruen");

        newJogo.Login("Kayan", "kaka321");
        newJogo.Login("Bruen", "bruen789");
        Assert.assertFalse(Kayan.IsOffline()); // Online
        Assert.assertFalse(Bruen.IsOffline()); // Online


        Partida KayanxBruen = new Partida(Kayan, Bruen); // Partida 1
        JogoOnline.Match(Kayan, Bruen);
        Assert.assertTrue(Kayan.IsPlaying()); // Is playing
        Assert.assertTrue(Bruen.IsPlaying()); // Is playing

        JogoOnline.MatchClosed(KayanxBruen, 1);
        Assert.assertFalse(Kayan.IsPlaying()); // Not playing
        Assert.assertFalse(Bruen.IsPlaying()); // Not playing

        Assert.assertTrue(Kayan.Score > Bruen.Score); // Score after 1 match


        Partida KayanxBruen2 = new Partida(Kayan, Bruen); // Match 2
        JogoOnline.Match(Kayan, Bruen);

        Assert.assertTrue(Kayan.IsPlaying()); // Is playing
        Assert.assertTrue(Bruen.IsPlaying()); // Is playing

        JogoOnline.MatchClosed(KayanxBruen2, 0);
        Assert.assertFalse(Kayan.IsPlaying()); // Not playing
        Assert.assertFalse(Bruen.IsPlaying()); // Not playing

        Assert.assertTrue(Kayan.Score > Bruen.Score); // Score after 2 matches


        Partida KayanxBruen3 = new Partida(Kayan, Bruen); // Match 3
        JogoOnline.Match(Kayan, Bruen);
        Assert.assertTrue(Kayan.IsPlaying()); // Is playing
        Assert.assertTrue(Bruen.IsPlaying()); // Is playing

        JogoOnline.MatchClosed(KayanxBruen3, 2);
        Assert.assertFalse(Kayan.IsPlaying()); // Not playing
        Assert.assertFalse(Bruen.IsPlaying()); // Not playing

        Assert.assertEquals(Kayan.Score, Bruen.Score); // Score after 3 matches


        Partida KayanxBruen4 = new Partida(Kayan, Bruen); // Match 4
        JogoOnline.Match(Kayan, Bruen);
        Assert.assertTrue(Kayan.IsPlaying()); // Is playing
        Assert.assertTrue(Bruen.IsPlaying()); // Is playing

        JogoOnline.MatchClosed(KayanxBruen4, 2);
        Assert.assertFalse(Kayan.IsPlaying()); // Not playing
        Assert.assertFalse(Bruen.IsPlaying()); // Not playing

        Assert.assertTrue(Bruen.Score > Kayan.Score); // Final score

    }

    @Test
    public void OpponentTest() {
        Jogador Kayan = newJogo.GetPlayer("Kayan");
        Jogador Bruen = newJogo.GetPlayer("Bruen");
        Jogador Euvaldo = newJogo.GetPlayer("Euvaldo");
        Jogador Fatima = newJogo.GetPlayer("Fatima");

        newJogo.Login("Kayan", "kaka321");
        newJogo.Login("Bruen", "bruen789");
        newJogo.Login("Euvaldo", "eueu");
        newJogo.Login("Fatima", "fafa567");
        Assert.assertFalse(Kayan.IsOffline()); // Online
        Assert.assertFalse(Bruen.IsOffline()); // Online
        Assert.assertFalse(Fatima.IsOffline()); // Online
        Assert.assertFalse(Euvaldo.IsOffline()); // Online

        JogoOnline.Match(Kayan, Bruen);
        Assert.assertTrue(Kayan.IsPlaying()); // Is playing
        Assert.assertTrue(Bruen.IsPlaying()); // Is playing
        Assert.assertFalse(Fatima.IsPlaying()); // Not playing
        Assert.assertFalse(Euvaldo.IsPlaying()); // Not playing

        newJogo.Opponent(Kayan);
        Assert.assertFalse(newJogo.Opponent(Kayan).IsPlaying()); // Opponent not playing
        Assert.assertFalse(newJogo.Opponent(Kayan).IsOffline()); // Opponent offline
    }
}
