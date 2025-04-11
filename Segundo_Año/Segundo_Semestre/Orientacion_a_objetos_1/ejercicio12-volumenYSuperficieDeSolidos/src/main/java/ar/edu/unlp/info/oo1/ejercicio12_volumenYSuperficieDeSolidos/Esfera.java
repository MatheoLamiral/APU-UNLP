package ar.edu.unlp.info.oo1.ejercicio12_volumenYSuperficieDeSolidos;

public class Esfera extends Pieza {
	private int radio;
	
	public Esfera(String material, String color, int radio) {
		super(material, color);
		this.radio = radio;
	}

	@Override
	protected double calcularVolumen() {
		return (4/3)*Math.PI*Math.pow(this.radio, 3);
	}

	@Override
	protected double calcularSuperficie() {
		return 4*Math.PI*Math.pow(this.radio, 2);
	}

}
