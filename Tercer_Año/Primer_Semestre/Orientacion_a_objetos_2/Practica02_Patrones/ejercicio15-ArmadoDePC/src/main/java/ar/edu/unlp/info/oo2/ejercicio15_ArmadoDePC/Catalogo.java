package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

import java.util.List;
import java.util.stream.Collectors;

public class Catalogo {
	private static List<Componente> catalogo;
	
	public Catalogo(List<Componente> catalogo) {
		Catalogo.catalogo = catalogo;
	}
	
	public static Componente getComponente(String desc) {
		return catalogo.stream().filter(componente -> componente.getDesc().equals(desc)).findFirst().orElse(null);
				
	}
}
