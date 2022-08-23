import java.util.Random;

public class Dado implements Sorteador {
    Random random = new Random();

    @Override
    public int sortear() { return random.nextInt(7); }
}