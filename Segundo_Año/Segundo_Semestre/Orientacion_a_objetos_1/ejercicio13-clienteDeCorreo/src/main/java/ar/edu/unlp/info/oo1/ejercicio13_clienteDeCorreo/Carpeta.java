package ar.edu.unlp.info.oo1.ejercicio13_clienteDeCorreo;

import java.util.List;

public class Carpeta {
	private String nombre;
	private List<Email> emails;
	
	
	public Carpeta(String nombre, List<Email> emails) {
		this.nombre = nombre;
		this.emails = emails;
	}

	public void mover(Email email, Carpeta destino) {
		this.emails.remove(email);
		destino.agregarMail(email);
		
	}
	
	protected Email buscar(String texto) {
		return this.emails.stream().filter(email -> email.buscar(texto)).findFirst().orElse(null);
	}
	
	public int espacioOcupado() {
		return this.emails.stream().mapToInt(email -> email.tamaño()).sum();
	}
	
	protected void agregarMail(Email email) {
		this.emails.add(email);
		
	}

	public String getNombre() {
		return nombre;
	}
	
	
	
}
