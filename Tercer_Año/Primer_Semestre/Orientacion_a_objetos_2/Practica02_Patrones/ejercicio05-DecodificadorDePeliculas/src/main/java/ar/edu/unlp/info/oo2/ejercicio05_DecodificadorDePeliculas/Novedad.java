package ar.edu.unlp.info.oo2.ejercicio05_DecodificadorDePeliculas;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Novedad extends DecodificadorStrategy {

	@Override
	public List<Pelicula> sugerir(List<Pelicula> reproducidas, List<Pelicula> noVistas) {
		return this.select(this.orderByYear(noVistas));
	}

	

}
