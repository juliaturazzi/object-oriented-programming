import java.util.HashMap;

public class CaracterMaisFrequente {

    public static char encontrarCaracterMaisFrequenteQuadratica(String texto) {
        // algoritmo ineficiente (quadrático):
        int ocorrenciasDoMaisFrequente = 1;
        char maisFrequente = texto.charAt(0);

        for (int i = 0; i < texto.length(); i++) {
            int contOcorrencias = 1;
            char caracterDaVez = texto.charAt(i);

            for (int j = i+1; j < texto.length(); j++)
                if (texto.charAt(j) == caracterDaVez)  contOcorrencias++;

            if (contOcorrencias > ocorrenciasDoMaisFrequente) {
                maisFrequente = caracterDaVez;
                ocorrenciasDoMaisFrequente = contOcorrencias;
            }
        }

        return maisFrequente;
    }

    // algoritmo eficiente (linear):
    public static char encontrarCaracterMaisFrequenteLinear(String texto) {
        HashMap<Character, Integer> Mapa = new HashMap<>();

        for (int i = 0; i < texto.length(); i++) {
            if (Mapa.containsKey(texto.charAt(i))) {
                int j = Mapa.get(texto.charAt(i));
                Mapa.put(texto.charAt(i), j + 1);
            }

            Mapa.put(texto.charAt(i), 1);
        }

        int cont = 0;
        char charMaisFrequente = 0;

        for(Character i : Mapa.keySet())
            if(Mapa.get(i) > cont) {
                charMaisFrequente = i;
                cont = Mapa.get(i);
            }

        return charMaisFrequente;
    }
}