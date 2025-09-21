package ar.edu.unlp.info.oo2.ejercicio09_AlquilerAutomoviles;

import java.time.LocalDate;

public class Estricta implements AutoStrategy {

	@Override
	public double montoAReembolsar(double montoPagado, LocalDate fechaCancelacion, LocalDate fechaInicio) {
		// TODO Auto-generated method stub
		if(fechaCancelacion.isBefore(fechaInicio))
			return 0;
		throw new RuntimeException("No se puede cancelar una reserva que ya inicio");
	}

}
