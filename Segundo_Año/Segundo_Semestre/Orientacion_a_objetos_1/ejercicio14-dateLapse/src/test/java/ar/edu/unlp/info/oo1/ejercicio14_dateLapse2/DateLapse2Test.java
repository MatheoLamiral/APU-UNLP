package ar.edu.unlp.info.oo1.ejercicio14_dateLapse2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlp.info.oo1.ejercicio14_dateLapse.DateLapse;
import ar.edu.unlp.info.oo1.ejercicio14_dateLapseSizeInDays.DateLapseSizeInDays;

class DateLapse2Test {
	private DateLapseSizeInDays dateLapse;

	@BeforeEach
	void setUp() throws Exception {
		this.dateLapse = new DateLapseSizeInDays(2024,10,01,9);
	}

	@Test
	void testSizeInDays() {
		assertEquals(9,this.dateLapse.sizeInDays());
	}
	
	@Test
	void testIncludesDate() {
		LocalDate date = LocalDate.of(2024, 10, 5);
		assertTrue(this.dateLapse.includesDate(date));
		LocalDate date2 = LocalDate.of(2024, 10, 20);
		assertFalse(this.dateLapse.includesDate(date2));
	}

}
