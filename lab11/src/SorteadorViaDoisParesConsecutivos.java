public class SorteadorViaDoisParesConsecutivos implements Sorteador<Integer> {
    Sorteador dadoEscolhido;

    public SorteadorViaDoisParesConsecutivos(Sorteador dado) { this.dadoEscolhido = dado; }

    public Integer sortear() {
        int A = (int) dadoEscolhido.sortear();
        int B = (int) dadoEscolhido.sortear();
        int C = (int) dadoEscolhido.sortear();
        int D = (int) dadoEscolhido.sortear();

        if(A==B && C==D)  return 1;

        return 0;
    }
}