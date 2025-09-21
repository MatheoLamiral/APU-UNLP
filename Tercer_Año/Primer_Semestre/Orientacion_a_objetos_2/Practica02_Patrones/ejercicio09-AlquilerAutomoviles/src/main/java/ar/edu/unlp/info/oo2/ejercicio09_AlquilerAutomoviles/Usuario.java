package ar.edu.unlp.info.oo2.ejercicio09_AlquilerAutomoviles;

import java.util.List;

public class Usuario {
	private String nombre;
	private List<AutoEnAlquiler> autosEnAlquiler;
	
	public Usuario(String nombre, List<AutoEnAlquiler> autosEnAlquiler) {
		this.nombre = nombre;
		this.autosEnAlquiler = autosEnAlquiler;
	}
	
	
}
