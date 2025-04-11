package ar.edu.unlp.info.oo1.ejercicio12_volumenYSuperficieDeSolidos;

import java.util.List;

public class ReporteDeConstruccion {
	private List<Pieza> piezas;
	
	
	public ReporteDeConstruccion(List<Pieza> piezas) {
		this.piezas = piezas;
	}

	public double volumenDeMaterial(String material) {
		return this.piezas.stream().filter(pieza -> pieza.getMaterial().equals(material))
								   .mapToDouble(pieza -> pieza.calcularVolumen()).sum();

	}
	
	public double superficieDeColor(String color) {
		return this.piezas.stream().filter(pieza -> pieza.getColor().equals(color))
						  .mapToDouble(pieza -> pieza.calcularSuperficie()).sum();
	}
	
}
