package Practica1.ejercicio8;

public class CircularQueue <T> extends Queue <T>{

	public void shift(){
		this.enqueue(this.dequeue());
	}
	
	public static void main(String[]args) {
		
	}
}
