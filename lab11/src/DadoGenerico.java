import java.util.Map;
import java.util.Random;

public class DadoGenerico<T> implements Sorteador<Integer> {
    Map<T, Integer> frequenciaByResultado;

    public DadoGenerico(Map<T, Integer> frequenciaByResultado) { this.frequenciaByResultado = frequenciaByResultado; }

    @Override
    public Integer sortear() {
        int frequenciaParcial = 0, frequenciaTotal = 0;

        for (Integer i:frequenciaByResultado.values())  frequenciaTotal += i;

        Random rand = new Random();
        int x = rand.nextInt(frequenciaTotal+1);

        for (int i = 1; i < frequenciaByResultado.size()+1; i++){
            frequenciaParcial += frequenciaByResultado.get(i);

            if (x <= frequenciaParcial)  return i;
        }

        return null;
    }
}