package ar.edu.unlp.info.oo1.ejercicio13_clienteDeCorreo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteDeCorreoTest {
	private ClienteDeCorreo cc;
	private List<Carpeta> carpetas;
	private Carpeta inbox;
	private Email email;

	@BeforeEach
	void setUp() throws Exception {
		this.carpetas = new LinkedList<Carpeta>();
		List<Email> emails = new LinkedList<Email>();
		this.email = new Email("titulo1","cuerpo1",new LinkedList<Archivo>());
		emails.add(this.email);
		carpetas.add(new Carpeta ("carpeta 1", emails));
		this.cc = new ClienteDeCorreo (carpetas, new Carpeta("inbox",new LinkedList<Email>()));
	}

	@Test
	void testBuscar() {
		assertEquals(this.email, this.cc.buscar("cuer"));
		
	}

}
