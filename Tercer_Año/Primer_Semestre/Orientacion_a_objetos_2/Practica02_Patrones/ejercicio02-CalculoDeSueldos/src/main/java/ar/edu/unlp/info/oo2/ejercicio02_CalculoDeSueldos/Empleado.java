package ar.edu.unlp.info.oo2.ejercicio02_CalculoDeSueldos;

public abstract class Empleado {
	private String legajo;
	private boolean esCasado;
	private int cantidadHijos;
	private int cantidadHoras;
	
	public Empleado(String legajo, boolean esCasado, int cantidadHijos, int cantidadHoras) {
		this.legajo = legajo;
		this.esCasado = esCasado;
		this.cantidadHijos = cantidadHijos;
		this.cantidadHoras = cantidadHoras;
	}

	public double calcularSueldo() {
		return (this.calcularBasico() + this.calcularAdicional()) - this.calcularDescuento();
	}
	
	public abstract double calcularBasico();
	
	public abstract double calcularAdicional();
	
	public double calcularDescuento() {
		return this.calcularBasico() * 0.13 + this.calcularAdicional() * 0.05;
	}

	public String getLegajo() {
		return legajo;
	}

	public boolean isEsCasado() {
		return esCasado;
	}

	public int getCantidadHijos() {
		return cantidadHijos;
	}

	public int getCantidadHoras() {
		return cantidadHoras;
	}
	
	
}
