import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

public class ContaCorrenteTeste {
    private float FLOAT_DELTA = 0.00001f;
    private ContaCorrente contaDoKayan;
    private Correntista Kayan;
    private float saldoInicial;
    private Correntista Julia;
    private ContaCorrente contaDaJulia;

    @Before
    public void setUp() {
        Kayan = new Correntista("Kayan", 222);
        contaDoKayan = new ContaCorrente(1, "Kayan");
        saldoInicial = contaDoKayan.getSaldoEmReais();
        Julia = new Correntista("Julia",32093220);
        contaDaJulia = new ContaCorrente(2,"Julia");
    }

    @Test
    public void testarSaldoInicialDaConta() {
        assertEquals("Toda conta, ao ser criada, deve começar com saldo de R$10,00.", 10.0, saldoInicial, FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresValidos() {
        contaDoKayan.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito", saldoInicial + 50, contaDoKayan.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresNegativos() {
        contaDoKayan.receberDepositoEmDinheiro(-200);
        assertEquals("Depósitos de valores negativos devem ser solenemente ignorados", saldoInicial, contaDoKayan.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValorZero() {
        String extratoAntes = contaDoKayan.getExtrato();
        contaDoKayan.receberDepositoEmDinheiro(0);
        assertEquals("Depósitos de valor zero devem ser ignorados", saldoInicial, contaDoKayan.getSaldoEmReais(), FLOAT_DELTA);

        String extratoDepois = contaDoKayan.getExtrato();
        assertEquals("Depósitos ignorados não devem sequer constar do extrato", extratoAntes, extratoDepois);
    }

    @Test
    public void testarSaqueComFundos() {
        contaDoKayan.sacar(2);
        assertEquals("O valor sacado deve ser descontado do saldo da conta", saldoInicial - 2, contaDoKayan.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarSaqueSemFundos() {
        contaDoKayan.sacar(100000);
        assertEquals("Saques de valores maiores que o saldo não devem ser permitidos", saldoInicial, contaDoKayan.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferencia() {
        contaDoKayan.efetuarTransferecia(contaDaJulia, 3);

        assertEquals("Impossível realizar uma transferência de valor maior do que o saldo da conta.", saldoInicial + 3, contaDaJulia.getSaldoEmReais(), FLOAT_DELTA);
        assertEquals("O valor não foi transferido da conta.", saldoInicial - 3, contaDoKayan.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferenciaSemFundos() {
        Correntista Julia = new Correntista("Julia", 22222);
        ContaCorrente contaDaJulia = new ContaCorrente(2, "Julia");
        contaDoKayan.efetuarTransferecia(contaDaJulia, 100000);

        assertEquals("Não realizar transferência de valor maior do que o saldo.", saldoInicial, contaDaJulia.getSaldoEmReais(), FLOAT_DELTA);
        assertEquals("Não realizar transferência de valor abaixo ou igual a 0.", saldoInicial, contaDoKayan.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarGetCpfDoCorrentista() {
        assertEquals("teste",222,Kayan.getCpf(),FLOAT_DELTA);
    }

    @Test
    public void getQuantidadeDeTransacoesDeTodasAsContas() {
        contaDaJulia.receberDepositoEmDinheiro(10);
        contaDaJulia.receberDepositoEmDinheiro(29);
        contaDoKayan.receberDepositoEmDinheiro(2222);
        contaDaJulia.sacar(22);
        contaDoKayan.sacar(292);
        
        assertEquals("test",5,ContaCorrente.quantidadeDeTransacoesDeTodasAsContas,FLOAT_DELTA);
    }
}