package ar.edu.unlp.info.oo2.ejercicio06_Excursiones;

public class Usuario {
	private String nombre;
	private String apellido;
	private String email;
	
	public Usuario(String nombre, String apellido, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}
	
	protected String getNombre() {
		return nombre;
	}
	protected String getApellido() {
		return apellido;
	}
	protected String getEmail() {
		return email;
	}
}
