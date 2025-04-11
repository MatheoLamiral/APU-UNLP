package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
	private Usuario usuario;

	@BeforeEach
	void setUp() throws Exception {
		this.usuario = new Usuario("usuario1","direccion1");
		this.usuario.crearPedido(new Contado (), new Comercio (), new Producto ("producto1","categoria1",10,2), 2);
		this.usuario.crearPedido(new Contado (), new Comercio (), new Producto ("producto2","categoria1",5,2), 2);
		this.usuario.crearPedido(new Contado (), new Comercio (), new Producto ("producto3","categoria2",10,2), 1);
	}

	@Test
	void testProductoPorCategoria() {
		assertEquals(2,this.usuario.productoPorCategoria().occurrencesOf("categoria1"));
		assertEquals(1,this.usuario.productoPorCategoria().occurrencesOf("categoria2"));
		assertEquals(0,this.usuario.productoPorCategoria().occurrencesOf("categoria3"));
	}

}
