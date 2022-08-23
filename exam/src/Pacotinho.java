import java.util.ArrayList;

public class Pacotinho<T extends Colecionavel> extends ArrayList<T> {
    int tamanhoPacote;

    public Pacotinho(int tamanho) {
        super();
        this.tamanhoPacote = tamanho;
    }

    @Override
    public boolean add(T elemento) {
        if (size() >= tamanhoPacote)  throw new IllegalStateException();

        if (size() < tamanhoPacote)  return super.add(elemento);

        return false;
    }
}