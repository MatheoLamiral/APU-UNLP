package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class PantanoTest {
    private Pantano pantano1;
    private Pantano pantano2;
    private Agua agua;
    private Tierra tierra;
    private Mixta mixta;

    @BeforeEach
    void setUp() {
        this.pantano1 = new Pantano();
        this.pantano2 = new Pantano();
        this.agua = new Agua();
        this.tierra = new Tierra();
        this.mixta = new Mixta(List.of(agua, tierra, pantano1, pantano2));
    }

    @Test
    void testEsIgual() {
        assertTrue(this.pantano1.esIgual(this.pantano2));
        assertFalse(this.pantano1.esIgual(this.agua));
        assertFalse(this.pantano1.esIgual(this.tierra));
        assertFalse(this.pantano1.esIgual(this.mixta));
    }
}