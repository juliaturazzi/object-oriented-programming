import java.util.ArrayList;

public class Jogador {
    public static final int INITIAL_SCORE = 1000;
    private final String Username;
    private final String Password;
    public ArrayList<Partida> Historic;
    public int Score;
    private boolean Online;
    private boolean Playing;

    public Jogador(String Username2, String Password2) {
        this.Username = Username2;
        this.Password = Password2;
        this.Score = INITIAL_SCORE;
        this.Online = false;
        this.Playing = false;
        this.Historic = new ArrayList();
    }

    public String getPasswordPlayer() {
        return this.Password;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setOnline(boolean Online) {
        this.Online = Online;
    }

    public boolean setPlaying(boolean Playing) {
        this.Playing = Playing;
        return Playing;
    }

    public boolean IsPlaying() {
        return this.Playing;
    }

    public boolean IsOffline() {
        return !this.Online;
    }

    public void Historic(Partida match) {
        this.Historic.add(match);
    }

    public void setScore(boolean finalresult) {
        if (finalresult) this.Score += 1;
        if (!finalresult) this.Score -= 1;
    }
}