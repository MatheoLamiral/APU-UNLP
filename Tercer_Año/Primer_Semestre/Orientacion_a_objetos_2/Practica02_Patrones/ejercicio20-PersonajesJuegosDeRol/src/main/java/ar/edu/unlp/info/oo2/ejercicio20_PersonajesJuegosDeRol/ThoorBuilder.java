package ar.edu.unlp.info.oo2.ejercicio20_PersonajesJuegosDeRol;

import java.util.ArrayList;
import java.util.List;

public class ThoorBuilder extends PersonajeBuilder {

	@Override
	public void agregarArmadura() {
		// TODO Auto-generated method stub
		this.getPersonaje().setArmadura(new Hierro());
	}

	@Override
	public void agregarArma() {
		// TODO Auto-generated method stub
		this.getPersonaje().setArma(new Martillo());
	}

	@Override
	public void agregarHabilidades() {
		// TODO Auto-generated method stub
		List<String> habilidades = new ArrayList();
		habilidades.add("lanzar rayos");
		habilidades.add("combate a distancia");
		this.getPersonaje().setHabilidades(habilidades);
	}

}
