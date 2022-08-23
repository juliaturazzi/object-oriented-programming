public class DadosDeGamao implements Sorteador {
    Dado primeiroDado = new Dado();
    Dado segundoDado = new Dado();

    int numero1 = 0;
    int numero2 = 0;

    @Override
    public int sortear() {
        numero1 = primeiroDado.sortear();
        numero2 = segundoDado.sortear();

        if(!(numero1 == numero2))  return numero1 + numero2;

        return (numero1 + numero2) * 2;
    }
}