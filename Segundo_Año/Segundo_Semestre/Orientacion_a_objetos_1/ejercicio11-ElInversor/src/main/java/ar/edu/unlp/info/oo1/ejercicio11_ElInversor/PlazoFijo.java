package ar.edu.unlp.info.oo1.ejercicio11_ElInversor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlazoFijo implements Inversion {
	private LocalDate fecha;
	private double montoDepositado;
	private double porcentajeDeInteresDiario;
	

	public PlazoFijo(LocalDate fecha, double montoDepositado, double porcentajeDeInteresDiario) {
		this.fecha = fecha;
		this.montoDepositado = montoDepositado;
		this.porcentajeDeInteresDiario = porcentajeDeInteresDiario;
	}


	@Override
	public double valorActual() {
		int days = (int) ChronoUnit.DAYS.between(this.fecha, LocalDate.now());
		return this.montoDepositado + (this.porcentajeDeInteresDiario * days);
	}

}
