package ar.edu.unlp.info.oo1.ejercicio8_lookUpConEmpleados;

public class Empleado {
	private String nombre;
	
	public double aportes() {
		return 13500;
	}
	
	public double montoBasico() {
		return 35000;
	}
	
	public double SueldoBasico() {
		return this.montoBasico() + this.aportes();
	}
}
