package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import java.util.List;

public class Agua implements Topografia {

	@Override
	public double calcularProporcion() {
		//proporción de agua en una topografía de solo agua
		return 1;
	}
	
	@Override
	public boolean esIgual(Topografia topografia) {
		return topografia.esIgualAgua();
	}
	
	@Override
	public boolean esIgualAgua() {
		return true;
	}
	
	@Override
	public boolean esIgualTierra() {
		return false;
	}

	@Override
	public boolean esIgualPantano() {
		return false;
	}

	@Override
	public boolean esIgualMixta(List<Topografia> topografias) {
		return false;
	}

}
