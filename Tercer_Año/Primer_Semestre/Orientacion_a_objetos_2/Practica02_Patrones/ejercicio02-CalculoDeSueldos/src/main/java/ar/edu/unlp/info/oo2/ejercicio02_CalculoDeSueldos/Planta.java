package ar.edu.unlp.info.oo2.ejercicio02_CalculoDeSueldos;

public class Planta extends Empleado {
	private int antiguedad;
	
	public Planta(String legajo, boolean esCasado, int cantidadHijos, int cantidadHoras, int antiguedad) {
		super(legajo, esCasado, cantidadHijos, cantidadHoras);
		this.antiguedad = antiguedad;
	}	

	@Override
	public double calcularBasico() {
		return 50000;
	}

	@Override
	public double calcularAdicional() {
		double adicional = (this.isEsCasado()) ? 5000 + this.getCantidadHijos() * 2000 + this.antiguedad * 2000: 5000;
		return adicional;
	}
}
