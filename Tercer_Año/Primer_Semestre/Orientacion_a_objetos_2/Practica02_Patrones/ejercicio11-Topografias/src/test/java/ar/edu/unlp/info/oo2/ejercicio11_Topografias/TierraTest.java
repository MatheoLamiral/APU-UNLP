package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TierraTest {
	private Tierra tierra1;
	private Tierra tierra2;
	private Agua agua;

	@BeforeEach
	void setUp() throws Exception {
		this.tierra1 = new Tierra();
		this.tierra2 = new Tierra();
		this.agua = new Agua();
	}

	@Test
	void testEsIgual() {
		assertTrue(this.tierra1.esIgual(this.tierra2));
		assertFalse(this.tierra1.esIgual(this.agua));
		assertFalse(this.tierra2.esIgual(this.agua));
	}

}
