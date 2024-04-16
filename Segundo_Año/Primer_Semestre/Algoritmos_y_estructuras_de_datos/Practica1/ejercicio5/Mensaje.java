package Practica1.ejercicio5;

public class Mensaje {
	private int min;
	private int max;
	private float prom;
	
	public String toString() {
		return "mínimo: "+this.getMin()+"\n"+
			   "máximo: "+this.getMax()+"\n"+
			   "Promedio: "+this.getProm();
	}
	
	//getters and setters
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public float getProm() {
		return prom;
	}
	public void setProm(float prom) {
		this.prom = prom;
	}
	
	
}
