package ar.edu.unlp.info.oo1.ejercicio25_veterinaria;

import java.time.LocalDate;


public class ServicioGuarderia extends Servicio {
	private int cantidadDeDias;
	private double costoDiario;
	
	public ServicioGuarderia(Mascota mascota, int cantidadDeDias) {
		super(mascota);
		this.cantidadDeDias = cantidadDeDias;
		this.costoDiario = 500;
	}

	private int cantidadDias;

	@Override
	public double calcularCosto() {
		double monto = this.costoDiario*this.cantidadDeDias;
		if(this.ServiciosDeMacota() >= 5) {
			monto = monto - (monto * (10/100));
		}
		return monto;
	}

	@Override
	public double recaudacionDeMascota(LocalDate fecha, Mascota mascota) {
		// TODO Auto-generated method stub
		return 0;
	}

}
