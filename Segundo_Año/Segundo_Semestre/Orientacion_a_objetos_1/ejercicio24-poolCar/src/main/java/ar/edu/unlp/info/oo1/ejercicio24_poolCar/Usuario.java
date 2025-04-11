package ar.edu.unlp.info.oo1.ejercicio24_poolCar;

public abstract class Usuario {
	private String nombre;
	private double saldo;
	
	public abstract double calcularComision(double saldo);
	
	public void cargarSaldo(double saldo) {
		this.saldo = saldo - this.calcularComision(saldo);
	}
}
