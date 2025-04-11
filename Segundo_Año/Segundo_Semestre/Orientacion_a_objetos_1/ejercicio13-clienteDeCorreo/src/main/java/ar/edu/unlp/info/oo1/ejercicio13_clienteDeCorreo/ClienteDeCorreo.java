package ar.edu.unlp.info.oo1.ejercicio13_clienteDeCorreo;

import java.util.List;

public class ClienteDeCorreo {
	private List<Carpeta> carpetas;
	private Carpeta inbox;
		
	
	public ClienteDeCorreo(List<Carpeta> carpetas, Carpeta inbox) {
		this.carpetas = carpetas;
		this.inbox = inbox;
	}

	public void recibir (Email email) {
		this.inbox.agregarMail(email);
	}
	
	public Email buscar(String texto) {
		return this.carpetas.stream().map(carpeta -> carpeta.buscar(texto)).findFirst().orElse(null);	
		
	}
	
	public int espacioOcupado() {
		return this.carpetas.stream().mapToInt(carpeta -> carpeta.espacioOcupado()).sum();
	}

}
