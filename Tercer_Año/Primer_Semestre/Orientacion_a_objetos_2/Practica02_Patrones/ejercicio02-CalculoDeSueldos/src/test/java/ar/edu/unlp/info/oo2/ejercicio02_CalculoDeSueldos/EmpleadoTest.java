package ar.edu.unlp.info.oo2.ejercicio02_CalculoDeSueldos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpleadoTest {
	private Temporario temporario;
	private Pasante pasante;
	private Planta planta;
	
	@BeforeEach
	void setUp() throws Exception {
		//no está casado, 0 hijos, 50 horas trabajadas y 10 exámenes rendidos
		this.pasante = new Pasante("1234-1",false,0,50,10);
		//está casado, 2 hijos y 200 horas trabajadas
		this.temporario = new Temporario("1234-2",true,2,200);
		//está casado, 3 hijos, 500 horas trabajadas y 20 años de antiguedad
		this.planta = new Planta("1234-3",true,3,500,20);
	}
	
	@Test
	void testClacularSueldo() {
		//(básico + adicional) - descuento
		//(20000 + 20000) - 3600 -> 36400
		assertEquals(36400,this.pasante.calcularSueldo());
		//(80000 + 9000) - 10850 -> 78150
		assertEquals(78150,this.temporario.calcularSueldo());
		//(50000 + 51000) - 9050 -> 91950
		assertEquals(91950,this.planta.calcularSueldo());
	}
	
	@Test
	void testClacularBasico() {
		//20000 por defecto
		assertEquals(20000,this.pasante.calcularBasico());
		//20000 + (horas trabajadas * 300) -> 20000 + (200 * 300) -> 20000 + 60000 -> 80000
		assertEquals(80000,this.temporario.calcularBasico());
		//50000 por defecto
		assertEquals(50000,this.planta.calcularBasico());
	}
	
	@Test
	void testClacularAdicional() {
		//2000 * examenes rendidos -> 2000 * 10 -> 20000
		assertEquals(20000,this.pasante.calcularAdicional());
		//5000 + 2000 * hijo -> 5000 + 2000 * 2 -> 9000
		assertEquals(9000,this.temporario.calcularAdicional());
		//5000
		Temporario temporarioSoltero = new Temporario ("1234-2",false,2,200);
		assertEquals(5000,temporarioSoltero.calcularAdicional());
		//5000 + 2000 * hijo + 2000 * antiguedad -> 5000 + 2000 * 3 + 2000 * 20 -> 51000
		assertEquals(51000,this.planta.calcularAdicional());
		//500
		Planta plantaSoltero = new Planta("1234-3",false,3,500,20);
		assertEquals(5000,plantaSoltero.calcularAdicional());
	}
	
	@Test
	void testClacularDescuento() {
		//(Para todos) 0.13 * básico + 0.05 * adicional
		//0.13 * 20000 + 0.05 * 20000 -> 3600
		assertEquals(3600,this.pasante.calcularDescuento());
		//0.13 * 80000 + 0.05 * 9000 -> 10850
		assertEquals(10850,this.temporario.calcularDescuento());
		//0.13 * 50000 + 0.05 * 51000 -> 9050
		assertEquals(9050,this.planta.calcularDescuento());
	}

}
