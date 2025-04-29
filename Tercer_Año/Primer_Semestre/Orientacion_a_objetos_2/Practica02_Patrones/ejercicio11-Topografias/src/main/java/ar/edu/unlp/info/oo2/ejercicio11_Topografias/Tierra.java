package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import java.util.List;

public class Tierra extends Topografia {

	@Override
	public double calcularProporcion() {
		//proporción de agua en una topografía de solo tierra
		return 0;
	}
	
	@Override
	public boolean esIgual(Topografia topografia) {
		return topografia.esIgualTierra();
	}
	
	@Override
	public boolean esIgualTierra() {
		return true;
	}
	
	@Override
	public boolean esIgualAgua() {
		return false;
	}

	@Override
	public boolean esIgualMixta(List<Topografia> topografias) {
		return false;
	}
	
}
