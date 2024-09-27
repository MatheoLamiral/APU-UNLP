package ar.edu.info.unlp.oo1.balanzaElectronica;

public class Producto {
	private double peso;
	private double precioPorKilo;
	private String descripcion;
	
	public void setDescripcion(String string) {
		this.descripcion = string;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setPeso(double i) {
		this.peso = i;		
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPrecioPorKilo(double i) {
		this.precioPorKilo = i;
	}

	public double getPrecioPorKilo() {
		return this.precioPorKilo;
	}

	public double getPrecio() {
		return this.precioPorKilo * peso;
	}

}
