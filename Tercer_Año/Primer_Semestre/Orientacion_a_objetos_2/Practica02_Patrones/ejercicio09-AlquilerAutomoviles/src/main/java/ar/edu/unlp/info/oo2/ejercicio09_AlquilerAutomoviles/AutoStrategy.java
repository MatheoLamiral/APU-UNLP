package ar.edu.unlp.info.oo2.ejercicio09_AlquilerAutomoviles;

import java.time.LocalDate;

public interface AutoStrategy {

	public double montoAReembolsar(double montoPagado, LocalDate fechaCancelacion, LocalDate fechaInicio);
}
