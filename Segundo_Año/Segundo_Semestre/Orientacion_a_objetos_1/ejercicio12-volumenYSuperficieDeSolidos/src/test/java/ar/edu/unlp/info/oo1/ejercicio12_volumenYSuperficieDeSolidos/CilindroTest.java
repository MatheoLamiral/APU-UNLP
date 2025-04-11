package ar.edu.unlp.info.oo1.ejercicio12_volumenYSuperficieDeSolidos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CilindroTest {
	private Cilindro c;

	@BeforeEach
	void setUp() throws Exception {
		this.c = new Cilindro("madera", "negra", 10, 2);
	}

	@Test
	void testCalcularVolumen() {
		assertEquals(628.318,this.c.calcularVolumen(), 0.01);
	}
	
	@Test
	void testCalcularSuperficie() {
		assertEquals(753.982,this.c.calcularSuperficie(),0.01);		
	}

}
