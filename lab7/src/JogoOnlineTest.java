import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JogoOnlineTest {
    private JogoOnline jogo;

    @Before
    public void setUp() { jogo = new JogoOnline(); }

//    @Test
//    public void testarLoginComUsuarioDesconhecido() {
//        try {
//            jogo.fazerLogin("UsuarioQualquerDesconhecido", "1234");
//            fail("Uma UsuarioInexistenteException deve ser lançada se o username for desconhecido");
//
//        } catch (UsuarioInexistenteException e) {
//            // tudo bem, aconteceu o que eu esperava!!!
//        }
//    }

//    // equivalentemente...
//
//    @Test(expected = UsuarioInexistenteException.class)
//    public void testarLoginComUsuarioDesconhecido() {
//        JogoOnline jogo = new JogoOnline();
//        jogo.fazerLogin("UsuarioQualquerDesconhecido", "1234");
//    }

    @Test
    public void testarCadastroELogin() throws SenhaInvalidaException, UsuarioInexistenteException {
        Jogador jogadorJulia = jogo.cadastrarJogador("julia", "2604");
        assertNotNull(jogadorJulia);
        assertEquals("julia", jogadorJulia.getUsername());
        jogo.fazerLogin("julia", "2604");

        assertTrue("Após o login (e antes do logout), o jogador " +
                "deve ser considerado online",
                jogadorJulia.isOnline());
    }

    @Test
    public void testarCadastroELoginComSenhaIncorreta() {
        Jogador jogadorJulia = jogo.cadastrarJogador("julia", "2604");

        assertNotNull(jogadorJulia);
        assertEquals("julia", jogadorJulia.getUsername());

//        try {
//            jogo.fazerLogin("Julia", "3246523");
//            fail("O fazerLogin deveria lançar uma SenhaInvalidaException!!!");
//
//        } catch (SenhaInvalidaException e) {
//            // ok, era esperado que ela acontecesse!!!!
//        }

        assertFalse(jogadorJulia.isOnline());
    }

    @Test
    public void testarIniciarPartida() throws SenhaInvalidaException, UsuarioInexistenteException {
        Jogador jogadorJulia = jogo.cadastrarJogador("julia", "2604");
        Jogador jogadoraKayan = jogo.cadastrarJogador("kayan", "0507");

        jogo.fazerLogin("julia", "2604");
        jogo.fazerLogin("kayan", "0507");

        // sanity check
        assertFalse(jogadorJulia.isJogando());
        assertFalse(jogadoraKayan.isJogando());

        Partida partida = jogo.iniciarPartida(jogadorJulia, jogadoraKayan);

        assertEquals(jogadorJulia, partida.getJogador1());
        assertEquals(jogadoraKayan, partida.getJogador2());

        assertEquals(Partida.PARTIDA_EM_ANDAMENTO, partida.getResultado());
        assertTrue(jogadorJulia.isJogando());
        assertTrue(jogadoraKayan.isJogando());
    }

    @Test
    public void testarLogout() throws SenhaInvalidaException, UsuarioInexistenteException {
        Jogador jogadoraKayan = jogo.cadastrarJogador("kayan", "0507");

        jogo.fazerLogin("kayan", "0507");
        assertTrue(jogadoraKayan.isOnline());

        jogo.fazerLogout(jogadoraKayan);
        assertFalse(jogadoraKayan.isOnline());
    }

    @Test(expected = RuntimeException.class)
    public void testarLogoutDeJogadorNaoOnline() {
        Jogador jogadoraKayan = jogo.cadastrarJogador("kayan", "0507");

        jogo.fazerLogout(jogadoraKayan);
    }

    @Test (expected = SenhaInvalidaException.class)
    public void testarLoginSenhaInvalida() throws SenhaInvalidaException, UsuarioInexistenteException{
        Jogador jogadoraKayan = jogo.cadastrarJogador("kayan", "0507");

        jogo.fazerLogin("kayan", "0705");
        assertFalse(jogadoraKayan.isOnline());
    }

    @Test (expected = UsuarioInexistenteException.class)
    public void testarLoginUsuarioInexistente() throws SenhaInvalidaException, UsuarioInexistenteException{
        Jogador jogadoraKayan = jogo.cadastrarJogador("kayan", "3456");

        jogo.fazerLogin("bruen", "0507");
        assertFalse(jogadoraKayan.isOnline());
    }
}