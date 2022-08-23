public class SorteadorViaTrio implements Sorteador<Integer>{
    Sorteador dadoEscolhido;

    public SorteadorViaTrio(Sorteador dado) { this.dadoEscolhido = dado; }

    public Integer sortear() {
        int A = (int) dadoEscolhido.sortear();
        int B = (int) dadoEscolhido.sortear();
        int C = (int) dadoEscolhido.sortear();

        if(A==B && A==C)  return 1;

        return 0;
    }
}