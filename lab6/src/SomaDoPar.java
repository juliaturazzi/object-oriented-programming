import java.util.ArrayList;
import java.util.HashSet;

public class SomaDoPar {
    public static Integer encontrarParComSomaDadaQuadratica(ArrayList<Integer> numeros, int somaDesejada) {

        // algoritmo ineficiente(quadrático):

        for (int i = 0; i < numeros.size(); i++)
            for (int j = i + 1; j < numeros.size(); j++) {
                int x = numeros.get(i);
                int y = numeros.get(j);

                if (x + y == somaDesejada)  return Math.min(x, y);
            }

        return null;
    }

        // algoritmo eficiente (linear):
        public static Integer encontrarParComSomaDadaLinear(ArrayList<Integer> numeros, int somaDesejada){
            int numero = 0;
            HashSet<Integer> novoSet = new HashSet();

            for (Integer i : numeros)  novoSet.add(i);

            for (Integer i : novoSet){
                numero = somaDesejada - i;

                if (novoSet.contains(numero))  return Math.min(i,numero);
            }

            return null;
        }
    }