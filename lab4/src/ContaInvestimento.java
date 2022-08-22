public class ContaInvestimento extends Conta{
    private String tipoInvestimento;
    private float taxaDeJuros;
    public ContaInvestimento (int numeroDaConta, Correntista correntista, String tipoInvestimento, float taxaDeJuros) {
        super(numeroDaConta, correntista);
        this.tipoInvestimento = tipoInvestimento;
        this.taxaDeJuros = taxaDeJuros;
        for (Conta conta: Conta.contasExistentes){
            if(conta.getCorrentista() == correntista && conta != this) {
                return;
            }
        }
        throw new RuntimeException("Correntista sem conta corrente!");
    }

    public void aplicarJuros() { // atualiza o saldo
        float valorInicial = getSaldoEmReais();
        float valorFinal = valorInicial * taxaDeJuros;
        this.receberDepositoEmDinheiro(valorFinal);
        this.sacar(valorInicial);
        return;
    }

    public void resgatarTotal (Conta account) {
        if(this.getSaldoEmReais() < 0){
            return;
        }

        if(account.getCorrentista() == this.getCorrentista()) {
            this.efetuarTransferecia(account, this.getSaldoEmReais());
        }
    }
}
