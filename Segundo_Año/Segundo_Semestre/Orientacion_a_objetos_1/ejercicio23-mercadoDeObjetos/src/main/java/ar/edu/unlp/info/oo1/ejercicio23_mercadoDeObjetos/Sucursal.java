package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;

public class Sucursal implements FormaDeEnvio {
	private double comision = 3000;

	@Override
	public double calcularCosto() {
		return this.comision;
	}

}
