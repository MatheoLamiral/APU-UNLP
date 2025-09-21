package ar.edu.unlp.info.oo2.ejercicio14_PrestamosPrendarios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alquileres extends Bien {
	private LocalDate comienzoContrato;
	private LocalDate finContrato;
	private double costoMensual;

	public Alquileres(double liquidez, LocalDate comienzoContrato, LocalDate finContrato, double costoMensual) {
		super(liquidez);
		this.comienzoContrato = comienzoContrato;
		this.finContrato = finContrato;
		this.costoMensual = costoMensual;
	}

	@Override
	public double calcularValor() {
		// TODO Auto-generated method stub
		return ChronoUnit.MONTHS.between(this.finContrato, this.comienzoContrato) * this.costoMensual;
	}

}
