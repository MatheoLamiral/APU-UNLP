package ar.edu.unlp.info.oo1.ejercicio24_poolCar;

public class Conductor extends Usuario {
	private Vehiculo vehiculo;

	@Override
	public double calcularComision(double saldo) {
		if (this.vehiculo.calcularAntiguedad() > 5) {
			return saldo * 0.01;
		}
		return saldo * 0.1;
	}
	
	
}
