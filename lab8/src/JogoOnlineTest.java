import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JogoOnlineTest {
    private JogoOnline jogo;

    @Before
    public void setUp() {
        jogo = new JogoOnline();
    }

    @Test
    public void testarLoginComUsuarioDesconhecido() throws SenhaInvalidaException {
        try {
            jogo.fazerLogin("UsuarioQualquerDesconhecido", "0507");
            fail("Uma UsuarioInexistenteException deve ser lançada se o username for desconhecido");

        } catch (UsuarioInexistenteException e) {
            // tudo bem, aconteceu o que eu esperava!!!
        }
    }

    @Test(expected = UsuarioInexistenteException.class)
    public void testarLoginComUsuarioDesconhecidoOutraManeira() throws UsuarioInexistenteException, SenhaInvalidaException {
        JogoOnline jogo = new JogoOnline();
        jogo.fazerLogin("UsuarioQualquerDesconhecido", "0507");
    }

    @Test
    public void testarCadastroELogin() throws UsuarioInexistenteException, SenhaInvalidaException {
        Jogador jogadorKayan = jogo.cadastrarJogador("Kayan", "0507");
        assertNotNull(jogadorKayan);
        assertEquals("Kayan", jogadorKayan.getUsername());

        jogo.fazerLogin("Kayan", "0507");

        assertTrue("Após o login (e antes do logout), o jogador " +
                "deve ser considerado online",
                jogadorKayan.isOnline());
    }

    @Test
    public void testarCadastroELoginComSenhaIncorreta() throws UsuarioInexistenteException {
        Jogador jogadorKayan = jogo.cadastrarJogador("Kayan", "0507");
        assertNotNull(jogadorKayan);
        assertEquals("Kayan", jogadorKayan.getUsername());

        try {
            jogo.fazerLogin("Kayan", "0830129");
            fail("O fazerLogin deveria lançar uma SenhaInvalidaException!!!");

        } catch (SenhaInvalidaException e) {
            // ok, era esperado que ela acontecesse!!!!
        }

        assertFalse(jogadorKayan.isOnline());
    }

    @Test
    public void testarIniciarPartida() throws UsuarioInexistenteException, SenhaInvalidaException {
        Jogador jogadorKayan = jogo.cadastrarJogador("Kayan", "0507");
        Jogador jogadoraFatima = jogo.cadastrarJogador("Fatima", "1309");

        jogo.fazerLogin("Kayan", "0507");
        jogo.fazerLogin("Fatima", "1309");

        assertFalse(jogadorKayan.isJogando());
        assertFalse(jogadoraFatima.isJogando());

        Partida partida = jogo.iniciarPartida(jogadorKayan, jogadoraFatima);

        assertEquals(jogadorKayan, partida.getJogador1());
        assertEquals(jogadoraFatima, partida.getJogador2());
        assertEquals(Partida.PARTIDA_EM_ANDAMENTO,
                partida.getResultado());
        assertTrue(jogadorKayan.isJogando());
        assertTrue(jogadoraFatima.isJogando());
    }

    @Test
    public void testarLogout() throws UsuarioInexistenteException, SenhaInvalidaException {
        Jogador jogadoraFatima = jogo.cadastrarJogador("Fatima", "1309");

        jogo.fazerLogin("Fatima", "1309");

        assertTrue(jogadoraFatima.isOnline());

        jogo.fazerLogout(jogadoraFatima);

        assertFalse(jogadoraFatima.isOnline());
    }

    @Test(expected = RuntimeException.class)
    public void testarLogoutDeJogadorNaoOnline() {
        Jogador jogadoraFatima = jogo.cadastrarJogador("Fatima", "1309");

        jogo.fazerLogout(jogadoraFatima);
    }

    @Test
    public void testarObterRanking() throws SenhaInvalidaException, UsuarioInexistenteException {
        Jogador jogadorHugo = jogo.cadastrarJogador("Hugo", "0507");
        Jogador jogadoraFatima = jogo.cadastrarJogador("Fatima", "1309");

        jogo.fazerLogin("Hugo", "0507");
        jogo.fazerLogin("Fatima", "1309");

        Partida partida = jogo.iniciarPartida(jogadorHugo, jogadoraFatima);
        jogo.encerrarPartida(partida,1);

        Partida partida2 = jogo.iniciarPartida(jogadorHugo, jogadoraFatima);
        jogo.encerrarPartida(partida2,1);

        Partida partida3 = jogo.iniciarPartida(jogadorHugo, jogadoraFatima);
        jogo.encerrarPartida(partida3,1);

        assertEquals("Hugo",jogo.obterRanking().get(0).getUsername());

    }

    @Test
    public void testarObterJogadoresEmOrdemAlfabetica() throws SenhaInvalidaException, UsuarioInexistenteException {
        Jogador jogadorBruen = jogo.cadastrarJogador("Bruen", "2412");
        Jogador jogadoraJulia = jogo.cadastrarJogador("Julia", "1309");

        jogo.fazerLogin("Bruen", "2412");
        jogo.fazerLogin("Julia", "1309");

        assertEquals("Bruen",jogo.obterJogadoresEmOrdemAlfabetica().get(0).getUsername());
    }
}