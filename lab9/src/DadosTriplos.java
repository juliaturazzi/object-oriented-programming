public class DadosTriplos implements Sorteador{
    Dado unicoDado = new Dado();
    int unicoDado1 = 0, unicoDado2 = 0, unicoDado3 = 0;

    @Override
    public int sortear() {
        unicoDado1=unicoDado.sortear();
        unicoDado2=unicoDado.sortear();
        unicoDado3=unicoDado.sortear();

        return unicoDado1 + unicoDado2 + unicoDado3;
    }
}