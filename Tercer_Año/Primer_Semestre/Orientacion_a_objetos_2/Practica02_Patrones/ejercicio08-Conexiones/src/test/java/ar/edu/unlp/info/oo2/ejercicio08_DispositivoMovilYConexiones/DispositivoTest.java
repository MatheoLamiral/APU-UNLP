package ar.edu.unlp.info.oo2.ejercicio08_DispositivoMovilYConexiones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DispositivoTest {
	private Dispositivo dispositivo;

	@BeforeEach
	void setUp() throws Exception {
		this.dispositivo = new Dispositivo();
	}

	@Test
	void testSendConnectionWifi() {
		assertEquals("wifi: mensaje 53155", this.dispositivo.send("mensaje"));
	}
	
	@Test
	void testSendConnection4G() {
		this.dispositivo.setConnection(new Connection4GAdapter());;
		assertEquals("4G: mensaje 53155", this.dispositivo.send("mensaje"));
	}
	
	@Test
	void testSetConnection() {
		assertEquals(this.dispositivo.getConnection().pict(), "wifi");
		this.dispositivo.setConnection(new Connection4GAdapter());
		assertEquals(this.dispositivo.getConnection().pict(), "4G");
		this.dispositivo.setConnection(new ConnectionWifi());
		assertEquals(this.dispositivo.getConnection().pict(), "wifi");
	}
	
	@Test
	void testSetCRC() {
		String data = "Hola Mundo";
		assertEquals(this.dispositivo.getCrcCalculator().crcFor(data),3978);
		this.dispositivo.setStrategy(new CRC32Calculator());
		assertEquals(this.dispositivo.getCrcCalculator().crcFor(data),574844626);
		data = "";
		assertEquals(this.dispositivo.getCrcCalculator().crcFor(data),0);
		this.dispositivo.setStrategy(new CRC16Calculator());
		assertEquals(this.dispositivo.getCrcCalculator().crcFor(data),65535);
	}

}
