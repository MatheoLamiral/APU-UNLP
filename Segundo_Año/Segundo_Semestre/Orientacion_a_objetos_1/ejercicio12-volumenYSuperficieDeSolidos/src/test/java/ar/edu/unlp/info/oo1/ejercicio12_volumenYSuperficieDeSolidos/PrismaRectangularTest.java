package ar.edu.unlp.info.oo1.ejercicio12_volumenYSuperficieDeSolidos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrismaRectangularTest {
	private PrismaRectangular pr;

	@BeforeEach
	void setUp() throws Exception {
		this.pr = new PrismaRectangular("litio","gris",10,5,2);
	}

	@Test
	void testCalcularVolumen() {
		assertEquals(100,this.pr.calcularVolumen());
	}
	
	@Test
	void testCalcularSuperficie() {
		assertEquals(160,this.pr.calcularSuperficie());
	}

}
