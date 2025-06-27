package ar.edu.unlp.info.oo2.ejercicio05_DecodificadorDePeliculas;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DecodificadorStrategy {
	private final int cantidadSeleccion = 3;

	public abstract List<Pelicula> sugerir(List<Pelicula> reproducidas, List<Pelicula> noVistas);
	
	public List<Pelicula> orderByYear(List<Pelicula> peliculas) {
		return peliculas.stream().sorted(Comparator.comparingInt(Pelicula :: getAnioEstreno).reversed()).collect(Collectors.toList());
	}
	
	public List<Pelicula> select(List<Pelicula> peliculas) {
		return peliculas.stream().limit(this.cantidadSeleccion).collect(Collectors.toList());
	}
}
