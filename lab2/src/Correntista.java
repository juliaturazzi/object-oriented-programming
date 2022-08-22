public class Correntista{
    private final long cpf;
    private String nome;

    public Correntista(String nome,long cpf){
        this.nome = nome;
        this.cpf  = cpf;
    }

    public String getNome() { return this.nome; }
    
    public void setNome(String nome) { this.nome = nome; }

    public long getCpf() { return this.cpf; }
}