package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PC {
	private List<Componente> componentes;
	
	public PC() {
		this.componentes = new ArrayList();
	}
	
	public double calcularConsumo() {
		return this.componentes.stream().mapToDouble(componente -> componente.getConsumo()).sum();
	}
	
	public double calcularPrecio() {
		double total = this.componentes.stream().mapToDouble(componente -> componente.getPrecio()).sum();
		return total + total * 0.21;
	}

	protected void agregarComponente(Componente componente) {
		this.componentes.add(componente);
	}
	
	protected List<Componente> getComponentes(){
		//return this.componentes.stream().collect(Collectors.toUnmodifiableList());
		return this.componentes;
	}
	
	
}
