package ar.edu.unlp.info.oo1.ejercicio11_ElInversor;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InversorTest {
	private Inversor i;
	double res;

	@BeforeEach
	void setUp() throws Exception {
		InversionEnAcciones iea = new InversionEnAcciones("inversion 1",10,2.5);
		PlazoFijo pf = new PlazoFijo((LocalDate.of(2024, 11, 01)),10,2.5);
		List<Inversion> inversiones = new LinkedList<Inversion>();
		inversiones.add(iea);
		inversiones.add(pf);
		this.i = new Inversor("inversor",inversiones);
		this.res = (10+((int) ChronoUnit.DAYS.between(LocalDate.of(2024,11,01), LocalDate.now()))*2.5) + 10*2.5;
	}

	@Test
	void testValorActualConInversiones() {
		assertEquals(this.i.valorActual(),this.res);
	}
	
	@Test
	void testValorActualSinInversiones() {
		this.i = new Inversor("inveror2", new LinkedList());
		assertEquals(this.i.valorActual(),0);
	}

}
