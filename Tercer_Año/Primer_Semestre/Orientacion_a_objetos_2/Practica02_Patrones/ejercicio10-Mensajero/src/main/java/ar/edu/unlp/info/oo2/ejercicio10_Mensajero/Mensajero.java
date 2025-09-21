package ar.edu.unlp.info.oo2.ejercicio10_Mensajero;

public class Mensajero {
	private Strategy strategy;
	
	public void enviar(String mensaje) {
		this.strategy.encriptar(mensaje);
		System.out.println("mensaje enviado");	
	}
	
	public void recibir(String mensaje) {
		this.strategy.descencriptar(mensaje);
		System.out.println("mensaje recibido");
	}
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
