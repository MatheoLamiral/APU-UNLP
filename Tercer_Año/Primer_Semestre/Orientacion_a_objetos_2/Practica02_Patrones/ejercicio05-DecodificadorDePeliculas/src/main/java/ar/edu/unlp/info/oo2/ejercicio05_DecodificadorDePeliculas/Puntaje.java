package ar.edu.unlp.info.oo2.ejercicio05_DecodificadorDePeliculas;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Puntaje extends DecodificadorStrategy {

	@Override
	public List<Pelicula> sugerir(List<Pelicula> reproducidas, List<Pelicula> noVistas) {
		return this.select(noVistas.stream()
								   .sorted(Comparator.comparingDouble(Pelicula::getPuntaje)
								   .reversed()
								   .thenComparing(Comparator.comparingInt(Pelicula::getAnioEstreno)
								   .reversed()))
								   .collect(Collectors.toList()));
	}

}
