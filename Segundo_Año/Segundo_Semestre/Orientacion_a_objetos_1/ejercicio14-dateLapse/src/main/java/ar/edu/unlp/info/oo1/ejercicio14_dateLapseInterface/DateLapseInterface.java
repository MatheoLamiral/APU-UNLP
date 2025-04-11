package ar.edu.unlp.info.oo1.ejercicio14_dateLapseInterface;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface DateLapseInterface {
	
	public int sizeInDays();
	
	public boolean includesDate(LocalDate date);
}
