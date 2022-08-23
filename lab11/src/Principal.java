import java.util.HashMap;
import java.util.Map;

public class Principal {
    private static final int CONT_REPETICOES_POR_SIMULACAO = 100;
    private static DadoComum dadoNormal = new DadoComum();

    private static void percentuaisHonestos(int numeroDeRodadasPorPartida) {
        SorteadorViaDoisParesConsecutivos sorteadorPares = new SorteadorViaDoisParesConsecutivos(dadoNormal);
        SorteadorViaTrio sorteadorTrio = new SorteadorViaTrio(dadoNormal);

        JogoMalucoComSorteadores<SorteadorViaDoisParesConsecutivos, SorteadorViaTrio> jogo1;

        jogo1 = new JogoMalucoComSorteadores<>(
                "Jogador1", "Jogador2",
                numeroDeRodadasPorPartida, sorteadorPares, sorteadorTrio);

        for (int i = 0; i < CONT_REPETICOES_POR_SIMULACAO; i++)  jogo1.jogar();
        System.out.println(String.format("\n\n" + "      Para partidas com %d rodada(s):\n" +
                "      Vitórias do Jogador 1: %f%%\n" + "      Vitórias do Jogador 2: %f%%\n" +
                "      Empates: %f%%",
                numeroDeRodadasPorPartida,
                jogo1.getPercentualVitoriasJogador1(), jogo1.getPercentualVitoriasJogador2(),
                jogo1.getPercentualEmpates()));
    }

    private static void percentualJogador1Viciado(int numeroDeRodadasPorPartida) {
        Map<Integer, Integer> frequencias = new HashMap<>();

        frequencias.put(1, 5);
        frequencias.put(2, 1);
        frequencias.put(3, 1);
        frequencias.put(4, 1);
        frequencias.put(5, 1);
        frequencias.put(6, 1);

        DadoGenerico dadoViciado = new DadoGenerico(frequencias);
        SorteadorViaDoisParesConsecutivos sorteadorPares = new SorteadorViaDoisParesConsecutivos(dadoViciado);
        SorteadorViaTrio sorteadorTrio = new SorteadorViaTrio(dadoNormal);

        JogoMalucoComSorteadores<SorteadorViaDoisParesConsecutivos, SorteadorViaTrio> jogo2;

        jogo2 = new JogoMalucoComSorteadores<>(
                "Jogador1", "Jogador2",
                numeroDeRodadasPorPartida, sorteadorPares, sorteadorTrio);

        for (int i = 0; i < CONT_REPETICOES_POR_SIMULACAO; i++)   jogo2.jogar();
        System.out.println(String.format("\n\n" +
                        "      Para partidas com %d rodada(s):\n" +
                        "      Vitórias do Jogador 1: %f%%\n" +
                        "      Vitórias do Jogador 2: %f%%\n" +
                        "      Empates: %f%%",
                numeroDeRodadasPorPartida,
                jogo2.getPercentualVitoriasJogador1(),
                jogo2.getPercentualVitoriasJogador2(),
                jogo2.getPercentualEmpates()));
    }

    public static void main(String[] args) {
        System.out.println("\n\nJogo Normal:\n");
        for (int numeroDeRodadas = 1; numeroDeRodadas <= 2000; numeroDeRodadas*=2)  percentuaisHonestos(numeroDeRodadas);

        System.out.println("\n\nJogo Viciado:\n");
        for (int numeroDeRodadas = 1; numeroDeRodadas <= 2000; numeroDeRodadas*=2)  percentualJogador1Viciado(numeroDeRodadas);
    }
}