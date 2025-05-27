package ar.edu.unlp.info.oo2.ejercicio07_Calculadora;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculadoraTest {
	private Calculadora calc;
	private State state;

	@BeforeEach
	void setUp() throws Exception {
		this.calc = new Calculadora();
	}
	
	@Test
	void testMas() {
		this.calc.setValor(10);
		this.calc.mas();
		this.calc.setValor(10);
		assertEquals("20.0",this.calc.getResultado());
		this.calc.mas();
		this.calc.setValor(10);
		assertEquals("30.0",this.calc.getResultado());
		this.calc.mas();
		this.calc.mas();
		assertEquals("Error",this.calc.getResultado());
	}
	
	@Test
	void testMenos() {
		this.calc.setValor(10);
		this.calc.menos();
		this.calc.setValor(10);
		assertEquals("0.0",this.calc.getResultado());
		this.calc.menos();
		this.calc.setValor(10);
		assertEquals("-10.0",this.calc.getResultado());
		this.calc.menos();
		this.calc.menos();
		assertEquals("Error",this.calc.getResultado());
	}
	
	@Test
	void testMult() {
		this.calc.setValor(10);
		this.calc.mult();
		this.calc.setValor(10);
		assertEquals("100.0",this.calc.getResultado());
		this.calc.mult();
		this.calc.setValor(-1);
		assertEquals("-100.0",this.calc.getResultado());
		this.calc.mult();
		this.calc.mult();
		assertEquals("Error",this.calc.getResultado());
	}
	
	@Test
	void testDiv() {
		this.calc.setValor(10);
		this.calc.div();
		assertThrows(RuntimeException.class, () -> this.calc.setValor(0));
		this.calc.borrar();
		this.calc.setValor(10);
		this.calc.div();
		this.calc.setValor(-2);
		assertEquals("-5.0",this.calc.getResultado());
		this.calc.div();
		this.calc.div();
		assertEquals("Error",this.calc.getResultado());
	}
	
	@Test
	void testGetResultado() {
		this.calc.setValor(10);
		assertEquals("10.0",this.calc.getResultado());
		this.calc.mas();
		this.calc.mas();
		assertEquals("Error",this.calc.getResultado());
	}
	
	@Test
	void testBorrar() {
		this.calc.setValor(10);
		this.calc.mas();
		this.calc.setValor(30);
		this.calc.borrar();
		assertEquals("0.0",this.calc.getResultado());
	}


}
