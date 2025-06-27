package ar.edu.unlp.info.oo2.ejercicio06_Excursiones;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExcursionTest {
	private Excursion excursion;
	
	@BeforeEach
	void setUp() throws Exception {
		this.excursion = new Excursion("Excursion1",LocalDate.of(2025, 05, 10), LocalDate.of(2025, 05, 11), "Punto de encuentro", 200, 5, 3);
	}

	@Test
	void testProvisoria() {
		assertEquals(this.excursion.obtenerInformacion(), this.excursion.toString() + "\n" + "inscripciones faltantes para completar el cupo minimo: " + 3);
	}
	
	@Test
	void testDefinitiva() {
		this.excursion.inscribir(new Usuario("nombre1","apellido1","email1"));
		this.excursion.inscribir(new Usuario("nombre2","apellido2","email2"));
		this.excursion.inscribir(new Usuario("nombre3","apellido3","email3"));
		assertEquals(this.excursion.obtenerInformacion(), this.excursion.toString() + "\n" + "mails de inscriptos: " 
				+ "[email1, email2, email3]"
				+ "\n"
				+ "inscripciones faltantes para el cupo máximo: "
				+ 2);
	}
	
	void testCompleta() {
		this.excursion.inscribir(new Usuario("nombre1","apellido1","email1"));
		this.excursion.inscribir(new Usuario("nombre2","apellido2","email2"));
		this.excursion.inscribir(new Usuario("nombre3","apellido3","email3"));
		this.excursion.inscribir(new Usuario("nombre4","apellido4","email4"));
		this.excursion.inscribir(new Usuario("nombre5","apellido5","email5"));
		assertEquals(this.excursion.obtenerInformacion(), this.excursion.toString());
	}
	
	

}
