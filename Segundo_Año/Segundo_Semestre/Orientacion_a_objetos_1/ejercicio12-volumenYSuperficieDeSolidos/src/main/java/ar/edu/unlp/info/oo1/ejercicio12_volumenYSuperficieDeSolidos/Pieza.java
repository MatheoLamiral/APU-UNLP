package ar.edu.unlp.info.oo1.ejercicio12_volumenYSuperficieDeSolidos;

public abstract class Pieza {
	private String material;
	private String color;
	
	
	public Pieza(String material, String color) {
		this.material = material;
		this.color = color;
	}
	protected abstract double calcularVolumen();
	protected abstract double calcularSuperficie();
	
	public String getMaterial() {
		return material;
	}
	
	public String getColor() {
		return color;
	}
	
	
}
