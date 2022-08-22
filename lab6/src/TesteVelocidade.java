import java.util.ArrayList;
import java.util.Random;

public class TesteVelocidade {

    public static void main(String[] args) {
        ArrayList<Integer> listaNumeros = new ArrayList<>();

        for (int i = 0; i < 500000; i++) {
            Random random = new Random();
            listaNumeros.add(random.nextInt());
        }

        long iniciarContagemQuadratica = System.currentTimeMillis();
        SomaDoPar.encontrarParComSomaDadaQuadratica(listaNumeros, 5);

        long tempoQuadratica = System.currentTimeMillis() - iniciarContagemQuadratica;
        System.out.printf("\nTempo Soma (Quadrático) = %.4f seconds", tempoQuadratica / 1000.0);

        long iniciarContagemLinear = System.currentTimeMillis();
        SomaDoPar.encontrarParComSomaDadaLinear(listaNumeros, 5);

        long tempoLinear = System.currentTimeMillis() - iniciarContagemLinear;
        System.out.printf("\nTempo Soma (Linear) = %.4f seconds", tempoLinear / 1000.0);


        String sequenciaCaracteres = "awaaoaawaddawijdaawpdjwaaaaaaaaaaaaaaaaaaaaadpowjadapawojdaawpdojwaadpawojdwapdjio";
        String texto = "";

        for (int i = 0; i < 1000; i++)  texto += sequenciaCaracteres;


        long iniciarQuadratica = System.currentTimeMillis();
        CaracterMaisFrequente.encontrarCaracterMaisFrequenteQuadratica(texto);

        long tempoCaractereQuadratica = System.currentTimeMillis() - iniciarQuadratica;
        System.out.printf("\nTempo Caractere (Quadrático)= %.4f seconds", tempoCaractereQuadratica / 1000.0);

        long iniciarLinear = System.currentTimeMillis();
        CaracterMaisFrequente.encontrarCaracterMaisFrequenteLinear(texto);

        long tempoCaractereLinear = System.currentTimeMillis() - iniciarLinear;
        System.out.printf("\nTempo Caractere (Linear) = %.4f seconds\n", tempoCaractereLinear / 1000.0);
    }
}