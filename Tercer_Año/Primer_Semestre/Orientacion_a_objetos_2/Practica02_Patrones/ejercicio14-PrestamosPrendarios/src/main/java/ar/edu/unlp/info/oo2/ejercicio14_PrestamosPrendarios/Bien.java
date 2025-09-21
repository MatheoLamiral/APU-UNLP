package ar.edu.unlp.info.oo2.ejercicio14_PrestamosPrendarios;

public abstract class Bien {
	private double liquidez;
	
	public Bien(double liquidez) {
		this.liquidez = liquidez;
	}

	public abstract double calcularValor();
	
	public double calcularValorPrendario() {
		return this.calcularValor() * this.liquidez;
	}
}
