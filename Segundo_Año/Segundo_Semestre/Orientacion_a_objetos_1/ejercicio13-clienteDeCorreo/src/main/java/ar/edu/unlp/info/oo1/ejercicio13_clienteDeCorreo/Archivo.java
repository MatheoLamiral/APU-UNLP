package ar.edu.unlp.info.oo1.ejercicio13_clienteDeCorreo;

public class Archivo {
	private String nombre;
	
	
	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public int tamaño() {
		return this.nombre.length();
	}
}
