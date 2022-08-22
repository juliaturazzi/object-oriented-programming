public class Partida {
    public static final int VITORIA_JOGADOR_1 = 1;
    public static final int VITORIA_JOGADOR_2 = 2;
    public static final int EMPATE = 0;
    public static final int PARTIDA_EM_ANDAMENTO = -1;
    public static final int PARTIDA_NAO_INICIADA = -2;

    private final Jogador jogador1;
    private final Jogador jogador2;

    private int resultado;

    public Partida(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.resultado = PARTIDA_NAO_INICIADA;
    }

    public Jogador getJogador1() { return jogador1; }

    public Jogador getJogador2() { return jogador2; }

    public void setResultado(int resultado) { this.resultado = resultado; }

    public int getResultado() { return this.resultado; }
}