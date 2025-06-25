package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import java.util.LinkedList;
import java.util.List;

public class Mixta implements Topografia {
	private List<Topografia> topografias;
	
	public Mixta(List<Topografia> topografias) {
		if(topografias.size() != 4)
			throw new IllegalArgumentException("La cantidad de elementos de la topografía debe ser exactamente 4.");
		this.topografias = new LinkedList<Topografia>(topografias);
	}
	
	@Override
	public double calcularProporcion() {
		return this.topografias.stream().mapToDouble(topografia -> topografia.calcularProporcion()).sum()/4;
	}
	
	@Override
	public boolean esIgual(Topografia topografia) {
		return topografia.esIgualMixta(this.getTopografias());
	}
	
	@Override
	public boolean esIgualMixta(List<Topografia> topografias) {
		for(int i = 0;i < 4; i++) {
			if(!this.getTopografias().get(i).esIgual(topografias.get(i)))
				return false;
		}
		return true;
	}

	@Override
	public boolean esIgualAgua() {
		return false;
	}

	@Override
	public boolean esIgualTierra() {
		return false;
	}

	@Override
	public boolean esIgualPantano() {
		return false;
	}

	private List<Topografia> getTopografias() {
		return this.topografias;
	}
}
