package ar.edu.unlp.info.oo2.ejercicio17_AccesoABaseDeDatos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseRealAccesTest {
	private ProxyAutenticationDatabase proxy;
	
	@BeforeEach
	void setUp() throws Exception {
		this.proxy = new ProxyAutenticationDatabase("paswd");
		Logger.getLogger("ar.edu.unlp.info.oo2.ejercicio17_AccesoABaseDeDatos").setLevel(Level.ALL);
	}

	@Test
	void testLoggin() {
		//se deberia crear como no loggeado
		assertFalse(this.proxy.isLogged());
		//contraseña incorrecta no deberia modificar el estado de logged
		assertThrows(RuntimeException.class, () -> this.proxy.loggin("incorrect paswd"));
		assertFalse(this.proxy.isLogged());
		//contraseña correcta loggea
		this.proxy.loggin("paswd");
		assertTrue(this.proxy.isLogged());
	}
	
	@Test
	void testGetSearchResult() {
		//un usario que no está logeado no puede acceder a la base de datos
		assertThrows(RuntimeException.class, () -> this.proxy.getSearchResults("select * from comics where id=1"));
		//un usario loggeado puede acceder a la base de datos
		this.proxy.loggin("paswd");
		assertEquals(this.proxy.getSearchResults("select * from comics where id=1"), Arrays.asList("Spiderman", "Marvel"));

	}
	
	@Test
	void testInsertRow() {
		//un usario que no está logeado no puede acceder a la base de datos
		assertThrows(RuntimeException.class, () -> this.proxy.insertNewRow(Arrays.asList("iron man", "Marvel")));
		//un usario loggeado puede acceder a la base de datos
		this.proxy.loggin("paswd");
		//se habían agregados 2 comics, por ende al agregar uno más deberia devolvcer 3
		assertEquals(this.proxy.insertNewRow(Arrays.asList("iron man", "Marvel")), 3);
	}

}
