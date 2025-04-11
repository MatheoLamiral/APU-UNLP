package ar.edu.unlp.info.oo1.ejercicio13_clienteDeCorreo;

import java.util.List;

public class Email {
	private String titulo;
	private String cuerpo;
	private List<Archivo> adjuntos;
	
	public Email(String titulo, String cuerpo, List<Archivo> adjuntos) {
		this.titulo = titulo;
		this.cuerpo = cuerpo;
		this.adjuntos = adjuntos;
	}

	public List<Archivo> adjuntos(){
		return this.adjuntos;		
	}
	
	public int tamaño() {
		return this.titulo.length()+this.cuerpo.length()+this.adjuntos.stream().mapToInt(adjunto -> adjunto.tamaño()).sum();
	}
	
	public boolean buscar(String texto) {
		return ((this.titulo.contains(texto))||(this.cuerpo.contains(texto)));
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCuerpo() {
		return cuerpo;
	}
	
	
	
	

}
