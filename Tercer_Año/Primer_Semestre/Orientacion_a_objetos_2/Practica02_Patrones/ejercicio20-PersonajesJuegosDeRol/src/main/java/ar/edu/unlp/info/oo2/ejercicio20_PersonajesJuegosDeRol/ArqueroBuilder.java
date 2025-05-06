package ar.edu.unlp.info.oo2.ejercicio20_PersonajesJuegosDeRol;

import java.util.ArrayList;
import java.util.List;

public class ArqueroBuilder extends PersonajeBuilder {

	@Override
	public void agregarArmadura() {
		// TODO Auto-generated method stub
		this.getPersonaje().setArmadura(new Cuero());
	}

	@Override
	public void agregarArma() {
		// TODO Auto-generated method stub
		this.getPersonaje().setArma(new Arco());
	}

	@Override
	public void agregarHabilidades() {
		// TODO Auto-generated method stub
		List<String> habilidades = new ArrayList();
		habilidades.add("combate a distancia");
		this.getPersonaje().setHabilidades(habilidades);
	}

}
