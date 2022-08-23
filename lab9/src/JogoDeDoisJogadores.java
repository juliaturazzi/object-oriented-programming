import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {
    String nomeJogo;
    String nomeJogador1;
    String nomeJogador2;

    int numeroDeRodadas;

    ArrayList<Integer> historicoResultados;

    public JogoDeDoisJogadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas) {
        this.nomeJogo = nomeJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;
        this.historicoResultados = new ArrayList<>();
    }

    public String getNomeJogo() { return nomeJogo; }

    public String getNomeJogador1() { return nomeJogador1; }

    public String getNomeJogador2() { return nomeJogador2; }

    public int getNumeroDeRodadas() { return numeroDeRodadas; }

    protected abstract int executarRodadaDoJogo();
    protected int pontosJogador1 = 0;
    protected int pontosJogador2 = 0;

    public String jogar() {
        for (int i = 0; i < getNumeroDeRodadas(); i++) {
            executarRodadaDoJogo();
            historicoResultados.add(executarRodadaDoJogo());
        }

        for (Integer historicoResultado : historicoResultados) {
            if (historicoResultado == 1)  pontosJogador1++;
            if (historicoResultado == 2)  pontosJogador2++;
        }

        if (pontosJogador1 > pontosJogador2)
            return "O jogador " + getNomeJogador1() + " venceu o jogo por " +
                    pontosJogador1 + " a " + pontosJogador2;

        if (pontosJogador1 < pontosJogador2)
            return "O jogador " + getNomeJogador2() + " venceu o jogo por " +
                    pontosJogador2 + " a " + pontosJogador1;

        return "O jogo terminou em empate após " + numeroDeRodadas + " rodadas.";
    }
}