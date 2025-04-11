package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;

public class Cuotas6 implements FormaDePago {
	private double interes = 0.20;

	@Override
	public double calcularPrecioFinal(double monto) {
		return monto*this.interes;
	}

}
