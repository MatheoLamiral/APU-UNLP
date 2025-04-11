package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductoTest {
	private Producto producto;

	@BeforeEach
	void setUp() throws Exception {
		this.producto = new Producto("nombre1","categoria1",10,20);
	}

	@Test
	void testComprar() {
		assertFalse(this.producto.comprar(21));
		assertTrue(this.producto.comprar(20));
	}

}
