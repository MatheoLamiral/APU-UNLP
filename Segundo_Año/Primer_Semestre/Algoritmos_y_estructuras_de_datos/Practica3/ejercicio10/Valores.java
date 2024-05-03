package Practica3.ejercicio10;

import java.util.*;

public class Valores {
	private List<Integer> list;
	private int valor;
	
	public Valores(List<Integer> list, int valor) {
		this.list = new LinkedList<Integer>(list);
		this.setVal(valor);;
	}
	
	//GETTERS AND SETTERS
	public List<Integer> getList() {
		return list;
	}
	public int getVal() {
		return valor;
	}
	public void setVal(int max) {
		this.valor = max;
	}
	
	
}
