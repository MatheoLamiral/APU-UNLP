package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;

public class Domicilio implements FormaDeEnvio {
	private double comision = 0.5;
	private CalculadoraDeDistancia calculadora;

	@Override
	public double calcularCosto() {
		return 100*this.comision;
	}

}
