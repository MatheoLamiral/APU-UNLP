package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TierraTest {
	private Tierra tierra1;
	private Tierra tierra2;
	private Agua agua;
	private Pantano pantano;
	private Mixta mixta;

	@BeforeEach
	void setUp() throws Exception {
		this.tierra1 = new Tierra();
		this.tierra2 = new Tierra();
		this.agua = new Agua();
		this.pantano = new Pantano();
		this.mixta = new Mixta(List.of(agua, tierra1, pantano, tierra2));
	}

	@Test
	void testEsIgual() {
		assertTrue(this.tierra1.esIgual(this.tierra2));
		assertFalse(this.tierra1.esIgual(this.agua));
		assertFalse(this.tierra1.esIgual(this.pantano));
		assertFalse(this.tierra1.esIgual(this.mixta));
	}

}
