package ar.edu.unlp.info.oo1.ejercicio14_dateLapse;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateLapseTest {
	private DateLapse dateLapse;

	@BeforeEach
	void setUp() throws Exception {
		this.dateLapse = new DateLapse(2024,10,01,2024,10,10);
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
