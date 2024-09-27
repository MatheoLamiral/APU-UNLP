package ar.edu.unlp.info.oo1.ejercicio7_redDeAlumbrado;

import java.util.*;

public class Farola {
	private boolean interruptor;
	private List<Farola> neighbors;
	/*
	* Crear una farola. Debe inicializarla como apagada
	*/
	public Farola () {
		this.interruptor = false;
		this.neighbors = new LinkedList<Farola>();
	}
	/*
	* Crea la relación de vecinos entre las farolas. La relación de vecinos entre las farolas es recíproca, es decir el receptor del mensaje será vecino de otraFarola, al igual que otraFarola también se convertirá en vecina del receptor del mensaje
	*/
	public void pairWithNeighbor( Farola otraFarola ) {
		this.neighbors.add(otraFarola);
		if(!otraFarola.getNeighbors().contains(this))
			otraFarola.pairWithNeighbor(this);
	}
	/*
	* Retorna sus farolas vecinas
	*/
	public List<Farola> getNeighbors (){
		return this.neighbors;
	}


	/*
	* Si la farola no está encendida, la enciende y propaga la acción.
	*/
	public void turnOn() {
		if(!this.isOn()) { 
			this.interruptor = true;
			for(Farola neighbor: this.getNeighbors()) {
					neighbor.turnOn();
			}
		}
	}

	/*
	* Si la farola no está apagada, la apaga y propaga la acción.
	*/
	public void turnOff() {
		if(this.isOn()) { 
			this.interruptor = false;
			for(Farola neighbor: this.getNeighbors()) {
					neighbor.turnOff();
			}
		}
	}
	/*
	* Retorna true si la farola está encendida.
	*/
	public boolean isOn() {
		return (this.interruptor) ? true : false;
	}
	/*
	* Retorna true si la farola está apagada.
	*/
	public boolean isOff() {
		return (!this.interruptor) ? true : false;
	}

}
