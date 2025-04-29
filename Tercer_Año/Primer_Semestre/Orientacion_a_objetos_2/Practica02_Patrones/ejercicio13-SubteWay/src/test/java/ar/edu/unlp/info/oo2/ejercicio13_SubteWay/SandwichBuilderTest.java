package ar.edu.unlp.info.oo2.ejercicio13_SubteWay;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SandwichBuilderTest {
	private Director director;
	private Sandwich product;
	private ClasicoBuilder clasico;
	private VegetarianoBuilder vegetariano;
	private VeganoBuilder vegano;
	private SinTaccBuilder sinTacc;

	@BeforeEach
	void setUp() throws Exception {
		this.clasico = new ClasicoBuilder();
		this.vegetariano = new VegetarianoBuilder();
		this.vegano = new VeganoBuilder();
		this.sinTacc = new SinTaccBuilder();
	}

	@Test
	void testClasico() {
		this.director = new Director(this.clasico);
		this.director.crearSandwich();
		assertEquals(500,this.clasico.getPrecio());
	}
	
	@Test
	void testVegetariano() {
		this.director = new Director(this.vegetariano);
		this.director.crearSandwich();
		assertEquals(420,this.vegetariano.getPrecio());
	}
	
	@Test
	void testVegano() {
		this.director = new Director(this.vegano);
		this.director.crearSandwich();
		assertEquals(620,this.vegano.getPrecio());
	}
	
	@Test
	void testSinTacc() {
		this.director = new Director(this.sinTacc);
		this.director.crearSandwich();
		assertEquals(618,this.sinTacc.getPrecio());
	}
	

}
