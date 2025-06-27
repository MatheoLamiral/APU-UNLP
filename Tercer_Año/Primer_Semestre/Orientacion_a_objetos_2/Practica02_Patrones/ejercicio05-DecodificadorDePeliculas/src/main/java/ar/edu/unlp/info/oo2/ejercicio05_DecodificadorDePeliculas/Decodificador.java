package ar.edu.unlp.info.oo2.ejercicio05_DecodificadorDePeliculas;

import java.util.*;
import java.util.stream.Collectors;

public class Decodificador {
	private List<Pelicula> grilla;
	private List<Pelicula> reproducidas;
	private DecodificadorStrategy strategy;
	
	
	
	public Decodificador(List<Pelicula> grilla, List<Pelicula> reproducidas, DecodificadorStrategy strategy) {
		this.grilla = grilla;
		this.reproducidas = reproducidas;
		this.strategy = strategy;
	}

	public void setStrategy(DecodificadorStrategy strategy) {
		this.strategy = strategy;
	}
	
	public List<Pelicula> sugerir() {
		return this.strategy.sugerir(this.reproducidas,this.noVistas());
	}
	
	public List<Pelicula> noVistas() {
		return this.grilla.stream().filter(pelicula -> !this.reproducidas.contains(pelicula)).collect(Collectors.toList());
	}
}
