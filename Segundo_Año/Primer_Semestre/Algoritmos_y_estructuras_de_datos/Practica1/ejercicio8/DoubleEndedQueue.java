package Practica1.ejercicio8;

public class DoubleEndedQueue <T> extends Queue <T> {

	public void enqueueFirst(T dato){
		this.data.add(0,dato);
	}
}
