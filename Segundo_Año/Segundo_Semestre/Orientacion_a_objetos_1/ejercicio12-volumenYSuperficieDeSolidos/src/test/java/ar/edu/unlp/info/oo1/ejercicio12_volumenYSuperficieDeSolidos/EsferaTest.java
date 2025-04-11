package ar.edu.unlp.info.oo1.ejercicio12_volumenYSuperficieDeSolidos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EsferaTest {
	private Esfera e;
	
	@BeforeEach
	void setUp() throws Exception {
		this.e = new Esfera ("hierro","gris",2);
	}

	@Test
	void testCalcularVolumen() {
		assertEquals(33.510,this.e.calcularVolumen(), 0.01);
	}
	
	@Test
	void testCalcularSuperficie() {
		assertEquals(50.265,this.e.calcularSuperficie(), 0.01);
	}

}
