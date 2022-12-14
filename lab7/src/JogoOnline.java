import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class JogoOnline {
    public static final int PONTOS_VITORIA = 1;
    public static final int PONTOS_EMPATE = 0;
    public static final int PONTOS_DERROTA = -1;

    private Random random;

    private HashMap<String, Jogador> jogadorByUsername;


    public JogoOnline() {
        this.random = new Random();
        this.jogadorByUsername = new HashMap<>();
    }

    public Jogador cadastrarJogador(String username, String senha) {
        if (encontrarJogador(username) != null)  return null;

        Jogador novoJogador = new Jogador(username);
        novoJogador.setSenha(senha);

        this.jogadorByUsername.put(username, novoJogador);

        return novoJogador;
    }

    private Jogador encontrarJogador(String username) { return this.jogadorByUsername.get(username); }

    public void fazerLogin(String username, String senha) throws UsuarioInexistenteException, SenhaInvalidaException {
        Jogador jogador = encontrarJogador(username);

        if (jogador == null)  throw new UsuarioInexistenteException();

        if (jogador.getSenha().equals(senha))  jogador.setOnline(true);

        if (!jogador.getSenha().equals(senha))  throw new SenhaInvalidaException();
    }

    public void fazerLogout(Jogador jogador) {
        if(!jogador.isOnline())  throw new RuntimeException();

        jogador.setOnline(false);
    }

    public Partida iniciarPartida(Jogador jogador1, Jogador jogador2) {
        if (jogador1 == null || jogador2 == null ||
                !jogador1.isOnline() || jogador1.isJogando() ||
                !jogador2.isOnline() || jogador2.isJogando())
            return null;

        Partida novaPartida = new Partida(jogador1, jogador2);
        jogador1.setJogando(true);
        jogador2.setJogando(true);

        novaPartida.setResultado(Partida.PARTIDA_EM_ANDAMENTO);

        return novaPartida;
    }

    public void encerrarPartida(Partida partida, int resultado) {
        if (partida.getResultado() != Partida.PARTIDA_EM_ANDAMENTO)  throw new RuntimeException("Partida j?? encerrada!");

        if (resultado != Partida.EMPATE &&
                resultado != Partida.VITORIA_JOGADOR_1 &&
                resultado != Partida.VITORIA_JOGADOR_2)
            throw new IllegalArgumentException("Resultado inv??lido");


        partida.setResultado(resultado);

        Jogador jogador1 = partida.getJogador1();
        Jogador jogador2 = partida.getJogador2();

        jogador1.adicionarPartidaJogada(partida);
        jogador2.adicionarPartidaJogada(partida);

        jogador1.setJogando(false);
        jogador2.setJogando(false);
    }

    public Jogador escolherAdversario(Jogador solicitante) {
        int numeroAleatorio = this.random.nextInt(this.jogadorByUsername.size());
        int cont = 0;

        for (Jogador adversario : this.jogadorByUsername.values()) {
            if (cont >= numeroAleatorio)
                if (adversario.isOnline() &&
                    !adversario.isJogando() &&
                    !adversario.equals(solicitante)) {
                    return adversario;
                }

            cont++;
        }

        return null;
    }
}