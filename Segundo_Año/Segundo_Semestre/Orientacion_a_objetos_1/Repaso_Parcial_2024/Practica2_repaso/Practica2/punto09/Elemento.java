package Practica2.punto09;

public class Elemento {
	int suma;
	int dif;
	
	public Elemento (int unaSuma, int unaDif) {
		this.suma = unaSuma;
		this.dif = unaDif;
	}
	//GETTERS AND SETTERS 
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
	
	//TO STRING
	public String toString() {
		return "[suma=" + suma + ", dif=" + dif + "]";
	}
	
	
}
