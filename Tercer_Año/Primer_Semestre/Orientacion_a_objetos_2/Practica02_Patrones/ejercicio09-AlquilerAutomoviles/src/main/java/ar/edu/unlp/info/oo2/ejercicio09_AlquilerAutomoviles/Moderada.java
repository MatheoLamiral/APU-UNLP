package ar.edu.unlp.info.oo2.ejercicio09_AlquilerAutomoviles;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class Moderada implements AutoStrategy {
	private double interes = 0.5;
	
	@Override
	public double montoAReembolsar(double montoPagado, LocalDate fechaCancelacion, LocalDate fechaInicio) {
		// TODO Auto-generated method stub
		if(fechaCancelacion.isBefore(fechaInicio)) {
			if(ChronoUnit.DAYS.between(fechaCancelacion, fechaInicio) >= 3) {
				return montoPagado;
			}
			else return montoPagado*interes;
		}
		throw new RuntimeException("No se puede cancelar una reserva que ya inicio");
	}

}
