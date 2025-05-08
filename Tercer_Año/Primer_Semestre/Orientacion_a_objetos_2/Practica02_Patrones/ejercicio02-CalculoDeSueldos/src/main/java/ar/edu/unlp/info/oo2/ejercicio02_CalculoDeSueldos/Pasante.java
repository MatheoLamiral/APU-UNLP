package ar.edu.unlp.info.oo2.ejercicio02_CalculoDeSueldos;

public class Pasante extends Empleado {
	private int examenesRendidos;
	
	public Pasante(String legajo, boolean esCasado, int cantidadHijos, int cantidadHoras, int examenesRendidos) {
		super(legajo, esCasado, cantidadHijos, cantidadHoras);
		this.examenesRendidos = examenesRendidos;
	}

	@Override
	public double calcularBasico() {
		return 20000;
	}

	@Override
	public double calcularAdicional() {
		return 2000 * this.examenesRendidos;
	}
	
	
}
