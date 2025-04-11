package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;

public class Contado implements FormaDePago {

	@Override
	public double calcularPrecioFinal(double monto) {
		return monto;
	}

}
