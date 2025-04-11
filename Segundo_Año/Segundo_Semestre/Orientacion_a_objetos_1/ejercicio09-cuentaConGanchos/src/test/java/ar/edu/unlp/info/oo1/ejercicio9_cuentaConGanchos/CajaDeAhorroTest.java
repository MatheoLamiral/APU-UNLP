package ar.edu.unlp.info.oo1.ejercicio9_cuentaConGanchos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CajaDeAhorroTest {
	private CajaDeAhorro cajaDeAhorro,cajaDeAhorroVacia;

	@BeforeEach
	void setUp() throws Exception {
		this.cajaDeAhorro = new CajaDeAhorro(100);
		this.cajaDeAhorroVacia = new CajaDeAhorro();

	}

	@Test
	void testDepositarCajaDeAhorro() {
		cajaDeAhorroVacia.depositar(100);
		//VERIFICO POR 98 YA QUE DEBERIA SACARSE UN 2% DE COMISION
		assertEquals(98,cajaDeAhorroVacia.getSaldo());
	}
	
	@Test
	void testPuedeExtraerCuentaCorriente() {
		assertTrue(cajaDeAhorro.puedeExtraer(98));
		assertFalse(cajaDeAhorro.puedeExtraer(99));		
	}
	
	@Test
	void testExtraerCajaDeAhorro() {
		double saldo = cajaDeAhorro.getSaldo();
		cajaDeAhorro.extraer(10);
		assertEquals((saldo-10), cajaDeAhorro.getSaldo());
	}
	
	@Test
	void testTransferirACajaDeAhorroDesdeCajaDeAhorro() {
		double saldo = cajaDeAhorro.getSaldo();
		double monto = 10;
		CajaDeAhorro cajaDeAhorro2 = new CajaDeAhorro();
		//REALIZO LA TRANSFERENCIA
		cajaDeAhorro.transferirACuenta(monto, cajaDeAhorro2);
		//VERIFICO QUE SE HAYA ELIMINADO EL MONTO DE LA CUENTA DE ORIGEN CONTAND LA COMISION DE 2%
		assertEquals((saldo - (monto*1.02)),cajaDeAhorro.getSaldo());
		//VERIFICO QUE LA CUENTA DESTINO HAYA RECIBIDO EL MONTO 
		assertEquals(monto,cajaDeAhorro2.getSaldo());
	}
	
	@Test
	void testTransferirACuentaCorrienteDesdeCajaDeAhorro() {
		double saldo = cajaDeAhorro.getSaldo();
		double monto = 10;
		CuentaCorriente cuentaCorriente = new CuentaCorriente();
		//REALIZO LA TRANSFERENCIA
		cajaDeAhorro.transferirACuenta(monto, cuentaCorriente);
		//VERIFICO QUE SE HAYA ELIMINADO EL MONTO DE LA CUENTA DE ORIGEN CONTAND LA COMISION DE 2%
		assertEquals((saldo - (monto*1.02)),cajaDeAhorro.getSaldo());
		//VERIFICO QUE LA CUENTA DESTINO HAYA RECIBIDO EL MONTO 
		assertEquals(monto,cuentaCorriente.getSaldo());
	}

}
