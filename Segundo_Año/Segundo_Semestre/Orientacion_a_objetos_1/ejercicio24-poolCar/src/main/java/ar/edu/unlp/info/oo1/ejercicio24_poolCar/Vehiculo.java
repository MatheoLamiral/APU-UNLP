package ar.edu.unlp.info.oo1.ejercicio24_poolCar;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Vehiculo {
	private Conductor due;
	private String desc;
	private int cpacidad;
	private LocalDate anioFab;
	private double valor;
	
	public long calcularAntiguedad(){
		return ChronoUnit.YEARS.between(this.anioFab, LocalDate.now());
	}
}
