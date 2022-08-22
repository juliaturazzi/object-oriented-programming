import java.util.ArrayList;

public class ContaCorrente {
    private final int numero;
    private Correntista correntista;
    private float saldoEmReais = 0;
    private ArrayList<String> transacoes;
    public static final float SALDO_INICIAL_DA_CONTA = 10;  
    public static int quantidadeDeTransacoesDeTodasAsContas = 0;

    
    public ContaCorrente(int numeroDaConta, String correntista){
        this.numero = numeroDaConta;
        this.saldoEmReais = SALDO_INICIAL_DA_CONTA;
        this.transacoes = new ArrayList<>();
        this.transacoes.add("Conta criada com saldo de " + this.saldoEmReais);
    }

    public Correntista getCorrentista() { return correntista; }

    public void setCorrentista(Correntista correntista) { this.correntista = correntista; }

    public int getNumero() { return numero; }

    public float getSaldoEmReais() { return this.saldoEmReais; }

    public void receberDepositoEmDinheiro(float valor) {
        if(valor <= 0) return;  
        this.saldoEmReais += valor;

        String registroTransacao = "Recebido depósito em dinheiro no valor de " + valor;

        this.transacoes.add(registroTransacao);
        quantidadeDeTransacoesDeTodasAsContas++;
    }

    public String getExtrato() {
        String resultado = "";
        
        for (int i = 0; i < transacoes.size(); i++) resultado += transacoes.get(i) + "\n";
        return resultado;
    }

    public int getQuantidadeDeTransacoesDeTodasAsContas() { return quantidadeDeTransacoesDeTodasAsContas; }

    public void sacar(float valor) {
        if(valor <= 0 || valor> saldoEmReais) return;
        this.saldoEmReais -= valor;

        quantidadeDeTransacoesDeTodasAsContas++;
    }

    public void efetuarTransferecia(ContaCorrente contaDestino, float valor) {
        if(valor > saldoEmReais || valor <=0) return;
        
        contaDestino.saldoEmReais += valor;
        this.saldoEmReais -= valor;
        quantidadeDeTransacoesDeTodasAsContas++;
    }
}