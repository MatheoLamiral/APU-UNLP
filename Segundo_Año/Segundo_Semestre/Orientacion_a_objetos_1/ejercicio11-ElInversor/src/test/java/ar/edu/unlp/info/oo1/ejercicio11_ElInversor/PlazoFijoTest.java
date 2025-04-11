package ar.edu.unlp.info.oo1.ejercicio11_ElInversor;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlazoFijoTest {
	private PlazoFijo pf;

	@BeforeEach
	void setUp() throws Exception {
		this.pf = new PlazoFijo((LocalDate.of(2024, 11, 01)),10,2.5);
	}

	@Test
	void testValorActual() {
		LocalDate day = LocalDate.of(2024,11,01);
		LocalDate dayAct = LocalDate.now();
		int dif = (int) ChronoUnit.DAYS.between(day, dayAct);
		assertEquals(this.pf.valorActual(),(10 + (dif * 2.5)));
		assertNotEquals(this.pf.valorActual(),(10 + (dif * 2.5)));
	}

}
