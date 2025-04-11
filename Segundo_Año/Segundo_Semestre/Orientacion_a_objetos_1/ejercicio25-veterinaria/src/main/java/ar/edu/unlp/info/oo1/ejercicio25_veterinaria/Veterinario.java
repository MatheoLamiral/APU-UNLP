package ar.edu.unlp.info.oo1.ejercicio25_veterinaria;

import java.time.LocalDate;

import ar.edu.unlp.info.oo1.ejercicio14_dateLapse.DateLapse;

public class Veterinario {
	private String nombre;
	private LocalDate fechaIngreso;
	private double honorarios;
	
	public double getHonorarios() {
		return this.honorarios;
	}
	
	public int calcularAntiguedad() {
		DateLapse dl = new DateLapse(this.fechaIngreso,LocalDate.now());
		return dl.sizeInDays()*100;
	}
}
