package ar.edu.info.unlp.oo1.FigurasYCuerpos;

public class Circulo implements Figura {
	private double radio;
	
	
	public double getDiametro() {
		return 2 * this.radio;
	}
	public void setDiametro(double diametro) {
		this.radio = diametro/2;
	}
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}
	
	public double getArea() {
		return Math.PI * Math.pow(this.radio,2);
	}
	
	public double getPerimetro() {
		return 2 * Math.PI * this.radio;
	}
}
