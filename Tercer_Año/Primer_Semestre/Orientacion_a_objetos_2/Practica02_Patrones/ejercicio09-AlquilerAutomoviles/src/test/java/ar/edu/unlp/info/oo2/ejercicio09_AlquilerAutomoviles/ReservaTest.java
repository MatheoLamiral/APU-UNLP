package ar.edu.unlp.info.oo2.ejercicio09_AlquilerAutomoviles;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservaTest {
	private AutoEnAlquiler auto1;
	private AutoEnAlquiler auto2;
	private AutoEnAlquiler auto3;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	private Usuario usuario;
	
	@BeforeEach
	void setUp() throws Exception {
		List<AutoEnAlquiler> autos = new ArrayList();
		autos.add(this.auto1 = new AutoEnAlquiler(20, 4, "Ford", new Flexible()));
		autos.add(this.auto2 = new AutoEnAlquiler(30, 4, "Honda", new Moderada()));
		autos.add(this.auto3 = new AutoEnAlquiler(40, 2, "Porge", new Estricta()));
		this.usuario = new Usuario("usuario1",autos);
		this.reserva1 = new Reserva(10, LocalDate.of(2024, 06, 5), this.usuario, this.auto1);
		this.reserva2 = new Reserva(20, LocalDate.of(2024, 07, 5), this.usuario, this.auto2);
		this.reserva3 = new Reserva(7, LocalDate.of(2024, 07, 5), this.usuario, this.auto3);
	}

	@Test
	void testFlexible() {
		assertEquals(200,this.reserva1.montoAPagar());
		assertEquals(200, this.reserva1.montoAReembolsar(LocalDate.of(2024, 06, 04)));
		assertThrows(RuntimeException.class, () -> this.reserva1.montoAReembolsar(LocalDate.of(2024, 06, 05)));
	}
	
	@Test
	void testModerada() {
		assertEquals(600,this.reserva2.montoAPagar());
		assertEquals(600, this.reserva2.montoAReembolsar(LocalDate.of(2024, 07, 2)));
		assertEquals(300, this.reserva2.montoAReembolsar(LocalDate.of(2024, 07, 3)));
		assertThrows(RuntimeException.class, () -> this.reserva2.montoAReembolsar(LocalDate.of(2024, 07, 05)));
	}
	
	@Test
	void testEstricta() {
		assertEquals(280,this.reserva3.montoAPagar());
		assertEquals(0, this.reserva3.montoAReembolsar(LocalDate.of(2024, 06, 05)));
		assertThrows(RuntimeException.class, () -> this.reserva3.montoAReembolsar(LocalDate.of(2024, 07, 05)));
	}


}
