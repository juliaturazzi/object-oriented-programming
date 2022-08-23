public class JogoMalucoComSorteadores extends JogoDeDoisJogadores {
    Sorteador sorteador1;
    Sorteador sorteador2;

    int numero1 = 0;
    int numero2 = 0;

    public JogoMalucoComSorteadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas,Sorteador sorteador1, Sorteador sorteador2) {
        super(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);
        this.sorteador1=sorteador1;
        this.sorteador2=sorteador2;
    }

    @Override
    protected int executarRodadaDoJogo() {
        numero1 = sorteador1.sortear();
        numero2 = sorteador2.sortear();

        if (numero1 > numero2)  return 1;
        if (numero1 < numero2)  return 2;

        return 0;
    }
}