public class Main {

    public static void main(String[] args) {
        DadosDeGamao dado1 = new DadosDeGamao();
        DadosTriplos dado2 = new DadosTriplos();
        JogoMalucoComSorteadores jogo = new JogoMalucoComSorteadores("jogo","Kayan","Julia",10000,dado1,dado2);

        System.out.println(jogo.jogar());
    }
}