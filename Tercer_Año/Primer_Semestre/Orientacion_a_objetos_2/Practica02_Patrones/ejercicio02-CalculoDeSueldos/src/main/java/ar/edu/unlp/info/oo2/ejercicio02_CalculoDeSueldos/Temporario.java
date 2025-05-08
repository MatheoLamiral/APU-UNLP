package ar.edu.unlp.info.oo2.ejercicio02_CalculoDeSueldos;


public class Temporario extends Empleado {
	
	public Temporario(String legajo, boolean esCasado, int cantidadHijos, int cantidadHoras) {
		super(legajo, esCasado, cantidadHijos, cantidadHoras);
	}

	@Override
	public double calcularBasico() {
		return 20000 + (this.getCantidadHoras() * 300);
	}
	
	@Override
	public double calcularAdicional() {
		double adicional = (this.isEsCasado()) ? 5000 + this.getCantidadHijos() * 2000 : 5000;
		return adicional;
	}
}
