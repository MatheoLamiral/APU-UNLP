package ar.edu.info.unlp.oo1.FigurasYCuerpos;

public class Cuerpo3D {
	private double altura;
	private Figura caraBasal;
	
	public double getSuperficieExterior() {
		return 2 * this.caraBasal.getArea() + this.caraBasal.getPerimetro() * this.altura;
	}

	public double getVolumen() {
		return this.caraBasal.getArea() * this.altura;
	}

	public double getAltura() {
		return this.altura;
	}

	public void setAltura(int i) {
		this.altura = i;
	}

	public void setCaraBasal(Figura figura) {
		this.caraBasal = figura;
	}

}
