package Practica1.ejercicio2;

public class array {
	private int [] vec;
	private int dimF;
	
	public int[] vector (int n) {
		this.vec = new int [n];
		this.dimF = n;
		for (int i=0;i<n;i++) {
			this.vec[i] = n *(i+1); 
		}
		return vec;
	}

	public String toString() {
		String aux ="[";
		for (int j=0;j<dimF;j++) {
			aux += vec[j]+",";
		}
		aux += "]";
		return aux;
	}
}
