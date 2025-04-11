package ar.edu.unlp.info.oo1.ejercicio25_veterinaria;

import java.time.LocalDate;

public abstract class Servicio {
	private Mascota mascota;
	private LocalDate fecha;
	
	public Servicio(Mascota mascota) {
		this.mascota = mascota;
		this.fecha = LocalDate.now();
		this.mascota.agregarServicio(this);
	}

	public abstract double calcularCosto();
	
	public abstract double recaudacionDeMascota(LocalDate fecha, Mascota mascota);
	
	protected LocalDate getFecha() {
		return this.fecha;
	}
	
	protected int ServiciosDeMacota() {
		return this.mascota.cantidadDeServicios();
	}
	
}
