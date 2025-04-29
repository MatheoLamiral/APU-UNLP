package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MixtaTest {
	private Mixta mixta;
	private Mixta mixta2;
	private Mixta mixta3;
	private Mixta mixta4;
	private Mixta mixta5;
	
	@BeforeEach
	void setUp() throws Exception {
		List<Topografia> topografias1 = new LinkedList<Topografia>();
		topografias1.add(new Agua());
		topografias1.add(new Tierra());
		topografias1.add(new Tierra());
		topografias1.add(new Agua());
		this.mixta = new Mixta(topografias1);
		List<Topografia> topografias2 = new LinkedList<Topografia>();
		topografias2.add(new Tierra());
		topografias2.add(new Agua());
		topografias2.add(new Agua());
		topografias2.add(new Tierra());
		this.mixta2 = new Mixta(topografias2);
		List<Topografia> topografias3 = new LinkedList<Topografia>();
		topografias3.add(new Agua());
		topografias3.add(new Tierra());
		topografias3.add(new Tierra());
		topografias3.add(new Agua());
		this.mixta3 = new Mixta(topografias3);
		List<Topografia> topografias4 = new LinkedList<Topografia>();
		topografias4.add(new Agua());
		topografias4.add(new Tierra());
		topografias4.add(new Tierra());
		topografias4.add(this.mixta3);
		this.mixta4 = new Mixta(topografias4);
		List<Topografia> topografias5 = new LinkedList<Topografia>();
		topografias5.add(new Agua());
		topografias5.add(new Tierra());
		topografias5.add(new Tierra());
		topografias5.add(this.mixta3);
		this.mixta5 = new Mixta(topografias5);
	}

	@Test
	void testEsIgualMixta() {
		assertFalse(this.mixta.esIgual(this.mixta2));
		assertTrue(this.mixta.esIgual(this.mixta3));
		assertTrue(this.mixta4.esIgual(this.mixta5));
	}
	
	@Test
	void testCalcularProporcion() {
		//(1+0+0+1)/4 -> 2/4 -> 1/2
		assertEquals(0.5,this.mixta.calcularProporcion());
	}

}
