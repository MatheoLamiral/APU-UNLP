package ar.edu.unlp.info.oo2.ejercicio14_PrestamosPrendarios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Automovil extends Bien {
	private int modelo;
	private double kilometraje;
	private double costo0km;
	
	public Automovil(double liquidez, int modelo, double kilometraje, double costo0km) {
		super(liquidez);
		this.modelo = modelo;
		this.kilometraje = kilometraje;
		this.costo0km = costo0km;
	}

	@Override
	public double calcularValor() {
		// TODO Auto-generated method stub
		return this.costo0km * 0.10 * (LocalDate.now().getYear() - this.modelo); 
	}

}
