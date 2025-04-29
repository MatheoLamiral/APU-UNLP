package ar.edu.unlp.info.oo2.ejercicio11_Topografias;

import java.util.List;

public abstract class Topografia {
	
	public abstract double calcularProporcion();

	public abstract boolean esIgual(Topografia topografia);
	public abstract boolean esIgualAgua();
	public abstract boolean esIgualTierra();
	public abstract boolean esIgualMixta(List<Topografia> topografias);
}
