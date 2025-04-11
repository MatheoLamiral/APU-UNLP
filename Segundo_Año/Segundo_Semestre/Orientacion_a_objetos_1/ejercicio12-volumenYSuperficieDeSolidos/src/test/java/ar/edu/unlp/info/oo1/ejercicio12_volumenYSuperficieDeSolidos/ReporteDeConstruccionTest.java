package ar.edu.unlp.info.oo1.ejercicio12_volumenYSuperficieDeSolidos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReporteDeConstruccionTest {
	private ReporteDeConstruccion rc;

	@BeforeEach
	void setUp() throws Exception {
		List<Pieza> list = new LinkedList<Pieza>();
		list.add(new PrismaRectangular("madera","negra",10,5,2));
		list.add(new Esfera("metal","gris",2));
		list.add(new Cilindro("madera","negra",10,2));
		this.rc = new ReporteDeConstruccion(list);
	}

	@Test
	void testVolumenDeMaterialExistente() {
		assertEquals(728.318,this.rc.volumenDeMaterial("madera"),0.01);
	}
	
	@Test
	void testVolumenDeMaterialInxistente() {
		assertEquals(0,this.rc.volumenDeMaterial("hierro"));
	}
	
	@Test
	void testSuperficieDeColorExistente() {
		assertEquals(913.982,this.rc.superficieDeColor("negra"),0.01);
	}
	
	@Test
	void testSuperficieDeColorInexistente() {
		assertEquals(0,this.rc.superficieDeColor("violeta"));
	}

}
