package ar.edu.unlp.info.oo2.ejercicio05_DecodificadorDePeliculas;

import java.util.*;
import java.util.stream.Collectors;

public class Similaridad extends DecodificadorStrategy {

	@Override
	public List<Pelicula> sugerir(List<Pelicula> reproducidas, List<Pelicula> noVistas) {
		return this.select(this.orderByYear(reproducidas.stream()
														.flatMap(pelicula -> pelicula.getSimilares().stream()
																											.distinct())
																											.collect(Collectors.toList())));
	}

	

}
