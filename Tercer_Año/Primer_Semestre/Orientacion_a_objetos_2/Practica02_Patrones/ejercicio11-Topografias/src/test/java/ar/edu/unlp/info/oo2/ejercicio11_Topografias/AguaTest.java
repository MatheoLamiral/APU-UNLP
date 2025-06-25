package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AguaTest {
	private Agua agua1;
	private Agua agua2;
	private Tierra tierra;
	private Pantano pantano;
	private Mixta mixta;

	@BeforeEach
	void setUp() throws Exception {
		this.agua1 = new Agua();
		this.agua2 = new Agua();
		this.tierra = new Tierra();
		this.pantano = new Pantano();
		this.mixta = new Mixta(List.of(agua1, tierra, pantano, agua2));
	}

	@Test
	void testEsIgual() {
		assertTrue(this.agua1.esIgual(this.agua2));
		assertFalse(this.agua1.esIgual(this.tierra));
		assertFalse(this.agua1.esIgual(this.pantano));
		assertFalse(this.agua1.esIgual(this.mixta));
	}

}
