package ar.edu.unlp.info.oo1.ejercicio8_lookUpConEmpleados;

public class EmpleadoJerarquico extends Empleado {
	
	public double montoBasico() {
		return 45000;
	}
	
	public double SueldoBasico() {
		return super.SueldoBasico() + this.bonoPorCategoria();
	}
	
	public double bonoPorCategoria() {
		return 8000;
	}
}
