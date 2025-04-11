package ar.edu.unlp.info.oo1.ejercicio25_veterinaria;

import java.time.LocalDate;
import java.time.DayOfWeek;

public abstract class ServicioMedico extends Servicio {
	private Veterinario veterinario;
	private double precioDescartables;
	private double adicionalPorDomingo;
	
	
	public ServicioMedico(Mascota mascota, Veterinario veterinario) {
		super(mascota);
		this.veterinario = veterinario;
		this.adicionalPorDomingo = 200;
	}

	@Override
	public double calcularCosto() {
		double adicionalPorDomingo = 0;
		if(LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY){
			adicionalPorDomingo = this.adicionalPorDomingo;
		}
		return this.veterinario.getHonorarios()+adicionalPorDomingo+this.precioDescartables+this.calcularAdicionales();
	}
	
	protected abstract double calcularAdicionales();
	
	protected Veterinario getVeterinario() {
		return this.veterinario;
	}

	@Override
	public double recaudacionDeMascota(LocalDate fecha, Mascota mascota) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected void setDescartables(double precioPorDescartables) {
		this.precioDescartables = precioPorDescartables;
	}

}
