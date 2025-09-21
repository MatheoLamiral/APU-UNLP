package ar.edu.unlp.info.oo2.ejercicio14_PrestamosPrendarios;

public class Inmueble extends Bien {
	private String direccion;
	private double superficie;
	private double costoMetroCuadrado;

	public Inmueble(double liquidez, String direccion, double superficie, double costoMetroCuadrado) {
		super(liquidez);
		this.direccion = direccion;
		this.superficie = superficie;
		this.costoMetroCuadrado = costoMetroCuadrado;
	}

	@Override
	public double calcularValor() {
		// TODO Auto-generated method stub
		return this.superficie * this.costoMetroCuadrado;
	}

}
