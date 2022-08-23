public class Principal {

    public static void main(String[] args) {
        DadosDeGamao dadosDeGamao = new DadosDeGamao();
        DadosTriplos dadosTriplos = new DadosTriplos();

        JogoMalucoComSorteadores<DadosDeGamao,DadosTriplos>jogo1 = new JogoMalucoComSorteadores<>(
                "JogadorDosDadosDeGamao", "JogadorDosDadosTriplos",
                1, dadosDeGamao, dadosTriplos);

        for (int i = 1; i <= 1000; i++)  jogo1.jogar();

        JogoMalucoComSorteadores<DadosDeGamao,DadosTriplos>jogo2 = new JogoMalucoComSorteadores<>(
                "JogadorDosDadosDeGamao", "JogadorDosDadosTriplos",
                2, dadosDeGamao, dadosTriplos);

        for (int i = 1; i <= 1000; i++)  jogo2.jogar();

        System.out.println("Para partidas com 1 rodada: ");
        System.out.printf("Vitórias do Jogador 1: %.2f%%\n",jogo1.getPercentualVitoriasJogador1());
        System.out.printf("Vitórias do Jogador 2: %.2f%%\n",jogo1.getPercentualVitoriasJogador2());
        System.out.printf("Empates: %.2f%%\n",jogo1.getPercentualEmpates());

        System.out.println("\nPara partidas com 2 rodadas: ");
        System.out.printf("Vitórias do Jogador 1: %.2f%%\n",jogo2.getPercentualVitoriasJogador1());
        System.out.printf("Vitórias do Jogador 2: %.2f%%\n",jogo2.getPercentualVitoriasJogador2());
        System.out.printf("Empates: %.2f%%\n",jogo2.getPercentualEmpates());
    }

}