package ar.edu.unlp.info.oo2.ejercicio14_PrestamosPrendarios;

import java.util.ArrayList;
import java.util.List;

public class BienCompuesto extends Bien {
	private List<Bien> bienes;
	
	public BienCompuesto(double liquidez) {
		super(liquidez);
		this.bienes = new ArrayList();
	}

	@Override
	public double calcularValor() {
		// TODO Auto-generated method stub
		return this.bienes.stream()
						  .mapToDouble(Bien::calcularValor)
						  .sum();
	}
	
	public void agregarBien(Bien bien) {
		this.bienes.add(bien);
	}
}
