import org.junit.Test;

import static org.junit.Assert.*;

public class CaracterMaisFrequenteTest {

    @Test
    public void testarCaracterMaisFrequente() {
        assertEquals('o', CaracterMaisFrequente.encontrarCaracterMaisFrequenteQuadratica("bolso"));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequenteLinear("b o l s o jjj 894"));
    }
}