package ar.edu.unlp.info.oo1.ejercicio25_veterinaria;

public class Vacunacion extends ServicioMedico {
	private String nombreVacuna;
	private double costo;
	
	public Vacunacion(Mascota mascota, Veterinario veterinario, String nombreVacuna, double costo) {
		super(mascota, veterinario);
		this.nombreVacuna = nombreVacuna;
		this.costo = costo;
	}

	@Override
	protected double calcularAdicionales() {
		this.setDescartables(500);
		return this.costo;
	}
	
	

}
