package ar.edu.unlp.info.oo1.ejercicio12_volumenYSuperficieDeSolidos;

public class PrismaRectangular extends Pieza {
	private int ladoMayor;
	private int ladoMenor;
	private int altura;
	
	
	public PrismaRectangular(String material, String color, int ladoMayor, int ladoMenor, int altura) {
		super(material, color);
		this.ladoMayor = ladoMayor;
		this.ladoMenor = ladoMenor;
		this.altura = altura;
	}

	@Override
	protected double calcularVolumen() {
		return this.ladoMayor*this.ladoMenor*this.altura;
	}

	@Override
	protected double calcularSuperficie() {
		return 2*(this.ladoMayor*this.ladoMenor+this.ladoMayor*this.altura+this.ladoMenor*this.altura);
	}

}
