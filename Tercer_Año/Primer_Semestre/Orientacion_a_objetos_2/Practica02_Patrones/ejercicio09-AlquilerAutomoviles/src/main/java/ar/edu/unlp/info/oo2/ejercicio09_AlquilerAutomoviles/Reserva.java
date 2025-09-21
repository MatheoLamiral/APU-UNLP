package ar.edu.unlp.info.oo2.ejercicio09_AlquilerAutomoviles;

import java.time.LocalDate;

public class Reserva {
	private int cantidadDeDias;
	private LocalDate fecha;
	private Usuario conductor;
	private AutoEnAlquiler auto;
	
	public Reserva(int cantidadDeDias, LocalDate fecha, Usuario conductor, AutoEnAlquiler auto) {
		this.cantidadDeDias = cantidadDeDias;
		this.fecha = fecha;
		this.conductor = conductor;
		this.auto = auto;
	}

	public double montoAPagar() {
		return this.auto.getPrecioPorDia() * this.cantidadDeDias;
	}
	
	public double montoAReembolsar(LocalDate fechaCancelacion) {
		return this.auto.montoAReembolsar(this.montoAPagar(),fechaCancelacion,this.fecha);
	}
}
