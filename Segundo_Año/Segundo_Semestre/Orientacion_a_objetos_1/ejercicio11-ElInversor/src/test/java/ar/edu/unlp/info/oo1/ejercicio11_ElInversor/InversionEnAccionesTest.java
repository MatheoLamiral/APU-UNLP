package ar.edu.unlp.info.oo1.ejercicio11_ElInversor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InversionEnAccionesTest {
	private InversionEnAcciones iea;

	@BeforeEach
	void setUp() throws Exception {
		this.iea = new InversionEnAcciones("inversionEnAccion 1", 10, 2.5); 
	}

	@Test
	void testValorActual() {
		assertEquals(this.iea.valorActual(), (10*2.5));
		assertNotEquals(this.iea.valorActual(), (10*2.49));
	}

}
