package ar.edu.unlp.info.oo1.ejercicio25_veterinaria;

import java.time.LocalDate;

public class Consulta extends ServicioMedico {
	private LocalDate fechaAtencion;
	private double bonoPorAnio;

	public Consulta(Mascota mascota, Veterinario veterinario, LocalDate fechaAtencion) {
		super(mascota, veterinario);
		this.fechaAtencion = fechaAtencion;
		this.bonoPorAnio = 100;
	}

	@Override
	protected double calcularAdicionales() {
		this.setDescartables(300);
		return this.getVeterinario().calcularAntiguedad()*this.bonoPorAnio;
	}
	
	
}
