package ar.edu.unlp.info.oo2.ejercicio05_DecodificadorDePeliculas;

import java.util.*;

public class Pelicula {
	private List<Pelicula> similares;
	private String titulo;
	private int anioEstreno;
	private double puntaje;
	
	public Pelicula(List<Pelicula> similares, String titulo, int anioEstreno, double puntaje) {
		this.similares = similares;
		this.titulo = titulo;
		this.anioEstreno = anioEstreno;
		this.puntaje = puntaje;
	}

	protected List<Pelicula> getSimilares() {
		return this.similares;
	}

	public int getAnioEstreno() {
		return anioEstreno;
	}

	public double getPuntaje() {
		return puntaje;
	}
	
	
 }
