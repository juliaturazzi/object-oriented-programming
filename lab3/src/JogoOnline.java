import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Random;


public class JogoOnline {
    public static final int MAXIMO_PARTIDAS = 5;
    private ArrayList<Jogador> Players;

    public JogoOnline() {
        Players = new ArrayList<>();
    }

    public static Object Match(Jogador Player1, Jogador Player2) {
        if (Player1.IsOffline() || Player1.IsPlaying() || Player2.IsOffline() || Player2.IsPlaying())
            return null;
        Partida partida = new Partida(Player1, Player2);
        Player1.setPlaying(true);
        Player2.setPlaying(true);
        return partida;
    }

    public static void MatchClosed(Partida match, int result) {
        match.setResult(result);

        match.getPlayer1().Historic(match);
        match.getPlayer2().Historic(match);

        match.getPlayer1().setPlaying(false);
        match.getPlayer2().setPlaying(false);

        if (result == 0) return; // Empate.

        if (result == 1) { //Player 1 ganha.
            match.getPlayer1().setScore(true);
            match.getPlayer2().setScore(false);
        }

        if (result == 2) { //Player 2 ganha.
            match.getPlayer1().setScore(false);
            match.getPlayer2().setScore(true);
        }
    }

    public void Register(String UsernamePlayer, String PasswordPlayer) {
        Jogador player = new Jogador(UsernamePlayer, PasswordPlayer);
        Players.add(player);
    }

    public Jogador GetPlayer(String playerName) {
        for (int i = 0; i < Players.size(); i++) {
            Jogador j = Players.get(i);
            if (j.getUsername().equals(playerName))
                return j;
        }
        return null;
    }

    public void Login(@NotNull String UsernamePlayer, String PasswordPlayer) {
        for (int i = 0; i < Players.size(); i++) {
            Jogador Player = Players.get(i);
            if (Player.getPasswordPlayer().equals(PasswordPlayer) && Player.getUsername().equals(UsernamePlayer)) {
                Player.setOnline(true);
                return;
            }
        }
    }

    public void Logout(String UsernamePlayer) {
        for (int i = 0; i < Players.size(); i++) {
            Jogador Player = Players.get(i);

            if (Player.getUsername().equals(UsernamePlayer)) {
                Player.setOnline(false);
                return;
            }
        }
    }

    public Jogador Opponent(Jogador chosen) {
        int counter = 0;
        ArrayList<Jogador> opponents = new ArrayList<>();

        for (int i = 0; i < Players.size(); i++) {
            Jogador Player = Players.get(i);
            if (Player != chosen && !Player.IsOffline() && !Player.IsPlaying()) {
                opponents.add(Player);
                counter++;
                continue;
            }
        }
        if(counter != 0) {
            Random rand = new Random();
            return opponents.get(rand.nextInt(opponents.size()));
        }
        return null;
    }

    public ArrayList<Jogador> getPlayers() {
        return this.Players;
    }

    public void setPlayers(ArrayList<Jogador> players) {
        Players = players;
    }
}





