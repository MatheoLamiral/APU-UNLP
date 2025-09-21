package ar.edu.unlp.info.oo2.ejercicio09_AlquilerAutomoviles;

import java.time.LocalDate;

public class AutoEnAlquiler {
	private double precioPorDia;
	private int cantidadDePlazas;
	private String marca;
	private AutoStrategy strategy;
	
	
	
	public AutoEnAlquiler(double precioPorDia, int cantidadDePlazas, String marca, AutoStrategy strategy) {
		this.precioPorDia = precioPorDia;
		this.cantidadDePlazas = cantidadDePlazas;
		this.marca = marca;
		this.strategy = strategy;
	}

	public double montoAReembolsar(double montoPagado, LocalDate fechaCancelacion, LocalDate fechaInicio) {
		return this.strategy.montoAReembolsar(montoPagado, fechaCancelacion, fechaInicio);
	}
	
	protected double getPrecioPorDia() {
		return precioPorDia;
	}
	
	protected void switchStrategy(AutoStrategy strategy) {
		this.strategy = strategy;
	}
	
}
