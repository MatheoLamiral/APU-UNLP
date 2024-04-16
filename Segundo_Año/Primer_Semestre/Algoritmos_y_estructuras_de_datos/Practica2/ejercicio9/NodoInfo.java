package Practica2.ejercicio9;

public class NodoInfo {
	private int suma;
	private int dif;
	
	public NodoInfo(int unaSuma, int unaDif) {
		this.suma = unaSuma;
		this.dif = unaDif;
	}

	
	@Override
	public String toString() {
		return "[suma=" + suma + ", dif=" + dif + "]";
	}


	//getters and setters 
	public int getSuma() {
		return suma;
	}

	public void setSuma(int suma) {
		this.suma = suma;
	}

	public int getDif() {
		return dif;
	}

	public void setDif(int dif) {
		this.dif = dif;
	}
	
	
}
