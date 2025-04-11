package ar.edu.unlp.info.oo1.ejercicio24_poolCar;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Pasajero extends Usuario {
	private List<Viaje> viajes;

	public void registrarseEnViaje() {
		
	}
	
	public boolean existeViaje(int dias){
		  return viajes.stream()   
		 .anyMatch(viaje->ChronoUnit.DAYS.between(LocalDate.now(), viaje.getFecha())<=dias);
		}

	@Override
	public double calcularComision(double saldo) {
		if (existeViaje(30)) {
			return saldo * 0.1;
		}
		return 0;
	}
}
