package ar.edu.unlp.info.oo1.ejercicio11_ElInversor;

import java.util.List;

public class Inversor {
	private String nombre;
	private List<Inversion> inversiones;
	
	public Inversor(String nombre, List<Inversion> inversiones) {
		this.nombre = nombre;
		this.inversiones = inversiones;
	}

	public double valorActual() {
		return this.inversiones.stream().mapToDouble(inversion -> inversion.valorActual()).sum();
	}
}
