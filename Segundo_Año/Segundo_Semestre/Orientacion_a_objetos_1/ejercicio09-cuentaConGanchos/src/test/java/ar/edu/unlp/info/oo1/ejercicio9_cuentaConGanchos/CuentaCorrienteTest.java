package ar.edu.unlp.info.oo1.ejercicio9_cuentaConGanchos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CuentaCorrienteTest {

	private CuentaCorriente cuentaCorriente;
	
	@BeforeEach
	void setUp() throws Exception {
		this.cuentaCorriente = new CuentaCorriente();
		this.cuentaCorriente.setDescubierto(10);
	}
	
	@Test
	void testConstructor() {
		assertEquals(0,cuentaCorriente.getSaldo());
	}
	
	@Test
	void testDepositarCuentaCorriente() {
		cuentaCorriente.depositar(100);
		assertEquals(100,cuentaCorriente.getSaldo());
	}
	
	@Test
	void testPuedeExtraerCuentaCorriente() {
		cuentaCorriente.depositar(100);
		assertTrue(cuentaCorriente.puedeExtraer(110));
		assertFalse(cuentaCorriente.puedeExtraer(111));		
	}
	
	@Test
	void testExtraerCuentaCorriente() {
		double saldo = cuentaCorriente.getSaldo();
		cuentaCorriente.extraer(10);
		assertEquals((saldo-10), cuentaCorriente.getSaldo());
	}
	
	@Test
	void testTransferirACuentaCorrienteDesdeCuentaCorriente() {
		double saldo = cuentaCorriente.getSaldo();
		CuentaCorriente cuentaCorriente2 = new CuentaCorriente();
		//REALIZO LA TRANSFERENCIA
		cuentaCorriente.transferirACuenta(10, cuentaCorriente2);
		//VERIFICO QUE SE HAYA ELIMINADO EL MONTO DE LA CUENTA DE ORIGEN CONTAND LA COMISION DE 2%
		assertEquals((saldo - 10),cuentaCorriente.getSaldo());
		//VERIFICO QUE LA CUENTA DESTINO HAYA RECIBIDO EL MONTO 
		assertEquals(10,cuentaCorriente2.getSaldo());
	}
	
	@Test
	void testTransferirACajaDeAhorroDesdeCuentaCorriente() {
		double saldo = cuentaCorriente.getSaldo();
		double monto = 10;
		CajaDeAhorro cajaDeAhorro = new CajaDeAhorro();
		//REALIZO LA TRANSFERENCIA
		cuentaCorriente.transferirACuenta(monto, cajaDeAhorro);
		//VERIFICO QUE SE HAYA ELIMINADO EL MONTO DE LA CUENTA DE ORIGEN 
		assertEquals((saldo - monto),cajaDeAhorro.getSaldo());
		//VERIFICO QUE LA CUENTA DESTINO HAYA RECIBIDO EL MONTO 
		assertEquals((monto * 1.02),cajaDeAhorro.getSaldo());
	}

}
