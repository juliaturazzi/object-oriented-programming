public class Partida {

    private final Jogador Player1;
    private final Jogador Player2;

    public int Result;

    public Partida(Jogador Player1, Jogador Player2) {
        this.Player1 = Player1;
        this.Player2 = Player2;
    }

    public void setResult(int result) {
        this.Result = result;
    }

    public Jogador getPlayer1() {
        return this.Player1;
    }

    public Jogador getPlayer2() {
        return this.Player2;
    }
}
